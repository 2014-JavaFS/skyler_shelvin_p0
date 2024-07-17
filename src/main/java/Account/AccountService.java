package Account;

import Account.Account;
import Util.DataNotFoundException;
import Util.InvalidInputException;
import Util.Serviceable;
import Account.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.DataFormatException;

public class AccountService implements Serviceable<Account> {

   // private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Account> findAll() {
       List<Account> accounts = accountRepository.findAll();
       if(accounts.isEmpty()){
           throw new DataNotFoundException("No account information available");
       } else {
           return accounts;
       }
    }

    @Override
    public Account findById(int number) {
        System.out.println("Account number was sent to service as {}"+ number);
        Account foundAccount = accountRepository.findById(number);
        System.out.println("The account was found and is {}"+ foundAccount);
        return foundAccount;
    }

    @Override
    public Account findByEmail(String email) {
        System.out.println("Account email was sent to service as {}"+ email);
        Account foundAccount = accountRepository.findByEmail(email);
        System.out.println("The account was found and is {}" + foundAccount);
        return foundAccount;
    }

    @Override
    public Account create(Account newAccount) throws InvalidInputException {
        return accountRepository.create(newAccount);
    }

    public Account verifyAccount(String email, String password) {
        return accountRepository.verifyAccount(email, password);
    }
}