package com.hackedcube.kontact.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hackedcube.kontact.ContactUtils;
import com.hackedcube.kontact.Kontact;

import java.util.List;

public class MainActivity extends Activity {

    private final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonImportContanct = (Button) findViewById(R.id.buttonImport);

        buttonImportContanct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        List<Kontact> kontacts = ContactUtils.queryAllContacts(this);

        Log.d("Kontacts", "Kontacts Count: " + kontacts);

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
