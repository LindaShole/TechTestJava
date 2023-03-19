package za.co.anycompany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.anycompany.service.PageService;

@Controller
public class PageController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private PageService pageService;
    public PageController(PageService pageService){
        super();
        this.pageService=pageService;
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String loginUser(){
        //model.put("name", name);
        //logger.debug("Request param is {} ", name); // warn, info, debug m                            b
        //System.out.println("Request param is " + name);
        return "login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String welcomeUser(@RequestParam String name, @RequestParam String password, ModelMap model){

        if(pageService.authenticate(name, password)){
            model.put("name", name);
            model.put("password", password);
            logger.debug("Request param is {} ", name); // warn, info, debug m
            logger.debug("Request param is {} ", password); // warn, info, debug m
            return "index";
        }
                     //                        b
        //System.out.println("Request param is " + name);
        model.put("errorMessage", "Invalid Login Credentials!");
        return "login";
    }
}
