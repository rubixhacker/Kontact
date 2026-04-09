package com.hackedcube.kontact

data class Kontact(
    val id: String,
    val lookupKey: String,
    val displayNamePrimary: String,
    val photoId: Long? = null,
    val photoUri: String? = null,
    val thumbnailPhotoUri: String? = null,
    val isVisible: Boolean = true,
    val hasPhoneNumber: Boolean = false,
    val timesContacted: Int = 0,
    val lastTimeContacted: Long = 0L,
    val isStarred: Boolean = false,
    val customRingtone: String? = null,
    val sendToVoicemail: Boolean = false,
    val phoneNumbers: List<PhoneNumber> = emptyList(),
    val emailAddresses: List<EmailAddress> = emptyList(),
    val relations: List<Relation> = emptyList(),
    val postalAddresses: List<PostalAddress> = emptyList(),
    val nicknames: List<Nickname> = emptyList(),
    val events: List<Event> = emptyList(),
)

data class PhoneNumber(
    val id: String,
    val number: String,
    val type: Type,
    val label: String? = null,
) {
    enum class Type {
        CUSTOM, HOME, MOBILE, WORK, FAX_WORK, FAX_HOME, PAGER, OTHER,
        CALLBACK, CAR, COMPANY_MAIN, ISDN, MAIN, OTHER_FAX, RADIO,
        TELEX, TTY_TDD, WORK_MOBILE, WORK_PAGER, ASSISTANT, MMS,
    }
}

data class EmailAddress(
    val id: String,
    val address: String,
    val type: Type,
    val label: String? = null,
) {
    enum class Type {
        CUSTOM, HOME, WORK, OTHER, MOBILE,
    }
}

data class PostalAddress(
    val formattedAddress: String,
    val type: Type,
    val label: String? = null,
    val street: String? = null,
    val pobox: String? = null,
    val neighborhood: String? = null,
    val city: String? = null,
    val region: String? = null,
    val postcode: String? = null,
    val country: String? = null,
) {
    enum class Type {
        CUSTOM, HOME, WORK, OTHER,
    }
}

data class Event(
    val startDate: String? = null,
    val type: Type? = null,
    val label: String? = null,
) {
    enum class Type {
        CUSTOM, ANNIVERSARY, OTHER, BIRTHDAY,
    }
}

data class Nickname(
    val name: String? = null,
    val type: Type? = null,
    val label: String? = null,
) {
    enum class Type {
        CUSTOM, DEFAULT, OTHER, MAIDEN, SHORT, INITIALS,
    }
}

data class Relation(
    val name: String,
    val type: Type,
    val label: String? = null,
) {
    enum class Type {
        CUSTOM, ASSISTANT, BROTHER, CHILD, DOMESTIC_PARTNER, FATHER,
        FRIEND, MANAGER, MOTHER, PARENT, PARTNER, REFERRED_BY,
        RELATIVE, SISTER, SPOUSE,
    }
}
