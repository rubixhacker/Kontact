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
            val phoneNumbers = generateSequence { if (phoneCursor.moveToNext()) phoneCursor else null }
                    .map { PhoneNumber.create(it) }
                    .toList()

            kontact = kontact.withPhoneNumbers(phoneNumbers)
        }
    }

    // Fetch Email addresses
    context.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", arrayOf(kontact.id()), null).use { emailCursor ->
        val emailAddresses = generateSequence { if (emailCursor.moveToNext()) emailCursor else null }
                .map { EmailAddress.create(it) }
                .toList()

        kontact = kontact.withEmailAddresses(emailAddresses)
    }

    // Fetch additional info
    val where = String.format(
            "%s = ? AND %s = ?",
            ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.Relation.CONTACT_ID)

    val whereParams = arrayOf(
            ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE,
            kontact.id()
    )

    context.contentResolver.query(ContactsContract.Data.CONTENT_URI, null, where, whereParams, null).use { dataCursor ->
        val relation = Relation.create(dataCursor)

        kontact = kontact.withRelation(relation)
    }



    return kontact
}