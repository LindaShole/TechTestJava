package za.co.anycompany.service;

import org.springframework.stereotype.Service;

@Service
public class PageService {
    public boolean authenticate(String username, String password){
        boolean validUser = username.equalsIgnoreCase("xolisani");
        boolean validPassword = password.equalsIgnoreCase("xolisani");

        return validUser && validPassword;
    }
}
