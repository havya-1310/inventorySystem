package com.example.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorFunController {

    @Autowired
    private VendorFun vendorFun ;

    @Autowired
    private ProductFun productFun ;

    @CrossOrigin
    @PostMapping("/inventory/vendor/signUp")
    public Vendor saveVendor(@RequestBody Vendor vendor){
        return vendorFun.saveVendor(vendor) ;
    }
    @CrossOrigin
    @GetMapping("/inventory/vendor")
    public List<Vendor> getAllVendors(){
        return vendorFun.getAllVendors() ;
    }

    @CrossOrigin
    @PostMapping("/inventory/vendor/login")
    public boolean checkVendor(@RequestBody Vendor vendor){
        vendor.setVendorName("") ;
        return vendorFun.checkVendor(vendor) ;
    }
    @CrossOrigin
    @GetMapping("/inventory/vendor/login")
    public List<Product> getAllProducts(@RequestParam(name = "vendorId") String vendorId){
        System.out.println(vendorId + " is printing here") ;
        return productFun.getAllProducts() ;

    }
   /*@CrossOrigin
    @GetMapping("/inventory/try")
    public Vendor getVendor(@RequestParam(name = "vendorId") String vendorId)
    {
        return vendorFun.vendorDetails(vendorId) ;
    }*/
}
