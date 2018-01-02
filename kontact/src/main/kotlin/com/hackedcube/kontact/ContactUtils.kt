@file:JvmName("ContactUtils")

package com.hackedcube.kontact

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract


fun Context.queryAllContacts(): List<Kontact> {
    contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null).use {
        return generateSequence { if (it.moveToNext()) it else null }
                .map { kontactFromCursor(this, it) }
                .toList()
    }
}

fun Context.getContactFromId(uri: Uri): Kontact? {
    contentResolver.query(uri, null, null, null, null).use { cursorContact ->
        cursorContact.moveToFirst()
        return kontactFromCursor(this, cursorContact)
    }
}

private fun kontactFromCursor(context: Context, cursor: Cursor): Kontact {
    var kontact = Kontact.create(cursor)

    // Fetch Phone Numbers
    if (kontact.hasPhoneNumber()) {
        context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(kontact.id()), null).use { phoneCursor ->
            val phoneNumbers = phoneCursor.toSequence()
                    .map { PhoneNumber.create(it) }
                    .toList()

            kontact = kontact.withPhoneNumbers(phoneNumbers)
        }
    }

    // Fetch Email addresses
    context.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", arrayOf(kontact.id()), null).use { emailCursor ->
        val emailAddresses = emailCursor.toSequence()
                .map { EmailAddress.create(it) }
                .toList()

        kontact = kontact.withEmailAddresses(emailAddresses)
    }

    val select = arrayOf(ContactsContract.Data.MIMETYPE, "data1", "data2", "data3", "data4",
            "data5", "data6", "data7", "data8", "data9", "data10", "data11", "data12", "data13",
            "data14", "data15")

    // Fetch additional info
    val where = "${ContactsContract.Data.CONTACT_ID} = ? AND ${ContactsContract.Data.MIMETYPE} IN (?, ?, ?)"

    val whereParams = arrayOf(
            kontact.id(),
            ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE,
            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE
    )

    context.contentResolver.query(ContactsContract.Data.CONTENT_URI, select, where, whereParams, null).use { dataCursor ->

        val data = dataCursor.toSequence()
                .map {
                    val columnType = it.getString(it.getColumnIndex(ContactsContract.Data.MIMETYPE))

                    when(columnType) {
                        ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE -> columnType to Nickname.create(it)
                        ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE -> columnType to Relation.create(it)
                        ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE -> columnType to PostalAddress.create(it)
                        else -> columnType to null
                    }
                }
                .groupBy({it.first}, {it.second})




        kontact = kontact.toBuilder()
                .nicknames(data[ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE] as MutableList<Nickname>?)
                .postalAddresses(data[ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE] as MutableList<PostalAddress>?)
                .relations(data[ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE] as MutableList<Relation>?)
                .build()
    }



    return kontact
}