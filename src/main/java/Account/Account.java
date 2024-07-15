package main.java.Account;

public class Account {

    long accountId;

    String email;

    String password;

    // no args constructor
    public Account(long accountId, String email, String password){

    }

    //used for posting a new account, accountID auto generated in DB
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
//method overloading - polymorphism
    // when retrieving an account from the database, all fields will be needed.
    public Account(int accountId, String email, String password){
        this.accountId = accountId;
        this.password = password;
        this.email = email;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//validation of the correct account being represented
    //utilized inheritance from object class to determine if objects are equal
   @Override
   public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && email.equals(account.email)
                && password.equals(account.password);
   }
// String representation of this class
    @Override
    public String toString(){
        return "main.java.Account{" +
                "accountId=" + accountId +
                ", email'" + email + '\'' +
                ", password= " + password + '\'' +
                '}';
    }
}

