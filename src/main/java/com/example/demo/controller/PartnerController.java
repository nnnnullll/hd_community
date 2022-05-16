package com.example.demo.controller;
import com.example.demo.enity.partner;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class PartnerController {
    @Autowired
    PartnerService partnerService;
    @PostMapping("/insertpartner")
    public Integer insertPartner(
    @RequestParam(value = "name") String name,
    @RequestParam(value = "address") String address,
    @RequestParam(value = "phone") String phone,
    @RequestParam(value = "email") String email,
    @RequestParam(value = "description") String description,
    @RequestParam(value = "one") Integer one,
    @RequestParam(value = "two") Integer two,
    @RequestParam(value = "three") Integer three,
    @RequestParam(value = "four") Integer four,
    @RequestParam(value = "five") Integer five){
        return partnerService.insertPartner(name, address, phone, email, description, one, two, three, four, five);
    } 

    @PostMapping("/updatepartner")
    public Integer updatePartner(
    @RequestParam(value = "num") String num,
    @RequestParam(value = "address") String address,
    @RequestParam(value = "phone") String phone,
    @RequestParam(value = "email") String email,
    @RequestParam(value = "description") String description,
    @RequestParam(value = "one") Integer one,
    @RequestParam(value = "two") Integer two,
    @RequestParam(value = "three") Integer three,
    @RequestParam(value = "four") Integer four,
    @RequestParam(value = "five") Integer five,
    @RequestParam(value = "active") Integer active,
    @RequestParam(value = "type") Integer type,
    @RequestParam(value = "password") String password,
    @RequestParam(value = "oldpassword") String oldpassword){
        return partnerService.updatePartner(num, address, phone, email, description, one, two, three, four, five, type, password, oldpassword,active);
    } 

    @PostMapping("/getpartnerbynum")
    public partner getPartnerByNum(
    @RequestParam(value = "num") Integer num,
    @RequestParam(value = "company") String company){
        return partnerService.getPartnerByNum(num,company);
    } 

     //type==1 company通过relationship查物业公司合作的维修公司们
     @PostMapping("/getpartners") 
     public partner[] getPartners(
         @RequestParam(value = "company") String company,
         @RequestParam(value = "type") Integer type){
         return partnerService.getPartners(company, type);
     }
}
