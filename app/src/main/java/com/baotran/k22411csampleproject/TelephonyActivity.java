package com.baotran.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.baotran.adapters.TelephonyInforAdapter;
import com.baotran.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephony;
    TelephonyInforAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        getAllContacts();
        addEvents();
    }

    private void addViews() {
        lvTelephony = findViewById(R.id.lvTelephonyInfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TelephonyInfor ti = adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Toast.makeText(this, "Calling " + ti.getPhone(), Toast.LENGTH_SHORT).show();
        // Bạn có thể thay bằng Intent gọi trực tiếp nếu muốn
    }

    public void directCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialupCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (adapter != null) {
            adapter.clear();
        }

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phone = cursor.getString(phoneIndex);

                TelephonyInfor ti = new TelephonyInfor(name, phone);
                adapter.add(ti);
            }
            cursor.close();
        }
    }

    private void filterByCarrier(String targetCarrier) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (adapter != null) adapter.clear();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String carrier = getCarrier(phone);

                if (carrier.equals(targetCarrier)) {
                    TelephonyInfor ti = new TelephonyInfor(name, phone);
                    adapter.add(ti);
                }
            }
            cursor.close();
        }
    }

    private String getCarrier(String phoneNumber) {
        phoneNumber = phoneNumber.replace(" ", "").replace("+84", "0");

        if (phoneNumber.startsWith("096") || phoneNumber.startsWith("097") || phoneNumber.startsWith("098") ||
                phoneNumber.startsWith("032") || phoneNumber.startsWith("033") || phoneNumber.startsWith("034") ||
                phoneNumber.startsWith("035") || phoneNumber.startsWith("036") || phoneNumber.startsWith("037") ||
                phoneNumber.startsWith("038") || phoneNumber.startsWith("039")) {
            return "Viettel";
        } else if (phoneNumber.startsWith("090") || phoneNumber.startsWith("093") ||
                phoneNumber.startsWith("070") || phoneNumber.startsWith("076") ||
                phoneNumber.startsWith("077") || phoneNumber.startsWith("078") ||
                phoneNumber.startsWith("079")) {
            return "Mobifone";
        } else {
            return "Other";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_telephony, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnu_viettel) {
            filterByCarrier("Viettel");
        } else if (id == R.id.mnu_mobifone) {
            filterByCarrier("Mobifone");
        } else if (id == R.id.mnu_other) {
            filterByCarrier("Other");
        } else if (id == R.id.mnu_all) {
            getAllContacts();
        }
        return super.onOptionsItemSelected(item);
    }

}
