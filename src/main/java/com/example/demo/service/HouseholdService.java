package com.example.demo.service;
import com.example.demo.enity.Household;
import com.example.demo.mapper.HouseholdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    @Autowired
    HouseholdMapper householdMapper;

    public Integer insertHousehold(Integer community, Integer building,String room_number, String password){
        return householdMapper.insertHousehold(community, building, room_number, password);
    } 

    //查household 通过number(唯一一条记录)
    //查household 通过community number(多条记录)
    public Household[] getHousehold(Integer type,Integer number,Integer community){
        switch(type){
            case 1: 
            Household[] household = new Household[1]; 
            household[0]= householdMapper.getHouseholdByNumber(number);
            return household;
            case 2:
            return householdMapper.getHouseholdByCommunity(community);
            default:
            return null;
        } 
    }

}
