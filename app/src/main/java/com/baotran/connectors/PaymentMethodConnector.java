package com.baotran.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baotran.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodConnector {

    public ArrayList<PaymentMethod> getAll(SQLiteDatabase db) {
        ArrayList<PaymentMethod> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod pm = new PaymentMethod(id, name, description);
            list.add(pm);
        }
        cursor.close();
        return list;
    }
}
