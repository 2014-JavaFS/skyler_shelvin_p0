package Account;

import Util.Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.function.Predicate;

public class AccountController implements Controller {

    private final AccountService accountService;

    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void registerPaths(Javalin app){
        app.post("/login",this::postLoginHandler);
        app.post("/register", this::registerHandler);
    }

    private void registerHandler(Context ctx) throws Exception{
        Account account = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.create(account);

        //check if account is already added
        if(account.getEmail().trim().isEmpty() || account.getEmail() == null){
            newAccount = null;
        }if(account.getPassword().length() < 4 || account.getPassword() == null){
            newAccount = null;
        }

        if(newAccount != null){
            ctx.status(200).json(newAccount);
        }else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx)throws Exception{
        Account account = ctx.bodyAsClass(Account.class);
        Account verifiedAccount = accountService.verifyAccount(account.email, account.password);

        if(verifiedAccount != null){
            ctx.status(200).json(verifiedAccount);
        } else {
            ctx.status(401);

        }

    }
}
