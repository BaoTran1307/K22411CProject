package com.baotran.k22411csampleproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
public class MainActivity extends AppCompatActivity {

    ImageView imgEmployee;
    TextView txtEmployee;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgCategory;
    TextView txtCategory;
    ImageView imgProduct;
    TextView txtProduct;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;
    ImageView imgPaymentMethod;
    TextView txtPaymentMethod;
    ImageView imgOrder;
    TextView txtOrder;
    ImageView imgTelephony;
    TextView txtTelephony;
    ImageView imgMultiThreading;
    TextView txtMultiThreading;
    ImageView imgSms;
    TextView txtSms;
    private static final int SMS_PERMISSION_CODE = 100;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Kiểm tra và yêu cầu quyền SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, SMS_PERMISSION_CODE);
        }
        addViews();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền SMS được cấp!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quyền SMS bị từ chối, ứng dụng có thể không hoạt động!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManagementActivity();
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManagementActivity();
            }
        });
//        imgCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCategoryManagementActivity();
//            }package com.baotran.k22411csampleproject;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class MainActivity extends AppCompatActivity {
//
//    ImageView imgEmployee;
//    TextView txtEmployee;
//    ImageView imgCustomer;
//    TextView txtCustomer;
//    ImageView imgCategory;
//    TextView txtCategory;
//    ImageView imgProduct;
//    TextView txtProduct;
//    ImageView imgAdvancedProduct;
//    TextView txtAdvancedProduct;
//    ImageView imgPaymentMethod;
//    TextView txtPaymentMethod;
//    ImageView imgOrder;
//    TextView txtOrder;
//    ImageView imgTelephony;
//    TextView txtTelephony;
//    ImageView imgMultiThreading;
//    TextView txtMultiThreading;
//    ImageView imgSms;
//    TextView txtSms;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        addViews();
//        addEvents();
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//
//    private void addEvents() {
//        imgEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //gọi code mở màn hình quản trị nhân sự
//                openEmployeeManagementActivity();
//            }
//        });
//        txtEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //gọi code mở màn hình quản trị nhân sự
//                openEmployeeManagementActivity();
//            }
//        });
//        imgCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomerManagementActivity();
//            }
//        });
//        txtCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomerManagementActivity();
//            }
//        });
////        imgCategory.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                openCategoryManagementActivity();
////            }
////        });
////        txtCategory.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                openCategoryManagementActivity();
////            }
////        });
//        imgProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openProductManagementActivity();
//            }
//        });
//        txtProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openProductManagementActivity();
//            }
//        });
//        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAdvancedProductManagementActivity();
//            }
//        });
//        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAdvancedProductManagementActivity();
//            }
//        });
//        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPaymentMethodActivity();
//            }
//        });
//        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPaymentMethodActivity();
//            }
//        });
//        imgOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openOrdersViewerActivity();
//            }
//        });
//        txtOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openOrdersViewerActivity();
//            }
//        });
//        imgTelephony.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTelephonyActivity();
//            }
//        });
//        txtTelephony.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTelephonyActivity();
//            }
//        });
//        imgMultiThreading.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMultiThreadingCategoriesActivity();
//            }
//        });
//        txtMultiThreading.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMultiThreadingCategoriesActivity();
//            }
//        });
//        imgSms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openSmsActivity();
//            }
//        });
//        txtSms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openSmsActivity();
//            }
//        });
//
//    }
//
//    private void openSmsActivity() {
//        Intent intent=new Intent(MainActivity.this, SmsActivity.class);
//        startActivity(intent);
//    }
//
//    private void openMultiThreadingCategoriesActivity() {
//        Intent intent=new Intent(MainActivity.this, MultiThreadingCategoriesActivity.class);
//        startActivity(intent);
//    }
//
//    private void openTelephonyActivity() {
//        Intent intent=new Intent(MainActivity.this, TelephonyActivity.class);
//        startActivity(intent);
//    }
//
//    private void openOrdersViewerActivity() {
//        Intent intent=new Intent(MainActivity.this, OrdersViewerActivity.class);
//        startActivity(intent);
//    }
//
//    private void openPaymentMethodActivity() {
//        Intent intent=new Intent(MainActivity.this, PaymentMethodActivity.class);
//        startActivity(intent);
//    }
//
//    private void openAdvancedProductManagementActivity() {
//        Intent intent=new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
//        startActivity(intent);
//    }
//
//    void openEmployeeManagementActivity()
//    {
//        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
//        startActivity(intent);
//    }
//    void openCustomerManagementActivity()
//    {
//        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
//        startActivity(intent);
//    }
////    void openCategoryManagementActivity()
////    {
////        Intent intent=new Intent(MainActivity.this, CategoryManagementActivity.class);
////        startActivity(intent);
////    }
//    void openProductManagementActivity()
//    {
//        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
//        startActivity(intent);
//    }
//
//    private void addViews() {
//        imgEmployee=findViewById(R.id.imgEmployee);
//        txtEmployee=findViewById(R.id.txtEmployee);
//        imgCustomer=findViewById(R.id.imgCustomer);
//        txtCustomer=findViewById(R.id.txtCustomer);
////        imgCategory=findViewById(R.id.imgCategory);
////        txtCategory=findViewById(R.id.txtCategory);
//        imgProduct=findViewById(R.id.imgProduct);
//        txtProduct=findViewById(R.id.txtProduct);
//        imgAdvancedProduct=findViewById(R.id.imgAdvancedProduct);
//        txtAdvancedProduct=findViewById(R.id.txtAdvancedProduct);
//        imgPaymentMethod=findViewById(R.id.imgPaymentMethod);
//        txtPaymentMethod=findViewById(R.id.txtPaymentMethod);
//        imgOrder=findViewById(R.id.imgOrder);
//        txtOrder=findViewById(R.id.txtOrder);
//        imgTelephony=findViewById(R.id.imgTelephony);
//        txtTelephony=findViewById(R.id.txtTelephony);
//        imgMultiThreading=findViewById(R.id.imgMultiThreading);
//        txtMultiThreading=findViewById(R.id.txtMultiThreading);
//    }
//}
//        });
//        txtCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCategoryManagementActivity();
//            }
//        });
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });
        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        imgTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        txtTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        imgMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingCategoriesActivity();
            }
        });
        txtMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingCategoriesActivity();
            }
        });
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSmsActivity();
            }
        });
        txtSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSmsActivity();
            }
        });

    }

    private void openSmsActivity() {
        Intent intent=new Intent(MainActivity.this, SmsActivity.class);
        startActivity(intent);
    }

    private void openMultiThreadingCategoriesActivity() {
        Intent intent=new Intent(MainActivity.this, MultiThreadingCategoriesActivity.class);
        startActivity(intent);
    }

    private void openTelephonyActivity() {
        Intent intent=new Intent(MainActivity.this, TelephonyActivity.class);
        startActivity(intent);
    }

    private void openOrdersViewerActivity() {
        Intent intent=new Intent(MainActivity.this, OrdersViewerActivity.class);
        startActivity(intent);
    }

    private void openPaymentMethodActivity() {
        Intent intent=new Intent(MainActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
    }

    private void openAdvancedProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }

    void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }
    void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }
//    void openCategoryManagementActivity()
//    {
//        Intent intent=new Intent(MainActivity.this, CategoryManagementActivity.class);
//        startActivity(intent);
//    }
    void openProductManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    private void addViews() {
        imgEmployee=findViewById(R.id.imgEmployee);
        txtEmployee=findViewById(R.id.txtEmployee);
        imgCustomer=findViewById(R.id.imgCustomer);
        txtCustomer=findViewById(R.id.txtCustomer);
//        imgCategory=findViewById(R.id.imgCategory);
//        txtCategory=findViewById(R.id.txtCategory);
        imgProduct=findViewById(R.id.imgProduct);
        txtProduct=findViewById(R.id.txtProduct);
        imgAdvancedProduct=findViewById(R.id.imgAdvancedProduct);
        txtAdvancedProduct=findViewById(R.id.txtAdvancedProduct);
        imgPaymentMethod=findViewById(R.id.imgPaymentMethod);
        txtPaymentMethod=findViewById(R.id.txtPaymentMethod);
        imgOrder=findViewById(R.id.imgOrder);
        txtOrder=findViewById(R.id.txtOrder);
        imgTelephony=findViewById(R.id.imgTelephony);
        txtTelephony=findViewById(R.id.txtTelephony);
        imgMultiThreading=findViewById(R.id.imgMultiThreading);
        txtMultiThreading=findViewById(R.id.txtMultiThreading);
        imgSms = findViewById(R.id.imgSms); // Thêm dòng này
        txtSms = findViewById(R.id.txtSms);

    }
}