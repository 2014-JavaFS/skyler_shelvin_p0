package Expense;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ExpenseService  {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public Boolean deleteExpense(Expense expense){
        return expenseRepository.delete(expense);
    };

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }


    public List<Expense> findByCategory(int userId, String category) {
        return expenseRepository.findByCategory(userId, category);
    }


    public List<Expense> findByDate(int userId, Timestamp start, Timestamp end) throws SQLException {
        return expenseRepository.findByDate(userId, start, end);
    }


    public Expense create(Expense newObject) {
        return expenseRepository.create(newObject);
    }
}
