package com.hackedcube.kontact

import kotlinx.coroutines.flow.Flow

/**
 * Platform-agnostic interface for querying device contacts.
 */
interface KontactRepository {
    suspend fun queryAllContacts(): List<Kontact>
    suspend fun getContact(id: String): Kontact?
    fun observeAllContacts(): Flow<List<Kontact>>
}
