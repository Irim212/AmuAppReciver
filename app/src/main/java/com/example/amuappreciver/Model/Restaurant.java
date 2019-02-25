package com.example.amuappreciver.Model;

public class Restaurant {

    private String RestaurantId;
    private String Name;
    private String Password;

    public Restaurant() {
    }

    public Restaurant(String restaurantId, String name, String password) {
        RestaurantId = restaurantId;
        Name = name;
        Password = password;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
