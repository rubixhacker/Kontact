package com.hackedcube.kontact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Desktop (JVM) implementation of [KontactRepository].
 *
 * Desktop platforms do not have a standardised contacts API.
 * This stub returns empty results; consumers can extend it to
 * integrate with platform-specific stores (e.g. macOS Contacts via JNI).
 */
class DesktopKontactRepository : KontactRepository {

    override suspend fun queryAllContacts(): List<Kontact> = emptyList()

    override suspend fun getContact(id: String): Kontact? = null

    override fun observeAllContacts(): Flow<List<Kontact>> = flow {
        emit(emptyList())
    }
}
