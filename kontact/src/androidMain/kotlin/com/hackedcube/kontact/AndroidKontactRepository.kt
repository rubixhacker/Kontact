package com.hackedcube.kontact

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds
import android.provider.ContactsContract.Contacts
import android.provider.ContactsContract.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class AndroidKontactRepository(private val context: Context) : KontactRepository {

    private val contentResolver: ContentResolver
        get() = context.contentResolver

    override suspend fun queryAllContacts(): List<Kontact> = withContext(Dispatchers.IO) {
        contentResolver.query(Contacts.CONTENT_URI, null, null, null, null)
            ?.use { cursor ->
                cursor.asSequence().map { buildKontact(it) }.toList()
            } ?: emptyList()
    }

    override suspend fun getContact(id: String): Kontact? = withContext(Dispatchers.IO) {
        contentResolver.query(
            Contacts.CONTENT_URI,
            null,
            "${Contacts._ID} = ?",
            arrayOf(id),
            null,
        )?.use { cursor ->
            if (cursor.moveToFirst()) buildKontact(cursor) else null
        }
    }

    override fun observeAllContacts(): Flow<List<Kontact>> = flow {
        emit(queryAllContacts())
    }.flowOn(Dispatchers.IO)

    private fun buildKontact(cursor: Cursor): Kontact {
        val id = cursor.getString(Contacts._ID)
        val hasPhone = cursor.getInt(Contacts.HAS_PHONE_NUMBER) == 1

        val base = Kontact(
            id = id,
            lookupKey = cursor.getString(Contacts.LOOKUP_KEY),
            displayNamePrimary = cursor.getString(Contacts.DISPLAY_NAME_PRIMARY),
            photoId = cursor.getLongOrNull(Contacts.PHOTO_ID),
            photoUri = cursor.getStringOrNull(Contacts.PHOTO_URI),
            thumbnailPhotoUri = cursor.getStringOrNull(Contacts.PHOTO_THUMBNAIL_URI),
            isVisible = cursor.getInt(Contacts.IN_VISIBLE_GROUP) == 1,
            hasPhoneNumber = hasPhone,
            timesContacted = cursor.getInt(Contacts.TIMES_CONTACTED),
            lastTimeContacted = cursor.getLong(Contacts.LAST_TIME_CONTACTED),
            isStarred = cursor.getInt(Contacts.STARRED) == 1,
            customRingtone = cursor.getStringOrNull(Contacts.CUSTOM_RINGTONE),
            sendToVoicemail = cursor.getInt(Contacts.SEND_TO_VOICEMAIL) == 1,
        )

        val phoneNumbers = if (hasPhone) queryPhoneNumbers(id) else emptyList()
        val emailAddresses = queryEmailAddresses(id)
        val (events, nicknames, postalAddresses, relations) = queryAdditionalData(id)

        return base.copy(
            phoneNumbers = phoneNumbers,
            emailAddresses = emailAddresses,
            events = events,
            nicknames = nicknames,
            postalAddresses = postalAddresses,
            relations = relations,
        )
    }

    private fun queryPhoneNumbers(contactId: String): List<PhoneNumber> {
        return contentResolver.query(
            CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(contactId),
            null,
        )?.use { cursor ->
            cursor.asSequence().map { c ->
                PhoneNumber(
                    id = c.getString(CommonDataKinds.Phone._ID),
                    number = c.getString(CommonDataKinds.Phone.NUMBER),
                    type = c.getInt(CommonDataKinds.Phone.TYPE).toPhoneType(),
                    label = c.getStringOrNull(CommonDataKinds.Phone.LABEL),
                )
            }.toList()
        } ?: emptyList()
    }

    private fun queryEmailAddresses(contactId: String): List<EmailAddress> {
        return contentResolver.query(
            CommonDataKinds.Email.CONTENT_URI,
            null,
            "${CommonDataKinds.Email.CONTACT_ID} = ?",
            arrayOf(contactId),
            null,
        )?.use { cursor ->
            cursor.asSequence().map { c ->
                EmailAddress(
                    id = c.getString(CommonDataKinds.Email._ID),
                    address = c.getString(CommonDataKinds.Email.ADDRESS),
                    type = c.getInt(CommonDataKinds.Email.TYPE).toEmailType(),
                    label = c.getStringOrNull(CommonDataKinds.Email.LABEL),
                )
            }.toList()
        } ?: emptyList()
    }

    private data class AdditionalData(
        val events: List<Event>,
        val nicknames: List<Nickname>,
        val postalAddresses: List<PostalAddress>,
        val relations: List<Relation>,
    )

    private fun queryAdditionalData(contactId: String): AdditionalData {
        val events = mutableListOf<Event>()
        val nicknames = mutableListOf<Nickname>()
        val postalAddresses = mutableListOf<PostalAddress>()
        val relations = mutableListOf<Relation>()

        val where = "${Data.CONTACT_ID} = ? AND ${Data.MIMETYPE} IN (?, ?, ?, ?)"
        val whereParams = arrayOf(
            contactId,
            CommonDataKinds.Event.CONTENT_ITEM_TYPE,
            CommonDataKinds.Nickname.CONTENT_ITEM_TYPE,
            CommonDataKinds.Relation.CONTENT_ITEM_TYPE,
            CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE,
        )

        contentResolver.query(Data.CONTENT_URI, null, where, whereParams, null)?.use { cursor ->
            for (c in cursor.asSequence()) {
                when (c.getString(Data.MIMETYPE)) {
                    CommonDataKinds.Event.CONTENT_ITEM_TYPE -> events += Event(
                        startDate = c.getStringOrNull(CommonDataKinds.Event.START_DATE),
                        type = c.getIntOrNull(CommonDataKinds.Event.TYPE)?.toEventType(),
                        label = c.getStringOrNull(CommonDataKinds.Event.LABEL),
                    )
                    CommonDataKinds.Nickname.CONTENT_ITEM_TYPE -> nicknames += Nickname(
                        name = c.getStringOrNull(CommonDataKinds.Nickname.NAME),
                        type = c.getIntOrNull(CommonDataKinds.Nickname.TYPE)?.toNicknameType(),
                        label = c.getStringOrNull(CommonDataKinds.Nickname.LABEL),
                    )
                    CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE -> postalAddresses += PostalAddress(
                        formattedAddress = c.getString(CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS),
                        type = c.getInt(CommonDataKinds.StructuredPostal.TYPE).toPostalAddressType(),
                        label = c.getStringOrNull(CommonDataKinds.StructuredPostal.LABEL),
                        street = c.getStringOrNull(CommonDataKinds.StructuredPostal.STREET),
                        pobox = c.getStringOrNull(CommonDataKinds.StructuredPostal.POBOX),
                        neighborhood = c.getStringOrNull(CommonDataKinds.StructuredPostal.NEIGHBORHOOD),
                        city = c.getStringOrNull(CommonDataKinds.StructuredPostal.CITY),
                        region = c.getStringOrNull(CommonDataKinds.StructuredPostal.REGION),
                        postcode = c.getStringOrNull(CommonDataKinds.StructuredPostal.POSTCODE),
                        country = c.getStringOrNull(CommonDataKinds.StructuredPostal.COUNTRY),
                    )
                    CommonDataKinds.Relation.CONTENT_ITEM_TYPE -> relations += Relation(
                        name = c.getString(CommonDataKinds.Relation.NAME),
                        type = c.getInt(CommonDataKinds.Relation.TYPE).toRelationType(),
                        label = c.getStringOrNull(CommonDataKinds.Relation.LABEL),
                    )
                }
            }
        }

        return AdditionalData(events, nicknames, postalAddresses, relations)
    }
}
