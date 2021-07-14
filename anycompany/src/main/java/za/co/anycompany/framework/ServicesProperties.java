package main.java.za.co.anycompany.framework;

public class ServicesProperties {

    public static String getDbDriver() {
        return DB_DRIVER;
    }

    public static String getDbConnection() {
        return DB_CONNECTION;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getCOUNTRY() {
        return COUNTRY;
    }

    private static final String COUNTRY = "UK";

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


}
