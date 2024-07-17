package Expense;

import java.sql.Timestamp;

public class Expense {


    public int itemId;

    public int userId;

    public int amount;
    public String category;
    public Timestamp date;

    //no args constructor
    public Expense(){

    }
    //constructor for posting to main.java.Expense chart
    public Expense(int userId, int amount, String category, Timestamp date){
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    //constructor to retrieve from db
    public Expense(int itemId, int userId, int amount, String category, Timestamp date){
        this.itemId = itemId;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
