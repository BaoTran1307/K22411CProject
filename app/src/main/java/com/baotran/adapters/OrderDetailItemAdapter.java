package com.baotran.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baotran.models.OrderDetailItem;
import com.baotran.models.OrderDetails;
import com.baotran.k22411csampleproject.R;

import java.util.List;

public class OrderDetailItemAdapter extends ArrayAdapter<OrderDetailItem> {
    Activity context;
    int resource;

    public OrderDetailItemAdapter(Activity context, int resource, List<OrderDetailItem> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item_order_detail, null);
        TextView txtProductName = row.findViewById(R.id.txtProductName);
        TextView txtQuantity = row.findViewById(R.id.txtQuantity);
        TextView txtPrice = row.findViewById(R.id.txtPrice);
        TextView txtVat = row.findViewById(R.id.txtVat);
        TextView txtDiscount = row.findViewById(R.id.txtDiscount);
        TextView txtTotal = row.findViewById(R.id.txtTotal);

        OrderDetailItem item = getItem(position); // ✅ đúng class

        txtProductName.setText("Name: " + item.getProductName());
        txtQuantity.setText("Qty: " + item.getQuantity());
        txtPrice.setText("Price: " + item.getPrice() + "đ");
        txtVat.setText("VAT: " + item.getVat() + "%");
        txtDiscount.setText("Discount: " + item.getDiscount() + "%");
        txtTotal.setText(String.format("Total: %.0fđ", item.getTotal()));

        return row;
    }
}
