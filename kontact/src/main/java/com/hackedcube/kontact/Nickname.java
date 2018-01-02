package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.NicknameTypeIntAdapter;
import com.hackedcube.kontact.columnadapters.RelationTypeIntAdapter;

@AutoValue
public abstract class Nickname {

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Nickname.NAME)
    public abstract String name();

    @Nullable
    @ColumnAdapter(NicknameTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.Nickname.TYPE)
    public abstract Nickname.Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Nickname.LABEL)
    public abstract String label();

    public static Nickname create(Cursor cursor) {
        return AutoValue_Nickname.createFromCursor(cursor);
    }

    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.Nickname.TYPE_CUSTOM),
        DEFAULT(ContactsContract.CommonDataKinds.Nickname.TYPE_DEFAULT),
        OTHER(ContactsContract.CommonDataKinds.Nickname.TYPE_OTHER_NAME),
        MAIDEN(ContactsContract.CommonDataKinds.Nickname.TYPE_MAIDEN_NAME),
        SHORT(ContactsContract.CommonDataKinds.Nickname.TYPE_SHORT_NAME),
        INITIALS(ContactsContract.CommonDataKinds.Nickname.TYPE_INITIALS);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }

}
