package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.EventTypeIntAdapter;

@AutoValue
public abstract class Event {

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Event.START_DATE)
    public abstract String startDate();

    @Nullable
    @ColumnAdapter(EventTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.Event.TYPE)
    public abstract Event.Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Event.LABEL)
    public abstract String label();

    public static Event create(Cursor cursor) {
        return AutoValue_Event.createFromCursor(cursor);
    }

    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.Event.TYPE_CUSTOM),
        ANNIVERSARY(ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY),
        OTHER(ContactsContract.CommonDataKinds.Event.TYPE_OTHER),
        BIRTHDAY(ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }

}
