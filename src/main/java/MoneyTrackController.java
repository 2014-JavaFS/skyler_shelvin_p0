import Account.AccountController;
import Account.AccountRepository;
import Account.AccountService;
import Expense.ExpenseController;
import Expense.ExpenseRepository;
import Expense.ExpenseService;
import io.javalin.Javalin;
//import main.java.Account.AccountService;


public class MoneyTrackController {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);

        //TODO implement controller methods and test
        //Account accountLoggedIn = 1;
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService);
        accountController.registerPaths(app);

        ExpenseRepository expenseRepository = new ExpenseRepository();
        ExpenseService expenseService = new ExpenseService(expenseRepository);
        ExpenseController expenseController = new ExpenseController(expenseService);
        expenseController.registerPaths(app);

        app.start(8080);

    }
}
