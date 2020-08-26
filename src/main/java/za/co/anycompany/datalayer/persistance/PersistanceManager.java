package za.co.anycompany.datalayer.persistance;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;	

	/**
	 * 
	 * @author Mandlha
	 *
	 created to manage all the static context for db persistance
	 */
	public  class PersistanceManager {
		protected static final String DB_DRIVER = "org.h2.Driver";
	    protected static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	    protected static final String DB_USER = "";
	    protected static final String DB_PASSWORD = "";
	    
	
	    public static Connection getDBConnection() {
	        Connection dbConnection = null;
	        try {
	            Class.forName(DB_DRIVER);
	            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
	        } catch (ClassNotFoundException| SQLException e) {
	            System.out.println(e.getMessage());
	        }
	      
	        return dbConnection;
	    }

}
