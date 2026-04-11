package com.hackedcube.kontact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import platform.Contacts.CNContact
import platform.Contacts.CNContactFetchRequest
import platform.Contacts.CNContactFormatter
import platform.Contacts.CNContactFormatterStyle
import platform.Contacts.CNContactStore
import platform.Contacts.CNLabelHome
import platform.Contacts.CNLabelOther
import platform.Contacts.CNLabelPhoneNumberMobile
import platform.Contacts.CNLabelWork

/**
 * Apple-platform implementation of [KontactRepository] using the CNContacts framework.
 * Shared by iOS and macOS targets.
 */
class AppleKontactRepository : KontactRepository {

    private val store = CNContactStore()

    override suspend fun queryAllContacts(): List<Kontact> {
        val contacts = mutableListOf<Kontact>()
        val keysToFetch = contactKeysToFetch()
        val request = CNContactFetchRequest(keysToFetch = keysToFetch)

        store.enumerateContactsWithFetchRequest(request, error = null) { contact, _ ->
            if (contact != null) {
                contacts += contact.toKontact()
            }
        }
        return contacts
    }

    override suspend fun getContact(id: String): Kontact? {
        val keysToFetch = contactKeysToFetch()
        return try {
            val contact = store.unifiedContactWithIdentifier(id, keysToFetch = keysToFetch, error = null)
            contact?.toKontact()
        } catch (_: Exception) {
            null
        }
    }

    override fun observeAllContacts(): Flow<List<Kontact>> = flow {
        emit(queryAllContacts())
    }

    @Suppress("UNCHECKED_CAST")
    private fun contactKeysToFetch(): List<Any> = listOf(
        platform.Contacts.CNContactIdentifierKey,
        platform.Contacts.CNContactGivenNameKey,
        platform.Contacts.CNContactFamilyNameKey,
        platform.Contacts.CNContactPhoneNumbersKey,
        platform.Contacts.CNContactEmailAddressesKey,
        platform.Contacts.CNContactPostalAddressesKey,
        platform.Contacts.CNContactDatesKey,
        platform.Contacts.CNContactNicknameKey,
        platform.Contacts.CNContactRelationsKey,
        platform.Contacts.CNContactImageDataAvailableKey,
        CNContactFormatter.descriptorForRequiredKeysForStyle(CNContactFormatterStyle.CNContactFormatterStyleFullName),
    )

    private fun CNContact.toKontact(): Kontact {
        val fullName = CNContactFormatter.stringFromContact(this, CNContactFormatterStyle.CNContactFormatterStyleFullName) ?: ""

        return Kontact(
            id = identifier,
            lookupKey = identifier,
            displayNamePrimary = fullName,
            hasPhoneNumber = phoneNumbers.isNotEmpty(),
            phoneNumbers = phoneNumbers.mapNotNull { labeled ->
                @Suppress("UNCHECKED_CAST")
                val lv = labeled as? platform.Contacts.CNLabeledValue<platform.Contacts.CNPhoneNumber>
                lv?.let {
                    PhoneNumber(
                        id = identifier,
                        number = (it.value as platform.Contacts.CNPhoneNumber).stringValue,
                        type = it.label.toPhoneType(),
                        label = it.label,
                    )
                }
            },
            emailAddresses = emailAddresses.mapNotNull { labeled ->
                @Suppress("UNCHECKED_CAST")
                val lv = labeled as? platform.Contacts.CNLabeledValue<*>
                lv?.let {
                    EmailAddress(
                        id = identifier,
                        address = it.value.toString(),
                        type = it.label.toEmailType(),
                        label = it.label,
                    )
                }
            },
        )
    }

    private fun String?.toPhoneType(): PhoneNumber.Type = when (this) {
        CNLabelHome -> PhoneNumber.Type.HOME
        CNLabelWork -> PhoneNumber.Type.WORK
        CNLabelPhoneNumberMobile -> PhoneNumber.Type.MOBILE
        CNLabelOther -> PhoneNumber.Type.OTHER
        else -> PhoneNumber.Type.CUSTOM
    }

    private fun String?.toEmailType(): EmailAddress.Type = when (this) {
        CNLabelHome -> EmailAddress.Type.HOME
        CNLabelWork -> EmailAddress.Type.WORK
        CNLabelOther -> EmailAddress.Type.OTHER
        else -> EmailAddress.Type.CUSTOM
    }
}
