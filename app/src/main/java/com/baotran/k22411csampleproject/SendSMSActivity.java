package com.baotran.k22411csampleproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.baotran.models.TelephonyInfor;

public class SendSMSActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_SEND_SMS = 123;

    TextView txtTelephonyName;
    TextView txtTelephonyNumber;
    EditText edtBody;
    ImageView imgSendSms1;
    ImageView imgSendSms2;
    TelephonyInfor ti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_smsactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Kiểm tra và xin quyền
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_SEND_SMS);
        }

        addViews();
        addEvents();
    }

    private void addViews() {
        txtTelephonyName = findViewById(R.id.txtTelephonyName);
        txtTelephonyNumber = findViewById(R.id.txtTelephonyNumber);
        edtBody = findViewById(R.id.edtBody);
        imgSendSms1 = findViewById(R.id.imgSendsms1);
        imgSendSms2 = findViewById(R.id.imgSendsms2);
        Intent intent = getIntent();
        ti = (TelephonyInfor) intent.getSerializableExtra("TI");
        if (ti != null) {
            txtTelephonyName.setText(ti.getName());
            txtTelephonyNumber.setText(ti.getPhone());
        }
    }

    private void addEvents() {
        imgSendSms1.setOnClickListener(view -> {
            if (checkSmsPermission()) {
                sendSms(ti, edtBody.getText().toString());
            }
        });

        imgSendSms2.setOnClickListener(view -> {
            if (checkSmsPermission()) {
                sendSmsPendingIntent(ti, edtBody.getText().toString());
            }
        });
    }

    private boolean checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Bạn cần cấp quyền để gửi SMS", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_SEND_SMS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã cấp quyền gửi SMS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bạn đã từ chối quyền gửi SMS", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sendSms(TelephonyInfor ti, String content) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(ti.getPhone(), null, content, null, null);
        Toast.makeText(this, "Đã gửi tin nhắn tới " + ti.getPhone(),
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void sendSmsPendingIntent(TelephonyInfor ti, String content) {
        SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");

        PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = (result == Activity.RESULT_OK) ? "Gửi thành công" : "Gửi thất bại";
                Toast.makeText(SendSMSActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));

        sms.sendTextMessage(ti.getPhone(), null, content, pendingMsgSent, null);
    }
}
