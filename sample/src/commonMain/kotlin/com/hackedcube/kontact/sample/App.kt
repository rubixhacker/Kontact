package com.hackedcube.kontact.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackedcube.kontact.Kontact
import com.hackedcube.kontact.KontactRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(repository: KontactRepository) {
    MaterialTheme {
        var contacts by remember { mutableStateOf<List<Kontact>>(emptyList()) }
        var loading by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Kontact") })
            },
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        loading = true
                        scope.launch {
                            contacts = repository.queryAllContacts()
                            loading = false
                        }
                    },
                    enabled = !loading,
                ) {
                    Text(if (loading) "Loading..." else "Import All Contacts")
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "${contacts.size} contacts loaded",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(contacts, key = { it.id }) { contact ->
                        ContactCard(contact)
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactCard(contact: Kontact) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = contact.displayNamePrimary,
                style = MaterialTheme.typography.titleMedium,
            )

            if (contact.phoneNumbers.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                contact.phoneNumbers.forEach { phone ->
                    Row {
                        Text(
                            text = "${phone.type}: ",
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Text(
                            text = phone.number,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }

            if (contact.emailAddresses.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                contact.emailAddresses.forEach { email ->
                    Row {
                        Text(
                            text = "${email.type}: ",
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Text(
                            text = email.address,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}
