package za.co.anycompany.validations;

import java.sql.Date;

public class Screen {

    public static boolean isValidString(String input)
    {
        return null != null && !input.trim().isEmpty();
    }

    public static boolean isValidInt(int input)
    {
        return input > 0;
    }

    public static boolean isValidDouble(double input)
    {
        return input > 0;
    }

    public  static  boolean isValidDate(Date date)
    {
        return null != date;
    }
}
