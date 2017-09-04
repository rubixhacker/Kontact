@file:JvmName("RxContactUtils")

package com.hackedcube.kontact.rxjava

import android.content.Context
import android.net.Uri
import com.hackedcube.kontact.Kontact
import com.hackedcube.kontact.getContactFromId
import com.hackedcube.kontact.queryAllContacts
import rx.Single

fun Context.allContactsSingle(): Single<List<Kontact>> {
    return Single.fromCallable { queryAllContacts() }
}

fun Context.contactSingle(uri: Uri): Single<Kontact> {
    return Single.fromCallable { getContactFromId(uri) }
}


