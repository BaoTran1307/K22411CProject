package com.baotran.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.baotran.adapters.PaymentMethodAdapter;
import com.baotran.connectors.PaymentMethodConnector;
import com.baotran.connectors.SQLiteConnector;
import com.baotran.models.ListPaymentMethod;
import com.baotran.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

//    private void addViews() {
//        lvPaymentMethod=findViewById(R.id.lvPaymentMethod);
//        adapter=new PaymentMethodAdapter(PaymentMethodActivity.this,R.layout.item_paymentmethod);
//        lvPaymentMethod.setAdapter(adapter);
//        lpm=new ListPaymentMethod();
//        lpm.gen_payments_method();
//        adapter.addAll(lpm.getPaymentMethods());
//    }
private void addViews() {
    lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
    adapter = new PaymentMethodAdapter(PaymentMethodActivity.this, R.layout.item_paymentmethod);
    lvPaymentMethod.setAdapter(adapter);

    // Đọc từ SQLite
    PaymentMethodConnector connector = new PaymentMethodConnector();
    SQLiteConnector sqlite = new SQLiteConnector(this);
    ArrayList<PaymentMethod> data = connector.getAll(sqlite.openDatabase());
    adapter.clear();
    adapter.addAll(data);
}

}