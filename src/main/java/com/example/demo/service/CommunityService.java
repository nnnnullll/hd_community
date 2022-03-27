package com.example.demo.service;
import java.util.Arrays;
import java.util.List;

import com.example.demo.enity.Community;
import com.example.demo.enity.CommunityDetail;
import com.example.demo.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    BuildingMapper buildingMapper;
    @Autowired
    HouseholdMapper householdMapper;

    public Integer insertCommunity(String name,String region,String company,String buildings,String rooms){
        Community community = new Community();
        community.setName(name);
        community.setCompany(company);
        community.setRegion(region);
        communityMapper.insertCommunity(community);
        String str[] = rooms.split(",");
        List<String> room = Arrays.asList(str);
        String str1[] = buildings.split(",");
        List<String> building = Arrays.asList(str1);
        for(Integer i = 1;i<room.size(); i++){
            householdMapper.insertHousehold(community.getNumber(), Integer.parseInt(building.get(i)), room.get(i), community.getNumber()+building.get(i)+room.get(i));
        }
        return community.getNumber();
    }

    //type=1 查community 通过community number(唯一一条记录)
    //type=2 查communities 通过company number(多条记录)
    public Community[] getCommunity(Integer type,Integer number,String company){
        switch(type){
            case 1: 
            Community[] community = new Community[1];
            community[0] = communityMapper.getCommunityByNumber(number);
            return community;
            case 2:
            return communityMapper.getCommunitiesByCompany(company);
            default:
            return null;
        } 
    }

    //查community 通过community number(唯一一条记录)
    public CommunityDetail getCommunityDetail(Integer number){
        CommunityDetail communityDetail=new CommunityDetail();
        Community community = new Community();
        community = communityMapper.getCommunityByNumber(number);
        communityDetail.setNumber(community.getNumber());
        communityDetail.setName(community.getName());
        communityDetail.setCompany(community.getCompany());
        communityDetail.setRegion(community.getRegion());
        communityDetail.setActive(community.getActive());
        communityDetail.setBuilding_amount(householdMapper.getBuildingAmountByCommunity(number));
        communityDetail.setHousehold_amount(householdMapper.getHouseholdAmountByCommunity(number));
        return communityDetail;
    }
}
