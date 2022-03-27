package com.example.demo.controller;

import com.example.demo.enity.Community;
import com.example.demo.enity.CommunityDetail;
import com.example.demo.service.CommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CommunityController {
    @Autowired
    CommunityService communityService;
    //插community
    @PostMapping("/insertcommunity")
    public Integer insertCommunity(
        @RequestParam(value = "buildings") String buildings,
        @RequestParam(value = "rooms") String rooms,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "region") String region,
        @RequestParam(value = "company") String company){
        return communityService.insertCommunity(name, region, company,buildings,rooms);
    }

    //type=1 查community 通过community number(唯一一条记录)
    //type=2 查communities 通过company number(多条记录)
    @PostMapping("/getcommunity")
    public Community[] getCommunity(
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "company") String company){
        return communityService.getCommunity(type ,number, company);
    }

    //查community 通过community number(唯一一条记录) 
    @PostMapping("/getcommunitydetail")
    public CommunityDetail getCommunitySetail(
        @RequestParam(value = "number") Integer number){
        return communityService.getCommunityDetail(number);
    }

}
