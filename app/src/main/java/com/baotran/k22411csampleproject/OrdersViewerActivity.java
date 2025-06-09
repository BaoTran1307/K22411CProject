package com.baotran.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.baotran.adapters.OrdersViewerAdapter;
import com.baotran.connectors.OrdersViewerConnector;
import com.baotran.connectors.SQLiteConnector;
import com.baotran.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {
    ListView lvOrdersView;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersView = findViewById(R.id.lvOrdersViewer);
        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersView.setAdapter(adapter);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOrderViewers(connector.openDatabase());
        adapter.addAll(dataset);

        // Thêm sự kiện click vào ListView
        lvOrdersView.setOnItemClickListener((parent, view, position, id) -> {
            OrdersViewer order = adapter.getItem(position);
            Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailActivity.class);
            intent.putExtra("ORDER_ID", order.getId());
            intent.putExtra("ORDER_CODE", order.getCode());
            startActivity(intent);
        });
    }
}