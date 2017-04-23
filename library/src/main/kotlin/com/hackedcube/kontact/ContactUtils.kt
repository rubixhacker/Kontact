@file:JvmName("ContactUtils")
package com.hackedcube.kontact

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract


fun Context.queryAllContacts(): List<Kontact> {
    return listOf()
}

fun Context.getContactFromId(uri: Uri): Kontact? {
    val builder = Kontact.builder()

    val cursorId = contentResolver.query(uri, arrayOf(ContactsContract.Contacts._ID), null, null, null)

    val contactId: String by lazy { cursorId.getString(cursorId.getColumnIndex(ContactsContract.Contacts._ID)) }


    if (cursorId.moveToFirst()) {
        builder.id(contactId)

    }


    return builder.build()
}