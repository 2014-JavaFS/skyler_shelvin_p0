import Expense.ExpenseController;
import Expense.ExpenseRepository;
import Expense.ExpenseService;
import io.javalin.Javalin;
import main.java.Account.AccountService;

public class MoneyTrackController {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        ExpenseRepository expenseRepository = new ExpenseRepository();
        ExpenseService expenseService = new ExpenseService(expenseRepository);
        ExpenseController expenseController = new ExpenseController(expenseService);


    }




}
