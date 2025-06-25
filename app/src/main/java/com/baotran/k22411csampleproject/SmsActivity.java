package com.baotran.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SmsActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayAdapter<String> contactAdapter;
    String TAG = "FIREBASE";
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        // Xử lý WindowInsets để tránh bị che
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvContact), (v, insets) -> {
            androidx.core.graphics.Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        lvContact = findViewById(R.id.lvContact);
        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvContact.setAdapter(contactAdapter);
        btnInsert = findViewById(R.id.btnInsert);
        loadData();
    }

    private void addEvents() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInsertContactActivity();
            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = contactAdapter.getItem(position);
                String key = data.split("\n")[0]; // Tách key từ chuỗi hiển thị
                Intent intent = new Intent(SmsActivity.this, DetailContactActivity.class);
                intent.putExtra("KEY", key);
                startActivity(intent);
            }
        });
    }

    private void openInsertContactActivity() {
        Intent intent = new Intent(SmsActivity.this, InsertContactActivity.class);
        startActivity(intent);
    }

    private void loadData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://k22411csampleproject-9970a-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("contacts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactAdapter.clear();
                Log.d(TAG, "Số lượng contacts: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String key = data.getKey();
                    String name = data.child("name").getValue(String.class);
                    if (name != null) {
                        String displayText = key + "\n" + name;
                        Object phoneObj = data.child("phone").getValue();
                        if (phoneObj != null) {
                            String phone;
                            if (phoneObj instanceof Long) {
                                phone = String.valueOf((Long) phoneObj);
                            } else if (phoneObj instanceof String) {
                                phone = (String) phoneObj;
                            } else {
                                phone = "Không xác định";
                            }
                            displayText += " - " + phone;
                        }
                        contactAdapter.add(displayText);
                    } else {
                        Log.w(TAG, "Dữ liệu name null cho key: " + key);
                    }
                }
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled: " + databaseError.getMessage() + " (Code: " + databaseError.getCode() + ")", databaseError.toException());
            }
        });
    }
}