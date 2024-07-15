package main.java.Account;

public interface AccountService {
    public Account saveAccount(Account account);

    public Account getAccount(long accountId);

    public Account updateAccount(Account account);
    //none
}