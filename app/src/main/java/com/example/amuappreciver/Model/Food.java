package com.example.amuappreciver.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Food {

    private String menuName;
    private String menuId;
    private String price;
    private String amount;

    public Food() {
    }

    public Food(String menuName, String menuId, String price, String amount) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.price = price;
        this.amount = amount;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
