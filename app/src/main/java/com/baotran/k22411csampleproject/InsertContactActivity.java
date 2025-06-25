package com.baotran.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertContactActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPhone, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);
        addViews();
    }

    private void addViews() {
        edtId = findViewById(R.id.edtContactId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
    }

    public void InsertContactActivity(View view) {
        String contactId = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (contactId.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ID và Tên!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://k22411csampleproject-9970a-default-rtdb.asia-southeast1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference("contacts");

            // Chèn dữ liệu
            myRef.child(contactId).child("name").setValue(name);
            myRef.child(contactId).child("phone").setValue(phone);
            myRef.child(contactId).child("email").setValue(email);

            Toast.makeText(this, "Thêm liên hệ thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại SmsActivity
        } catch (Exception ex) {
            Toast.makeText(this, "Lỗi: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}