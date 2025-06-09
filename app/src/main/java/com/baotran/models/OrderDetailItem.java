package com.baotran.models;

public class OrderDetailItem {
    private String productName;
    private int quantity;
    private double price;
    private double vat;
    private double discount;

    public OrderDetailItem(String productName, int quantity, double price, double vat, double discount) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
        this.discount = discount;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getVat() {
        return vat;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        double subTotal = quantity * price;
        double discountAmount = subTotal * (discount / 100.0);
        double vatAmount = (subTotal - discountAmount) * (vat / 100.0);
        return subTotal - discountAmount + vatAmount;
    }
}
