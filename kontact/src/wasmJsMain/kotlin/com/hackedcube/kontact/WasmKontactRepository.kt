package com.hackedcube.kontact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * WasmJs (browser) implementation of [KontactRepository].
 *
 * The Contact Picker API is experimental and only available in
 * Chromium-based browsers. This stub returns empty results;
 * consumers can extend it to integrate with the browser API.
 */
class WasmKontactRepository : KontactRepository {

    override suspend fun queryAllContacts(): List<Kontact> = emptyList()

    override suspend fun getContact(id: String): Kontact? = null

    override fun observeAllContacts(): Flow<List<Kontact>> = flow {
        emit(emptyList())
    }
}
