package za.co.anycompany.datalayer.persistance;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import za.co.anycompany.model.Customer;

/**
 * 
 * @author Mandlha
 *
 *created to be an abstract class that has all management for persistence.
 *will act as the persistent repository for the persistence 
 *only for the sake of this  test example  , in relevant frameworks we would be using framework built managers.
 *all common persistence behavior should be in this class
 */
public  class PersistanceRepository {

    public static ResultSet get (PreparedStatement prpstmt, Object... param) {
        ResultSet resultSet = null;
        try {
        	for (int i = 0 ; i<param.length ;i++) {
        		if ((param [i] instanceof BigDecimal ) ) 
        			prpstmt.setBigDecimal(i+1, (BigDecimal) param[i]);
        		else if(param [i] instanceof Integer ) {
        			prpstmt.setInt(i+1, (Integer) param[i]);
        		}else if(param [i] instanceof Date ) {
        			prpstmt.setDate(i+1,new java.sql.Date(((Date)param[i]).getTime()) );
        		}else {
        			prpstmt.setString(i+1,  param[i].toString());		
        		}
        	}
            resultSet = prpstmt.executeQuery();     
        } catch (SQLException | ClassCastException e) 
        {
           e.printStackTrace();
        }
        return resultSet;
    }
    
    public static int save (PreparedStatement prpstmt, Object... param) {
        try {
        	for (int i = 0 ; i<param.length ;i++) {
        		if ((param [i] instanceof BigDecimal ) ) 
        			prpstmt.setBigDecimal(i+1, (BigDecimal) param[i]);
        		else if(param [i] instanceof Integer ) {
        			prpstmt.setInt(i+1, (Integer) param[i]);	
        		}else  if(param [i] instanceof Date ) {
        			prpstmt.setDate(i+1,new java.sql.Date(((Date)param[i]).getTime()) );
        		}else{
        			prpstmt.setString(i+1,  param[i].toString());		
        		}
        	}
        	prpstmt.executeUpdate();
    
        } catch (SQLException | ClassCastException e) 
        {
           e.printStackTrace();
        }
        return 1;
    }
    
    
    

}
