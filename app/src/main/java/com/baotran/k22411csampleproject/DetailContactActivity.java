package com.baotran.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailContactActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPhone, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Áp dụng EdgeToEdge
        setContentView(R.layout.activity_detail_contact);

        // Xử lý WindowInsets để tránh bị che bởi thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        getContactDetail();
    }

    private void addViews() {
        edtId = findViewById(R.id.edtContactId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtId.setEnabled(false); // Không cho phép chỉnh sửa ID
    }

    private void getContactDetail() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("KEY");
        if (key == null || key.trim().isEmpty()) {
            Toast.makeText(this, "Không tìm thấy thông tin liên hệ!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://k22411csampleproject-9970a-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("contacts").child(key);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        String name = dataSnapshot.child("name").getValue(String.class);
                        Object phoneObj = dataSnapshot.child("phone").getValue(); // Lấy giá trị thô
                        String phone = (phoneObj instanceof Long) ? String.valueOf((Long) phoneObj) : (phoneObj != null ? phoneObj.toString() : "");
                        String email = dataSnapshot.child("email").getValue(String.class);

                        if (name == null || phone == null || email == null) {
                            Log.w("MY_ERROR", "Dữ liệu thiếu: name=" + name + ", phone=" + phone + ", email=" + email);
                            Toast.makeText(DetailContactActivity.this, "Dữ liệu không đầy đủ!", Toast.LENGTH_SHORT).show();
                        }

                        edtId.setText(key);
                        edtName.setText(name != null ? name : "");
                        edtPhone.setText(phone);
                        edtEmail.setText(email != null ? email : "");
                    } catch (Exception ex) {
                        Log.e("MY_ERROR", "Lỗi tải dữ liệu: " + ex.toString());
                        Toast.makeText(DetailContactActivity.this, "Lỗi tải dữ liệu: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.w("MY_ERROR", "Không tìm thấy liên hệ với key: " + key);
                    Toast.makeText(DetailContactActivity.this, "Liên hệ không tồn tại!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("MY_ERROR", "loadPost:onCancelled: " + databaseError.getMessage(), databaseError.toException());
                Toast.makeText(DetailContactActivity.this, "Lỗi kết nối Firebase: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void processUpdateContact(View view) {
        String key = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (key.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ID và Tên!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://k22411csampleproject-9970a-default-rtdb.asia-southeast1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference("contacts");

            myRef.child(key).child("name").setValue(name);
            myRef.child(key).child("phone").setValue(phone); // Lưu dưới dạng String
            myRef.child(key).child("email").setValue(email);

            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("MY_ERROR", "Lỗi cập nhật: " + ex.toString());
            Toast.makeText(this, "Lỗi cập nhật: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void processDeleteContact(View view) {
        String key = edtId.getText().toString().trim();
        if (key.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn liên hệ để xóa!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://k22411csampleproject-9970a-default-rtdb.asia-southeast1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference("contacts");
            myRef.child(key).removeValue();

            Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception ex) {
            Log.e("MY_ERROR", "Lỗi xóa: " + ex.toString());
            Toast.makeText(this, "Lỗi xóa: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void processBack(View view) {
        finish(); // Quay lại SmsActivity
    }
}