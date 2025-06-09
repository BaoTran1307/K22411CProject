package com.baotran.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baotran.models.OrderDetails;

import java.util.ArrayList;

public class OrderDetailConnector {

    public ArrayList<OrderDetails> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();

        String sql = "SELECT Id, OrderId, ProductId, Quantity, Price, Discount, VAT, TotalValue " +
                "FROM OrderDetails WHERE OrderId = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});
        while (cursor.moveToNext()) {
            OrderDetails od = new OrderDetails();
            od.setId(cursor.getInt(0));
            od.setOrderId(cursor.getInt(1));
            od.setProductId(cursor.getInt(2));
            od.setQuantity(cursor.getInt(3));
            od.setPrice(cursor.getDouble(4));
            od.setDiscount(cursor.getDouble(5));
            od.setVAT(cursor.getDouble(6));
            od.setTotalValue(cursor.getDouble(7));
            orderDetailsList.add(od);
        }
        cursor.close();
        return orderDetailsList;
    }
}