@file:JvmName("ContactUtils")

package com.hackedcube.kontact

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import io.reactivex.Single

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

fun Context.allContacts(): Single<List<Kontact>> {
    return Single.fromCallable { queryAllContacts() }
}

fun Context.allContactsSingle(): rx.Single<List<Kontact>> {
    return rx.Single.fromCallable { queryAllContacts() }
}

fun Context.contact(uri: Uri): Single<Kontact> {
    return Single.fromCallable { getContactFromId(uri) }
}

fun Context.contactSingle(uri: Uri): rx.Single<Kontact> {
    return rx.Single.fromCallable { getContactFromId(uri) }
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

    context.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", arrayOf(kontact.id()), null).use { emailCursor ->
        val emailAddresses = generateSequence { if (emailCursor.moveToNext()) emailCursor else null }
                .map { EmailAddress.createfromCursor(it) }
                .toList()

        kontact = kontact.withEmailAddresses(emailAddresses)
    }

    return kontact
}