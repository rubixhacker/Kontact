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
    var kontact = Kontact.createfromCursor(cursor)

    if (kontact.hasPhoneNumber()) {
        context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(kontact.id()), null).use { phoneCursor ->
            val phoneNumbers = generateSequence { if (phoneCursor.moveToNext()) phoneCursor else null }
                    .map { PhoneNumber.createfromCursor(it) }
                    .toList()

            kontact = kontact.withPhoneNumbers(phoneNumbers)
        }
    }

    return kontact
}