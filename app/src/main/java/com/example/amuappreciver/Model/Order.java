package com.example.amuappreciver.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private String orderId;
    private String address;
    private String phone;
    private String total;

    public Order() {
    }

    public Order(String orderId, String address, String phone, String total) {
        this.orderId = orderId;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
