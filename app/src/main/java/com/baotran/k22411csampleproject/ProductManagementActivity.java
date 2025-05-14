package com.baotran.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.baotran.connectors.ProductConnector;
import com.baotran.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ProductConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        connector = new ProductConnector();
        adapter.addAll(connector.get_all_products());
        lvProduct.setAdapter(adapter);
    }

    private void addEvents() {
        lvProduct.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Product selected = adapter.getItem(position);
            new AlertDialog.Builder(ProductManagementActivity.this)
                    .setTitle("Xác nhận xoá")
                    .setMessage("Bạn có chắc muốn xoá sản phẩm này không?")
                    .setPositiveButton("Xoá", (dialog, which) -> adapter.remove(selected))
                    .setNegativeButton("Huỷ", null)
                    .show();
            return true;
        });
    }
}
