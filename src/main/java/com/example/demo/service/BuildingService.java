package com.example.demo.service;
import com.example.demo.enity.Building;
import com.example.demo.mapper.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    BuildingMapper buildingMapper;

    //插入building
    public Integer insertBuilding(Integer community,Integer building_number,Integer height,Integer household_amount){
        return buildingMapper.insertBuilding(community,building_number,height,household_amount);
    }

    //查building 通过community number 按照building_number排序
    public Building[] getBuilding_ByCommunity_OrderBuilding_number(Integer community){
        return buildingMapper.getBuilding_ByCommunity_OrderBuilding_number(community);
    }
}