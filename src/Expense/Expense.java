package Expense;

import java.sql.Date;
import java.time.LocalDate;

public class Expense {


    public long itemId;

    public long userId;

    public int amount;
    public String category;
    public LocalDate date;

    //no args constructor
    public Expense(){

    }
    //constructor for posting to Expense chart
    public Expense(int userId, int amount, String category, LocalDate date){
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    //constructor to retrieve from db
    public Expense(long itemId, int userId, int amount, String category, LocalDate date){
        this.itemId = itemId;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
