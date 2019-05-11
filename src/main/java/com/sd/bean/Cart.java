package com.sd.bean;

import com.sd.entity.Product;

public class Cart {
    private Product product ;
    private int quantityBuy;

    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantityBuy = quantity;
    }

    public Cart() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }


}
