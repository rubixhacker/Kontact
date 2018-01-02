package com.hackedcube.kontact.sample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hackedcube.kontact.ContactUtils;
import com.hackedcube.kontact.Kontact;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class MainActivity extends Activity {

    private final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // NO OP
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // NO-OP
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();


        Button buttonImportContact = (Button) findViewById(R.id.buttonImport);

        buttonImportContact.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, PICK_CONTACT);
        });


        Button buttonImportAllContact = (Button) findViewById(R.id.buttonImportAllContacts);


        buttonImportAllContact.setOnClickListener(v -> {
            List<Kontact> kontacts = ContactUtils.queryAllContacts(this);
            Log.d("Kontacts", "Kontacts Count: " + kontacts);
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT) {
            Kontact kontact = ContactUtils.getContactFromId(this, data.getData());

            Log.d("Kontact Import", "Kontact: " + kontact.toString());
        }

    }
}
