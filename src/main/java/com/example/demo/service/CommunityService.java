package com.example.demo.service;
import com.example.demo.enity.Community;
import com.example.demo.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;

    public Integer insertCommunity(String name,String region,String company){
        return communityMapper.insertCommunity(name,region,company);
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
}
