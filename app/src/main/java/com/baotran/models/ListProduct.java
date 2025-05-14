package com.baotran.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void generate_sample_dataset() {
        Random random = new Random();
        String[] sampleNames = {"Laptop", "Phone", "Tablet", "Camera", "Monitor", "Headphones", "Keyboard", "Mouse", "Speaker", "Charger"};
        String[] sampleDescriptions = {
                "Sản phẩm chất lượng cao", "Giá tốt", "Ưa chuộng", "Bán chạy", "Đang khuyến mãi", "Sản phẩm mới", "Hàng tồn kho"
        };

        for (int i = 1; i <= 100; i++) {
            int id = i;
            String name = sampleNames[random.nextInt(sampleNames.length)] + " " + (1000 + i);
            int quantity = random.nextInt(100) + 1;
            double price = 100000 + (random.nextInt(50) * 10000);
            int cate_id = random.nextInt(6) + 1;
            String description = sampleDescriptions[random.nextInt(sampleDescriptions.length)];

            Product p = new Product(id, name, quantity, price, cate_id, description);
            addProduct(p);
        }
    }
}
