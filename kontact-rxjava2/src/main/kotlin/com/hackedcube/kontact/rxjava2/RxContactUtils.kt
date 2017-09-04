@file:JvmName("RxContactUtils")

package com.hackedcube.kontact.rxjava2

import android.content.Context
import android.net.Uri
import com.hackedcube.kontact.Kontact
import com.hackedcube.kontact.getContactFromId
import com.hackedcube.kontact.queryAllContacts
import io.reactivex.Single

fun Context.allContacts(): Single<List<Kontact>> {
    return Single.fromCallable { queryAllContacts() }
}

fun Context.contact(uri: Uri): Single<Kontact> {
    return Single.fromCallable { getContactFromId(uri) }
}