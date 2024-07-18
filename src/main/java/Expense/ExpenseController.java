package Expense;

import Util.DataNotFoundException;
import io.javalin.Javalin;
import io.javalin.http.Context;


import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public void registerPaths(Javalin app) {
        app.post("/expenses", this::addExpense);
        app.get("/expenses", this::getAllExpenses);
        app.delete("/expenses/itemId", this::deleteExpense);
        app.get("/expenses/filter/{userId}/{category}", this::filterExpensesByCategory);
        app.get("/expenses/summary/{userId}", ctx -> getMonthlySummary(ctx));
    }


    private void getMonthlySummary(@NotNull Context ctx) {
        int userId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("userId")));

        try {
            YearMonth currentMonth = YearMonth.now();
            LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
            LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);
            Timestamp startTimestamp = Timestamp.valueOf(startOfMonth);
            Timestamp endTimestamp = Timestamp.valueOf(endOfMonth);

            List<Expense> summary = expenseService.findByDate(userId, startTimestamp, endTimestamp);
            ctx.json(summary);
            ctx.status(200);
        } catch (DataNotFoundException | SQLException e) {
            e.printStackTrace();
            ctx.status(404).json("No expenses found within the specified date range.");
        }
    }

    private void filterExpensesByCategory(@NotNull Context ctx) {
        int userId = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("userId")));
        String category = ctx.queryParam("category");

        if(userId == 0){
            ctx.status(403);
            ctx.result("You are not logged in. Please login.");
            return;
        }

        if(category == null || category.isEmpty()){
            ctx.status(400);
            ctx.result("Category is missing or empty");
            return;
        }

        try{
            List<Expense> expenses = expenseService.findByCategory(userId, category);
            ctx.json(expenses);
            ctx.status(200);
        } catch(Exception e){
            e.printStackTrace();
            ctx.status(500);
            ctx.result("Error retrieving expenses for the category" +category);
        }

    }

    private void deleteExpense(@NotNull Context ctx) {
        try{
            Expense expense = ctx.bodyAsClass(Expense.class);
            boolean isDeleted = expenseService.deleteExpense(expense);
            if(isDeleted){
                ctx.status(200);
                ctx.result("Expense is Deleted.");
            }else{
                ctx.status(404);
                ctx.result("Expense not found or could not be deleted.");
            }
        }catch(Exception e){
            e.printStackTrace();
            ctx.status(500);
            ctx.result("Error deleting expense");
        }

    }

    private void getAllExpenses(@NotNull Context ctx) {
       try{
           List<Expense> expenses = expenseService.findAll();
           ctx.json(expenses);
           ctx.status(200);
        } catch(Exception e){
           e.printStackTrace();
           ctx.status(500);
           ctx.result("Error retrieving expenses.");
        }

    }

    private void addExpense(@NotNull Context ctx) {
        try{
            System.out.println("Incoming request:" + ctx.body());
            Expense newExpense = ctx.bodyAsClass(Expense.class);
            System.out.println("Parsed Expense object: " + newExpense);
            Expense createdExpense = expenseService.create(newExpense);
            if (createdExpense != null){
                ctx.json(createdExpense);
                ctx.status(201);
            }else {
                ctx.status(500).result("Error adding new expense.");
            }
        } catch(Exception e){
            e.printStackTrace();
            ctx.status(500).result("error adding new expense.");
        }
    }


}
