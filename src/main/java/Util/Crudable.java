package Util;

import Account.Account;
import Expense.Expense;

public interface Crudable<O> extends Serviceable<O>{
    boolean update(O updatedObject);
    boolean delete(Account account);

    boolean delete(Expense expense);
}