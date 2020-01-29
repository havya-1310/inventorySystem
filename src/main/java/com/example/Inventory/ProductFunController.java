package com.example.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductFunController {

    @Autowired
    private ProductFun productFun ;

    @PostMapping("/inventory/product")
    public void saveProduct(@RequestBody Product product){

        productFun.saveProduct(product) ;
    }

    @GetMapping("/inventory/product")
    public List<Product> getAllProducts(HttpServletRequest req){
        String uid = (String) req.getSession().getAttribute("user_id") ;
        String pass = (String) req.getSession().getAttribute("password") ;
        System.out.println("In product: " + uid + " and  " + pass);
        return productFun.getAllProducts() ;
    }
}
