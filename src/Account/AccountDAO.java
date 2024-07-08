package Account;

public class AccountDAO {


    private final AccountService accountService;

    //utilizing dependency injection to take in service interface and interact with database
    public AccountDAO(AccountService accountService){
        this.accountService = accountService;
    }

    public Account saveAccount(Account account){
        this.accountService.saveAccount(account);
    }

    public Account getAccount(long accountId){
        return accountService.getAccount(accountId);
    }

    public Account updateAccount(Account account){
        return accountService.updateAccount(account);
    }




    /** learned: injecting the service interface promotes
     * loose coupling which enhances easier testing;
     * Delegating the actual work to the service interface ..
     */

}
