package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.EmailTypeIntAdapter;

@AutoValue
public abstract class EmailAddress {

    @ColumnName(ContactsContract.CommonDataKinds.Email._ID)
    public abstract String id();

    @ColumnName(ContactsContract.CommonDataKinds.Email.ADDRESS)
    public abstract String number();

    @ColumnAdapter(EmailTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.Email.TYPE)
    public abstract Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Email.LABEL)
    public abstract String label();

    public static EmailAddress create(Cursor cursor) {
        return AutoValue_EmailAddress.createFromCursor(cursor);
    }

    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM),
        HOME(ContactsContract.CommonDataKinds.Email.TYPE_HOME),
        WORK(ContactsContract.CommonDataKinds.Email.TYPE_WORK),
        OTHER(ContactsContract.CommonDataKinds.Email.TYPE_OTHER),
        MOBILE(ContactsContract.CommonDataKinds.Email.TYPE_MOBILE);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }
}
