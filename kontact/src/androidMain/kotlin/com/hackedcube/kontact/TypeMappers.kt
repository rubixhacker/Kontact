package com.hackedcube.kontact

import android.provider.ContactsContract.CommonDataKinds

internal fun Int.toPhoneType(): PhoneNumber.Type = when (this) {
    CommonDataKinds.Phone.TYPE_HOME -> PhoneNumber.Type.HOME
    CommonDataKinds.Phone.TYPE_MOBILE -> PhoneNumber.Type.MOBILE
    CommonDataKinds.Phone.TYPE_WORK -> PhoneNumber.Type.WORK
    CommonDataKinds.Phone.TYPE_FAX_WORK -> PhoneNumber.Type.FAX_WORK
    CommonDataKinds.Phone.TYPE_FAX_HOME -> PhoneNumber.Type.FAX_HOME
    CommonDataKinds.Phone.TYPE_PAGER -> PhoneNumber.Type.PAGER
    CommonDataKinds.Phone.TYPE_OTHER -> PhoneNumber.Type.OTHER
    CommonDataKinds.Phone.TYPE_CALLBACK -> PhoneNumber.Type.CALLBACK
    CommonDataKinds.Phone.TYPE_CAR -> PhoneNumber.Type.CAR
    CommonDataKinds.Phone.TYPE_COMPANY_MAIN -> PhoneNumber.Type.COMPANY_MAIN
    CommonDataKinds.Phone.TYPE_ISDN -> PhoneNumber.Type.ISDN
    CommonDataKinds.Phone.TYPE_MAIN -> PhoneNumber.Type.MAIN
    CommonDataKinds.Phone.TYPE_OTHER_FAX -> PhoneNumber.Type.OTHER_FAX
    CommonDataKinds.Phone.TYPE_RADIO -> PhoneNumber.Type.RADIO
    CommonDataKinds.Phone.TYPE_TELEX -> PhoneNumber.Type.TELEX
    CommonDataKinds.Phone.TYPE_TTY_TDD -> PhoneNumber.Type.TTY_TDD
    CommonDataKinds.Phone.TYPE_WORK_MOBILE -> PhoneNumber.Type.WORK_MOBILE
    CommonDataKinds.Phone.TYPE_WORK_PAGER -> PhoneNumber.Type.WORK_PAGER
    CommonDataKinds.Phone.TYPE_ASSISTANT -> PhoneNumber.Type.ASSISTANT
    CommonDataKinds.Phone.TYPE_MMS -> PhoneNumber.Type.MMS
    else -> PhoneNumber.Type.CUSTOM
}

internal fun Int.toEmailType(): EmailAddress.Type = when (this) {
    CommonDataKinds.Email.TYPE_HOME -> EmailAddress.Type.HOME
    CommonDataKinds.Email.TYPE_WORK -> EmailAddress.Type.WORK
    CommonDataKinds.Email.TYPE_OTHER -> EmailAddress.Type.OTHER
    CommonDataKinds.Email.TYPE_MOBILE -> EmailAddress.Type.MOBILE
    else -> EmailAddress.Type.CUSTOM
}

internal fun Int.toPostalAddressType(): PostalAddress.Type = when (this) {
    CommonDataKinds.StructuredPostal.TYPE_HOME -> PostalAddress.Type.HOME
    CommonDataKinds.StructuredPostal.TYPE_WORK -> PostalAddress.Type.WORK
    CommonDataKinds.StructuredPostal.TYPE_OTHER -> PostalAddress.Type.OTHER
    else -> PostalAddress.Type.CUSTOM
}

internal fun Int.toEventType(): Event.Type = when (this) {
    CommonDataKinds.Event.TYPE_ANNIVERSARY -> Event.Type.ANNIVERSARY
    CommonDataKinds.Event.TYPE_OTHER -> Event.Type.OTHER
    CommonDataKinds.Event.TYPE_BIRTHDAY -> Event.Type.BIRTHDAY
    else -> Event.Type.CUSTOM
}

internal fun Int.toNicknameType(): Nickname.Type = when (this) {
    CommonDataKinds.Nickname.TYPE_DEFAULT -> Nickname.Type.DEFAULT
    CommonDataKinds.Nickname.TYPE_OTHER_NAME -> Nickname.Type.OTHER
    CommonDataKinds.Nickname.TYPE_MAIDEN_NAME -> Nickname.Type.MAIDEN
    CommonDataKinds.Nickname.TYPE_SHORT_NAME -> Nickname.Type.SHORT
    CommonDataKinds.Nickname.TYPE_INITIALS -> Nickname.Type.INITIALS
    else -> Nickname.Type.CUSTOM
}

internal fun Int.toRelationType(): Relation.Type = when (this) {
    CommonDataKinds.Relation.TYPE_ASSISTANT -> Relation.Type.ASSISTANT
    CommonDataKinds.Relation.TYPE_BROTHER -> Relation.Type.BROTHER
    CommonDataKinds.Relation.TYPE_CHILD -> Relation.Type.CHILD
    CommonDataKinds.Relation.TYPE_DOMESTIC_PARTNER -> Relation.Type.DOMESTIC_PARTNER
    CommonDataKinds.Relation.TYPE_FATHER -> Relation.Type.FATHER
    CommonDataKinds.Relation.TYPE_FRIEND -> Relation.Type.FRIEND
    CommonDataKinds.Relation.TYPE_MANAGER -> Relation.Type.MANAGER
    CommonDataKinds.Relation.TYPE_MOTHER -> Relation.Type.MOTHER
    CommonDataKinds.Relation.TYPE_PARENT -> Relation.Type.PARENT
    CommonDataKinds.Relation.TYPE_PARTNER -> Relation.Type.PARTNER
    CommonDataKinds.Relation.TYPE_REFERRED_BY -> Relation.Type.REFERRED_BY
    CommonDataKinds.Relation.TYPE_RELATIVE -> Relation.Type.RELATIVE
    CommonDataKinds.Relation.TYPE_SISTER -> Relation.Type.SISTER
    CommonDataKinds.Relation.TYPE_SPOUSE -> Relation.Type.SPOUSE
    else -> Relation.Type.CUSTOM
}
