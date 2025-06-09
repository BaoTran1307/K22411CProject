package com.baotran.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baotran.models.OrderDetailItem;

import java.util.ArrayList;

public class OrderDetailsConnector {
    public ArrayList<OrderDetailItem> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetailItem> list = new ArrayList<>();

        String sql = "SELECT p.Name, od.Quantity, od.Price, od.Discount, od.VAT " +
                "FROM OrderDetails od " +
                "JOIN Product p ON od.ProductId = p.Id " +
                "WHERE od.OrderId = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});

        while (cursor.moveToNext()) {
            String productName = cursor.getString(0);
            int quantity = cursor.getInt(1);
            double price = cursor.getDouble(2);
            double discount = cursor.getDouble(3);
            double vat = cursor.getDouble(4);

            OrderDetailItem item = new OrderDetailItem(productName, quantity, price, vat, discount);
            list.add(item);
        }

        cursor.close();
        return list;
    }
}
