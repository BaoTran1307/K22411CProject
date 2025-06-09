package com.baotran.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baotran.k22411csampleproject.R;
import com.baotran.models.OrderDetails;

import java.util.List;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetails> {
    Activity context;
    int resource;

    public OrderDetailAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtProductId = item.findViewById(R.id.txtProductId);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        TextView txtDiscount = item.findViewById(R.id.txtDiscount);
        TextView txtVAT = item.findViewById(R.id.txtVAT);
        TextView txtTotalValue = item.findViewById(R.id.txtTotalValue);

        OrderDetails od = getItem(position);
        txtProductId.setText("Product ID: " + od.getProductId());
        txtQuantity.setText("Quantity: " + od.getQuantity());
        txtPrice.setText("Price: " + od.getPrice() + " VND");
        txtDiscount.setText("Discount: " + od.getDiscount() + "%");
        txtVAT.setText("VAT: " + od.getVAT() + "%");
        txtTotalValue.setText("Total Value: " + od.getTotalValue() + " VND");

        return item;
    }
}