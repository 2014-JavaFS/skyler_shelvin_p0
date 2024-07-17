package Expense;

import Account.Account;
import Util.DataNotFoundException;
import Util.Serviceable;

import java.util.List;

public class ExpenseService implements Serviceable<Expense> {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    Expense deleteExpense(Expense expense):


    @Override
    public List<Expense> findAll() {
        return null;
    }

    @Override
    public Expense findById(int number) {
        return null;
    }

    @Override
    public Expense findByEmail(String email) {
        return null;
    }

    @Override
    public Expense create(Expense newObject) {
        return null;
    }
}
