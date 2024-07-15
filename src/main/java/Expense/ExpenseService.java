package main.java.Expense;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    Expense viewExpense(Expense expense);

    Expense deleteExpense(Expense expense):

    Expense filterExpense(Expense expense);

    Expense expenseSummary(Expense expense);


}
