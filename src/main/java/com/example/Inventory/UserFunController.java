package com.example.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserFunController{

    @Autowired
    private UserFun userFun ;

    @CrossOrigin
    @PostMapping("/inventory/signUp")
    public User saveUser(@RequestBody User user){
        return userFun.saveUser(user) ;
    }

    @GetMapping("/inventory")
    public List<User> getAllUsers(HttpServletRequest req){
        return userFun.getAllUsers() ;
    }

    @CrossOrigin
    @PostMapping("/inventory/login")
    public boolean checkUser(@RequestBody User user,HttpServletRequest req){
        String user_id =(String) req.getSession().getAttribute("user_id") ;
        String pass = (String) req.getSession().getAttribute("password") ;
        if(user_id==null || pass==null)
        {
            req.getSession().setAttribute("user_id",user.getUser_id());
            req.getSession().setAttribute("password",user.getPassword());
            System.out.println("user is not logged in currently");
        }
        else
        {
            System.out.println(user_id + " " + pass);
        }
        user_id = (String)req.getSession().getAttribute("user_id");
        pass = (String)req.getSession().getAttribute("password");
        System.out.println(user_id + " user id and password " + pass + "is logged in");
        user.setName("") ;
        return userFun.checkUser(user) ;
    }
    @CrossOrigin
    @GetMapping("/inventory/logout")
    public void Logout(HttpServletRequest req)
    {
        req.getSession().removeAttribute("user_id");
        req.getSession().removeAttribute("password");
        System.out.println("logged out");
    }

}
