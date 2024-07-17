//package main.java.Account;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AccountJDBCService implements AccountService{
//
//   // TODO : Create a private final member variable for datasource.
//    //this will ensure the variable is assigned a value only once during obj. creation
//
//    // TODO: create a public constructor with parameter of datasource.
//
//    //logic for creating and saving account in database using jdbc
//    @Override
//    public Account saveAccount(Account account) {
//        try(Connection connection = db.getConnection()){
//            // Prepared statement to insert account data
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO accounts (email, password) Values (?, ?)");
//            statement.setString(1, account.getEmail());
//            statement.setString(2, account.getPassword());
//            statement.executeUpdate();
//
//            //Retrieve Autogenerated ID
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if(generatedKeys.next()) {
//                account.setAccountId(generatedKeys.getLong(1));
//            }
//        } catch (SQLException e){
//            throw new RuntimeException("Error saving account", e);
//        }
//        return account;
//    }
//
//    //logic for retrieving account
//    @Override
//    public Account getAccount(long accountId) {
//        Account account = null;
//        try(Connection connection = db.getConnection()){
//            PreparedStatement statement = connection.prepareStatement(
//                    "Select * From accounts WHERE id = ?");
//            statement.setLong(1, accountId);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                account = new Account(
//                        resultSet.getLong("accountId"),
//                        resultSet.getString("email"),
//                        resultSet.getString("password")
//                );
//            }
//        } catch (SQLException e){
//            throw new RuntimeException("Error Retrieving account", e);
//        }
//        return account;
//    }
//
//    //in the event we need to update the account
//
//    @Override
//    public Account updateAccount(Account account) {
//        try(Connection connection = db.connection()){
//            PreparedStatement statement = connection.prepareStatement(
//                    "Update accounts SET email = ?, password = ? WHERE id = ?");
//            statement.setString(1, account.getEmail());
//            statement.setString(2, account.getPassword());
//            statement.setLong(3, account.getAccountId());
//            statement.executeUpdate();
//        } catch (SQLException e){
//            throw new RuntimeException("Error parsing account", e);
//        }
//        return account;
//    }
//
//    /** Learned: annotations help ensure the methods are implemented correctly
//     * utilized prepared statements to prevent injection attacks
//     *
//     *
//     */
//
//}
