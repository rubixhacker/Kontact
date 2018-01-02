package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.PostalAddressTypeIntAdapter;

@AutoValue
public abstract class PostalAddress {


    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)
    public abstract String formattedAddress();

    @ColumnAdapter(PostalAddressTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.TYPE)
    public abstract PostalAddress.Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.LABEL)
    public abstract String label();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.STREET)
    public abstract String street();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)
    public abstract String pobox();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD)
    public abstract String neighborhood();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.CITY)
    public abstract String city();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.REGION)
    public abstract String region();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)
    public abstract String postcode();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)
    public abstract String country();

    public static PostalAddress create(Cursor cursor) {
        return AutoValue_PostalAddress.createFromCursor(cursor);
    }

    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.StructuredPostal.TYPE_CUSTOM),
        HOME(ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME),
        WORK(ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK),
        OTHER(ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }

}
