package com.baotran.connectors;

import com.baotran.models.ListProduct;
import com.baotran.models.Product;

import java.util.ArrayList;

public class ProductConnector {
    ListProduct listProduct;

    public ProductConnector() {
        listProduct = new ListProduct();
//        listProduct.generate_sample_dataset();
    }

    public ArrayList<Product> get_all_products() {
        if (listProduct == null) {
            listProduct = new ListProduct();
//            listProduct.generate_sample_dataset();
        }
        return listProduct.getProducts();
    }
}
