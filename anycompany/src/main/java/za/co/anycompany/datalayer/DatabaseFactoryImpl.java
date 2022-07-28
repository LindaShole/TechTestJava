package za.co.anycompany.datalayer;

import java.sql.*;

public class DatabaseFactoryImpl implements DatabaseFactory {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static DatabaseFactory database = null;

    private DatabaseFactoryImpl() {
    }

    private static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw e;
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Connection getConnection() throws  SQLException, ClassNotFoundException {
        try {
            return getDBConnection();
        }
        catch(Exception e) {
            throw e;
        }
    }

    public static DatabaseFactory getInstance() {
        if (database == null) {
            database = new DatabaseFactoryImpl();
        }

        return database;
    }
    
    public static CustomerDAO getCustomerDAO() {
        return new CustomerDAOImpl();
    }
    
    public static OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }
    
    public boolean schemaExist(String tableName) throws  SQLException, ClassNotFoundException {
        
        Connection dbConnection = null;
        ResultSet resultSet = null;

        try {
            dbConnection = getConnection();

            DatabaseMetaData dbm = dbConnection.getMetaData();
            
            // check if table exist
            resultSet = dbm.getTables(null, null, tableName, null);
    
            return resultSet.next();
        }
        catch(SQLException | ClassNotFoundException e) {
            throw e;
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (dbConnection != null)
                    dbConnection.close();
            }
            catch(SQLException e) {
                throw e;
            }
        }
    }

    public void createSchema(String createTableSql) throws  SQLException, ClassNotFoundException {
        Statement statement = null;
        Connection dbConnection = null;

        try {
            dbConnection = getConnection();
            
            statement = dbConnection.createStatement();

            statement.executeUpdate(createTableSql);
        }
        catch(SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
                
                if (dbConnection != null)
                    dbConnection.close();  
            }
            catch(SQLException e) {
                throw e;
            }     
        }
    }
}
