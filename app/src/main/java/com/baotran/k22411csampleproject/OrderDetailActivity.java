package com.baotran.k22411csampleproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baotran.adapters.OrderDetailItemAdapter;
import com.baotran.connectors.OrderDetailsConnector;
import com.baotran.models.OrderDetailItem;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    TextView txtOrderCode, txtOrderDate, txtCustomerName, txtEmployeeName, txtOrderTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail); // Giao diện sẽ tạo ở bước sau

        // Ánh xạ TextView
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtOrderDate = findViewById(R.id.txtOrderDate);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtEmployeeName = findViewById(R.id.txtEmployeeName);
        txtOrderTotalValue = findViewById(R.id.txtOrderTotalValue);

        // Nhận dữ liệu từ intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            txtOrderCode.setText(bundle.getString("code"));
            txtOrderDate.setText(bundle.getString("orderDate"));
            txtCustomerName.setText(bundle.getString("customerName"));
            txtEmployeeName.setText(bundle.getString("employeeName"));
            txtOrderTotalValue.setText(bundle.getDouble("total") + " VNĐ");

        }
        // 1. Nhận orderId từ Intent
        int orderId = getIntent().getIntExtra("orderId", -1);

// 2. Mở DB
        SQLiteDatabase db = openOrCreateDatabase("TenCSDL.sqlite", MODE_PRIVATE, null);

// 3. Truy vấn danh sách sản phẩm theo OrderId
        OrderDetailsConnector connector = new OrderDetailsConnector();
        ArrayList<OrderDetailItem> items = connector.getOrderDetailsByOrderId(db, orderId);

// 4. Gắn vào ListView
        ListView listView = findViewById(R.id.listOrderItems);
        OrderDetailItemAdapter adapter = new OrderDetailItemAdapter(this, R.layout.item_order_detail, items);
        listView.setAdapter(adapter);

    }
}
