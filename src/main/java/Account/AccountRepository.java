package Account;


import Util.ConnectionFactory;
import Util.Crudable;
import Util.DataNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {


    public boolean update(Account updatedAccount) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "UPDATE accounts SET email = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, updatedAccount.getEmail());
            statement.setString(2, updatedAccount.getPassword());
            statement.setLong(3, updatedAccount.getAccountId());
            int checkUpdate = statement.executeUpdate();
            System.out.println("Updating information......");
            if(checkUpdate == 0){
                throw new RuntimeException("Flight record was not updated.");
            }
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean delete(Account account) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "DELETE FROM accounts Where accountId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, account.getAccountId());
            boolean checkDelete = statement.executeUpdate() == 1;
            if(checkDelete){
                System.out.println("Account record was deleted.");
            } else{
                System.out.println("Account record was not deleted.");
            }
            return checkDelete;

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public List<Account> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Account> accounts = new ArrayList<>();

            String sql = "SELECT * FROM accounts";
            ResultSet rs = connection.createStatement().getResultSet();

            while(rs.next()){
                accounts.add(generateAccountFromResultSet(rs));
            }
            return accounts;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public Account create(Account newAccount) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "INSERT INTO accounts (email, password) Values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, newAccount.getEmail());
            statement.setString(2, newAccount.getPassword());

            System.out.println(statement.toString());
            int checkInsert = statement.executeUpdate();
            if (checkInsert == 0) {

                throw new RuntimeException("Account was not inserted into the database");
            }
            return newAccount;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    public Account findById(int number) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            System.out.println("Account number provided to the Repository by service is {}"+ number);
            String sql = "Select * from accounts WHERE accountId = ? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Information not found within database given account number {}" + number);
                throw new DataNotFoundException("No Account with id" + number + "exists in our database.");
            }

            Account foundAccount = generateAccountFromResultSet(resultSet);
            System.out.println("Sending back account information {}" + foundAccount);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Account generateAccountFromResultSet(ResultSet rs) throws SQLException {
        Account account = new Account();

        account.setAccountId(rs.getInt("accountId"));
        account.setEmail(rs.getString("email"));
        account.setPassword(rs.getString("password"));
        return account;
    }

    public Account findByEmail(String email){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            System.out.println("Account email provided to the Repository by service is {}"+ email);
            String sql = "SELECT * FROM account WHERE email = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Information not found withing database given account email {}" + email);
                throw new DataNotFoundException("No Account with id" + email + "exists in our database.");
            }

            Account foundAccount = generateAccountFromResultSet(resultSet);
            System.out.println("Sending back Account information {}" + foundAccount);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Account verifyAccount(String email, String password){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "SELECT * FROM accounts WHERE email = ? AND password = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            System.out.println("Executing query: " + statement.toString());

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No account found with the provided email and password.");
                System.out.println("No account with the provided email and password exists in our database.");
            }

            Account verifiedAccount = generateAccountFromResultSet(resultSet);
            System.out.println("Account verified: " + verifiedAccount);

            return verifiedAccount;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
