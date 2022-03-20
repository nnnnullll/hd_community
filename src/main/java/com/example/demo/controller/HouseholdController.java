package com.example.demo.controller;
import com.example.demo.enity.Household;
import com.example.demo.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class HouseholdController {

    @Autowired 
    HouseholdService householdService;
    //插household
    @PostMapping("/inserthousehold")
    public Integer inserthousehold(
        @RequestParam(value = "community") Integer community,
        @RequestParam(value = "building") Integer building,
        @RequestParam(value = "room_number") String room_number,
        @RequestParam(value = "password") String password){
        return householdService.insertHousehold(community, building, room_number, password);
    }
    
    //type=1 查household 通过number(唯一一条记录)
    //type=2 查household 通过community number(多条记录)
    //type=3 查household 通过company number(多条记录)
    @PostMapping("/gethouseholds")
    public Household[] getHousehold(
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "community") Integer community,
        @RequestParam(value = "company") String company){
        return householdService.getHousehold(type, number, community,company);
    }
}
