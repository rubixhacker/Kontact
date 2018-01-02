package com.hackedcube.kontact;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.hackedcube.kontact.columnadapters.RelationTypeIntAdapter;

@AutoValue
public abstract class Relation {

    @ColumnName(ContactsContract.CommonDataKinds.Relation.NAME)
    public abstract String name();

    @ColumnAdapter(RelationTypeIntAdapter.class)
    @ColumnName(ContactsContract.CommonDataKinds.Relation.TYPE)
    public abstract Relation.Type type();

    @Nullable
    @ColumnName(ContactsContract.CommonDataKinds.Relation.LABEL)
    public abstract String label();

    public static Relation create(Cursor cursor) {
        return AutoValue_Relation.createFromCursor(cursor);
    }


    public enum Type {
        CUSTOM(ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM),
        ASSISTANT(ContactsContract.CommonDataKinds.Relation.TYPE_ASSISTANT),
        BROTHER(ContactsContract.CommonDataKinds.Relation.TYPE_BROTHER),
        CHILD(ContactsContract.CommonDataKinds.Relation.TYPE_CHILD),
        DOMESTIC_PARTNER(ContactsContract.CommonDataKinds.Relation.TYPE_DOMESTIC_PARTNER),
        FATHER(ContactsContract.CommonDataKinds.Relation.TYPE_FATHER),
        FRIEND(ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND),
        MANAGER(ContactsContract.CommonDataKinds.Relation.TYPE_MANAGER),
        MOTHER(ContactsContract.CommonDataKinds.Relation.TYPE_MOTHER),
        PARENT(ContactsContract.CommonDataKinds.Relation.TYPE_PARENT),
        PARTNER(ContactsContract.CommonDataKinds.Relation.TYPE_PARTNER),
        REFERRED_BY(ContactsContract.CommonDataKinds.Relation.TYPE_REFERRED_BY),
        RELATIVE(ContactsContract.CommonDataKinds.Relation.TYPE_RELATIVE),
        SISTER(ContactsContract.CommonDataKinds.Relation.TYPE_SISTER),
        SPOUSE(ContactsContract.CommonDataKinds.Relation.TYPE_SPOUSE);

        int typeMap;

        Type(int columnName) {
            this.typeMap = columnName;
        }

        public int getTypeMap() {
            return typeMap;
        }
    }


}
