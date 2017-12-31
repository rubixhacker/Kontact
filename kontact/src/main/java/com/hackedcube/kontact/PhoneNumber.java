package com.hackedcube.kontact;


import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.PhoneTypeIntAdapter;

@AutoValue
public abstract class PhoneNumber {


    @ColumnName(ContactsContract.CommonDataKinds.Phone._ID)
    public abstract String id();

    @ColumnName(ContactsContract.CommonDataKinds.Phone.NUMBER)
    public abstract String number();

    @ColumnAdapter(PhoneTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.Phone.TYPE)
    public abstract Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Phone.LABEL)
    public abstract String label();

    public static PhoneNumber create(Cursor cursor) {
        return AutoValue_PhoneNumber.createFromCursor(cursor);
    }

    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM),
        HOME(ContactsContract.CommonDataKinds.Phone.TYPE_HOME),
        MOBILE(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE),
        WORK(ContactsContract.CommonDataKinds.Phone.TYPE_WORK),
        FAX_WORK(ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK),
        FAX_HOME(ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME),
        PAGER(ContactsContract.CommonDataKinds.Phone.TYPE_PAGER),
        OTHER(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER),
        CALLBACK(ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK),
        CAR(ContactsContract.CommonDataKinds.Phone.TYPE_CAR),
        COMPANY_MAIN(ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN),
        ISDN(ContactsContract.CommonDataKinds.Phone.TYPE_ISDN),
        MAIN(ContactsContract.CommonDataKinds.Phone.TYPE_MAIN),
        OTHER_FAX(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX),
        RADIO(ContactsContract.CommonDataKinds.Phone.TYPE_RADIO),
        TELEX(ContactsContract.CommonDataKinds.Phone.TYPE_TELEX),
        TTY_TDD(ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD),
        WORK_MOBILE(ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE),
        WORK_PAGER(ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER),
        ASSISTANT(ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT),
        MMS(ContactsContract.CommonDataKinds.Phone.TYPE_MMS);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }

}
