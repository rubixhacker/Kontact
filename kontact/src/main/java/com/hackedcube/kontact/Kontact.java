package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.BooleanIntAdapter;

import java.util.List;

@AutoValue
public abstract class Kontact {

    @ColumnName(ContactsContract.Contacts._ID)
    public abstract String id();

    @ColumnName(ContactsContract.Contacts.LOOKUP_KEY)
    public abstract String lookupKey();

    @ColumnName(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
    public abstract String displayNamePrimary();

    @Nullable
    @ColumnName(ContactsContract.Contacts.PHOTO_ID)
    public abstract Long photoId();

    @Nullable
    @ColumnName(ContactsContract.Contacts.PHOTO_URI)
    public abstract Long photoUri();

    @Nullable
    @ColumnName(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)
    public abstract Long thumbnailPhotoUri();

    @ColumnAdapter(BooleanIntAdapter.class)
    @ColumnName(ContactsContract.Contacts.IN_VISIBLE_GROUP)
    public abstract Boolean isVisible();

    @ColumnAdapter(BooleanIntAdapter.class)
    @ColumnName(ContactsContract.Contacts.HAS_PHONE_NUMBER)
    public abstract Boolean hasPhoneNumber();

    @ColumnName(ContactsContract.Contacts.TIMES_CONTACTED)
    public abstract Integer timesContacted();

    @ColumnName(ContactsContract.Contacts.LAST_TIME_CONTACTED)
    public abstract Long lastTimeContacted();

    @ColumnAdapter(BooleanIntAdapter.class)
    @ColumnName(ContactsContract.Contacts.STARRED)
    public abstract Boolean isStarred();

    @Nullable
    @ColumnName(ContactsContract.Contacts.CUSTOM_RINGTONE)
    public abstract String customRingtone();

    @ColumnAdapter(BooleanIntAdapter.class)
    @ColumnName(ContactsContract.Contacts.SEND_TO_VOICEMAIL)
    public abstract Boolean sendToVoicemail();

    @Nullable
    public abstract List<PhoneNumber> phoneNumbers();

    @Nullable
    public abstract List<EmailAddress> emailAddresses();

    @Nullable
    public abstract List<Relation> relations();

    @Nullable
    public abstract List<PostalAddress> postalAddresses();

    @Nullable
    public abstract List<Nickname> nicknames();

    public static Kontact create(Cursor cursor) {
        return AutoValue_Kontact.createFromCursor(cursor);
    }

    public static Builder builder() {
        return new AutoValue_Kontact.Builder();
    }

    public abstract Builder toBuilder();

    public abstract Kontact withPhoneNumbers(List<PhoneNumber> phoneNumbers);

    public abstract Kontact withEmailAddresses(List<EmailAddress> emailAddresses);

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder lookupKey(String lookupKey);

        public abstract Builder displayNamePrimary(String displayNamePrimary);

        public abstract Builder photoId(Long photoId);

        public abstract Builder photoUri(Long photoUri);

        public abstract Builder thumbnailPhotoUri(Long thumbnailPhotoUri);

        public abstract Builder isVisible(Boolean isVisible);

        public abstract Builder hasPhoneNumber(Boolean hasPhoneNumber);

        public abstract Builder timesContacted(Integer timesContacted);

        public abstract Builder lastTimeContacted(Long lastTimeContacted);

        public abstract Builder isStarred(Boolean isStarred);

        public abstract Builder customRingtone(String customRingtone);

        public abstract Builder sendToVoicemail(Boolean sendToVoicemail);

        public abstract Builder phoneNumbers(List<PhoneNumber> phoneNumbers);

        public abstract Builder emailAddresses(List<EmailAddress> emailAddresses);

        public abstract Builder relations(List<Relation> relations);

        public abstract Builder postalAddresses(List<PostalAddress> postalAddresses);

        public abstract Builder nicknames(List<Nickname> nicknames);

        public abstract Kontact build();
    }
}
