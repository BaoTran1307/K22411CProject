package com.baotran.k22411csampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        if (bundle != null) {
            try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                StringBuilder fullMessage = new StringBuilder();
                String sender = "";

                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    if (i == 0) {
                        sender = msgs[i].getOriginatingAddress();
                    }
                    fullMessage.append(msgs[i].getMessageBody());
                }

                String content = fullMessage.toString();
                Toast.makeText(context, "Tin nhắn mới: " + content, Toast.LENGTH_LONG).show();
                Log.d("SMS_RECEIVER", "From: " + sender + " | Content: " + content);

                // Đẩy lên Firebase
                pushSmsToFirebase(sender, content);

            } catch (Exception e) {
                Log.e("SMS_RECEIVER", "Exception: " + e.getMessage());
            }
        }
    }

    private void pushSmsToFirebase(String sender, String content) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("sms_logs");
        String key = db.push().getKey();
        if (key != null) {
            db.child(key).setValue(new SmsLog(sender, content));
        }
    }
}
