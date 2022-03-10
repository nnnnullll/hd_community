package com.example.demo.service;
import com.example.demo.enity.partner;
import com.example.demo.mapper.PartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    @Autowired
    PartnerMapper partnerMapper;
    public Integer insertPartner(String number, String name, String address, String phone, String email,String password){
        return partnerMapper.InsertPartner(number, name, address, phone, email, password);
    }

    public partner getPartnerByNum(Integer num){
        return partnerMapper.getPartnerByNum(num);
    }
} 
