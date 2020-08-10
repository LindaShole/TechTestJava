package za.co.anycompany;

import java.io.*;
import java.util.List;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import za.co.anycompany.datalayer.*;
import za.co.anycompany.model.*;
import za.co.anycompany.service.*;
//import com.sun.media.sound.ModelAbstractChannelMixer;
public class Main {

    public static void main(String[] args) throws IOException{
//      System.out.println(name);
    	String selectedOption = "";
    	while (selectedOption != "X")
    	{
    		selectedOption = showMenu();
      		switch (selectedOption) {
      			case "1":
      				try {
      					placeNewOrder(selectCustomer().getId());
      				}catch (NullPointerException exp) {
      					System.out.println("Invalid Customer");
      				}
      				break;
      			case "2":
      				addNewCustomer();
      				break;
      			case "3":
      				showOrders(selectCustomerReport());
      				break;
      			case "X":
      				System.out.println("Exit");
      				return;
      			default:
      				System.out.println("Invalid Selecction");
      				break;
      		}
    	}
    }
    
    private static void showOrders(int customerId) {
    	
    	if (customerId == 0) {
    		try {
    			new Reports().getAllCustomersOrders();
    		}catch (IOException exp) {
    			
    		}
    		
    	}else  if (customerId != -1){
    		Reports reports = new Reports();
    		try {
    			reports.getCustomersOrders(new CustomerRepository().load(customerId));
    		}catch (IOException exp) {
    			
    		}
    		
    	}
    }
    
    private static boolean addNewCustomer() {
    	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter Customer Name");
    	String name = "";
    	try {
        	name =  reader.readLine();
        }catch (IOException exp) {
        	System.out.println("Error in name");
        	return false;
        }
    	String country = "";
    	System.out.println("Enter Country Code (2 Digit)");
    	try {
        	country =  reader.readLine().substring(0,2);
        }catch (IOException exp) {
        	System.out.println("Error in country");
        	return false;
        }
    	Date dateOfBirth= null;
    	System.out.println("Enter Date Of Birth (dd/mm/yyyy)");
    	try {
        	 dateOfBirth =  new SimpleDateFormat("dd/MM/yyyy").parse(reader.readLine());
        }catch (IOException exp) {
        	System.out.println("Error in date of birth");
        	return false;
        }
    	catch (Exception exp) {
    		System.out.println("Error in date of birth");
    		return false;
    	}
    	CustomerRepository CR = new CustomerRepository();
    	Customer customer = new Customer();
    	customer.setCountry(country);
    	customer.setName(name);
    	customer.setDateOfBirth(dateOfBirth);
    	return CR.addNewCustomer(customer);
    }
    private static void placeNewOrder(int customerId) {
    	if (customerId  >= 0) 
    	{
    		/*try {*/
			BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    			System.out.println("############## Placing New Order ###############" + customerId);
    			System.out.println("Enter Value of order");
    			double orderAmount = -1;
    			try {
    				orderAmount=  Double.parseDouble(reader.readLine());
    			}catch (IOException exp) {
    				System.out.println("Please enter a valid amount");
    				return;
    			}
    			Order order = new Order();
    			order.setAmount(orderAmount);
    			order.setCustomerId(customerId);
    			OrderService OS = new OrderService();
    			OS.placeOrder(order, customerId);
    			System.out.println();
    			System.out.println("Order Placed Successfully");
    			System.out.println();
    	}else {
    		System.out.println("Invalid Customer");
    	}
    	
    	
    }
    
    private static int  selectCustomerReport() 
    {
    	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    	CustomerRepository CR = new CustomerRepository();
    	List<Customer> allCustomer = CR.getAllCustomers();
    	int customerId = -1;
    	if (allCustomer.isEmpty()) {
    		System.out.println("No Customers");
    		return -1;
    	}

    	System.out.println("======================================");
    	System.out.println("======= Select Customer===============");
    	System.out.println("======================================");
    	System.out.println("A : All Customers");
    	for (Customer tempCustomer : allCustomer) {
    		System.out.println(tempCustomer.getId() + " : " + tempCustomer.getName()) ;
    	}
    	String response = "";
        try {
        	response= reader.readLine();
        	customerId = Integer.parseInt(response);
        	boolean validSelection = false;
        	for ( Customer tempCustomer : allCustomer) {
        		
        		if (tempCustomer.getId() == customerId) {
        			validSelection = true;
        		}
        	}
        	if (!validSelection) {
        		customerId = -1;
        	}
        }catch (IOException exp) {
        	response = null;
        }catch (NumberFormatException nExp) {
        	if (response.toUpperCase().equals("A")) {
        		return 0;
        	}else {
        		return -1;
        	}
        }
        if (response != null) {
        	
        }
        else {
        	System.out.println("Invalid Selection");
        	return -1;
        }
        return customerId;
    }
    
    private static Customer selectCustomer() 
    {
    	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    	CustomerRepository CR = new CustomerRepository();
    	List<Customer> allCustomer = CR.getAllCustomers();
    	int customerId = -1;
    	Customer customer = null;
    	if (allCustomer.isEmpty()) {
    		System.out.println("No Customers");
    		return null;
    	}

    	System.out.println("======================================");
    	System.out.println("======= Select Customer===============");
    	System.out.println("======================================");
    	for (Customer tempCustomer : allCustomer) {
    		System.out.println(tempCustomer.getId() + " : " + tempCustomer.getName()) ;
    	}
    	String response = "";
        try {
        	response= reader.readLine();
        	customerId = Integer.parseInt(response);
        	boolean validSelection = false;
        	for ( Customer tempCustomer : allCustomer) {
        		
        		if (tempCustomer.getId() == customerId) {
        			validSelection = true;
        			customer = tempCustomer;
        		}
        	}
        	if (!validSelection) {
        		customerId = -1;
        	}
        }catch (IOException exp) {
        	response = null;
        }
        if (response != null) {
        	
        }
        else {
        	System.out.println("Invalid Selection");
        	return null;
        }
        return customer;
    }
    
    private static String showMenu() {
      	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*************************************");
        System.out.println("***********SELECT OPTION*************");
        System.out.println("*************************************");
        System.out.println("  1) Place New Order");
        System.out.println("  2) Add New Customer");
        System.out.println("  3) Show Orders");
        System.out.println("  X) Exit");
        String response = "";
        try {
        	response= reader.readLine();
        }catch (IOException exp) {
        	response = "";
        }        	
        return response.toUpperCase();
    }
}
