package com.example.demo.service;
import java.util.Arrays;
import java.util.List;

import com.example.demo.enity.Community;
import com.example.demo.enity.CommunityDetail;
import com.example.demo.enity.Household;
import com.example.demo.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    CaseMapper caseMapper;
    public Integer insertCommunity(String name,String region,String address,String company,String buildings,String rooms){

        Community community = new Community();
        community.setName(name);
        community.setCompany(company);
        community.setRegion(region);
        community.setAddress(address);
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
            case 3:
            return communityMapper.getCommunityAll();
            default:
            return null;
        } 
    }

    // type=1 解除合作 type=2 添加合作
    public Integer updateCommunity(Integer type,Integer number,String company){
        if(type==1){
            if(communityMapper.validateActiveCommunityCompanyByNumber(number, company)==1){
                if(caseMapper.getCaseAmountByCommunity(number)==0){
                    communityMapper.updateCommunityRemoveCompanyByNumber(number);
                    return 1;
                }else{
                    return 2;//名下还有投诉单 解决完才能解除合作
                }
            }else{
                return 0;//company不符 人家合作物业不是你
            }
        }else if(type==2){
            if(communityMapper.validateInactiveCommunityByNumber(number)==1){//有没有合作物业
                return communityMapper.updateCommunityAddCompanyByNumber(company,number);
            }else{
                return 0;// company不是空的 已经有合作物业了
            }
        }else{
            return 0;
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
        communityDetail.setAddress(community.getAddress());
        communityDetail.setActive(community.getActive());
        communityDetail.setBuilding_amount(householdMapper.getBuildingAmountByCommunity(number));
        communityDetail.setHousehold_amount(householdMapper.getHouseholdAmountByCommunity(number));
        Household[] households = new Household[householdMapper.getHouseholdAmountByCommunity(number)];
        households = householdMapper.getHouseholdByCommunity(number);
        communityDetail.setHouseholds(households);
        return communityDetail;
    }
}
