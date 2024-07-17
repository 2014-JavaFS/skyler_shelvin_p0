package Expense;

import Account.Account;
import Util.ConnectionFactory;
import Util.Crudable;
import Util.DataNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ExpenseRepository implements Crudable<Expense> {

    @Override
    public boolean delete(Expense expense) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "DELETE FROM expenses Where itemId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, expense.getItemId());
            boolean checkDelete = statement.executeUpdate() == 1;
            if(checkDelete){
                System.out.println("expense record was deleted.");
            } else{
                System.out.println("expense record was not deleted.");
            }
            return checkDelete;

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Expense> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Expense> expenses = new ArrayList<>();

            String sql = "SELECT * FROM expenses";
            ResultSet rs = connection.createStatement().getResultSet();

            while(rs.next()){
                expenses.add(generateExpenseFromResultSet(rs));
            }
            return expenses;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense create(Expense newObject) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "INSERT INTO expenses (userId, amount, category, date) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, newObject.getUserId());
            statement.setInt(2, newObject.getAmount());
            statement.setString(3, newObject.getCategory());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newObject.setItemId(resultSet.getInt("itemId"));
                newObject.setDate(resultSet.getTimestamp("date"));
                System.out.println("Expense created successfully: " + newObject);
                return newObject;
            } else {
                throw new SQLException("Creating expense failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Expense> findByCategory(int userId, String category) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            System.out.println("Expense category provided to the Repository by service is {}"+ category);
            String sql = "SELECT * FROM expenses WHERE category = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Information not found within database given category {}" + category);
                throw new DataNotFoundException("No expense within category" + category + "exists in our database.");
            }

            Expense foundExpense = generateExpenseFromResultSet(resultSet);
            System.out.println("Sending back expense information {}" + foundExpense);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Expense> findByDate(int userId, Timestamp start, Timestamp end) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "SELECT * FROM expenses WHERE userId = ? AND date BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setTimestamp(2, start);
            statement.setTimestamp(3,end);
            ResultSet resultSet = statement.executeQuery();

            List<Expense> expenses = new ArrayList<>();
            if (!resultSet.next()) {
                System.out.println("Information not found within database given date range {}" + start + ": " + end);
                throw new DataNotFoundException("No expense within range start" + start + "range end "+ end + "exists in our database.");
            }

           expenses.add(generateExpenseFromResultSet(resultSet));
            System.out.println("Sending back expense information {}" + expenses);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Expense generateExpenseFromResultSet(ResultSet rs) throws SQLException {
        Expense expense = new Expense();

        expense.setItemId(rs.getInt("itemId"));
        expense.setAmount(rs.getInt("amount"));
        expense.setCategory(rs.getString("category"));
        expense.setDate(rs.getTimestamp("date"));
        return expense;
    }




//    @Override
//    public Expense addExpense(Long itemID) {
//        Expense expense = null;
//        try(Connection connection = db.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO expenses (user_id, amount, category, date) Values (?, ?, ?, ?)");
//            statement.setLong(1, expense.getUserId());
//            statement.setInt(2, expense.getAmount());
//            statement.setString(3, expense.getCategory());
//            statement.setDate(4, Date.valueOf(expense.getDate()));
//            statement.executeUpdate();
//
//            //Retrieve Autogenerated ID
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                expense.setItemId(generatedKeys.getLong(1));
//            }
//        }catch (SQLException e){
//                throw new RuntimeException("Error saving account", e);
//        }
//            return expense;
//    }
//
//    @Override
//    public Expense viewAllExpense(Long userId) {
//        List<Expense> expenses = new ArrayList<>();
//        try(Connection connection = db.getconnection ){
//            PreparedStatement statement;
//            statement = connection.prepareStatement(
//                    "Select * from expenses WHERE user_id = ?");
//            statement.setLong(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                Expense expense = new Expense(
//                    resultSet.getInt("itemId"),
//                    resultSet.getInt("amount"),
//                    resultSet.getString("category"),
//                    resultSet.getTimestamp("date"));
//                    expenses.add(expense);
//            }
//        }catch(SQLException e){
//           throw new RuntimeException("unable to retrieve expenses");
//        }
//        return expenses;
//
//    }
}
