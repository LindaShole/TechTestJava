package za.co.anycompany.datalayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseFactory {
    public Connection getConnection() throws  SQLException, ClassNotFoundException;
    public boolean schemaExist(String tableName) throws  SQLException, ClassNotFoundException;
    public void createSchema(String createTableSql) throws  SQLException, ClassNotFoundException;
}