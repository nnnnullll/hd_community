package com.example.demo.controller;
import com.example.demo.enity.Building;
import com.example.demo.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    //插Building
    @PostMapping("/insertbuilding")
    public Integer insertBuilding(
        @RequestParam(value = "community") Integer community,
        @RequestParam(value = "building_number") Integer building_number,
        @RequestParam(value = "household_amount") Integer household_amount,
        @RequestParam(value = "height") Integer height){
        return buildingService.insertBuilding(community,building_number,height,household_amount);
    }

    //查building 通过community number 按照building_number排序
    @PostMapping("/getbuilding_bycommunity_orderbuilding_number")
    public Building[] getBuilding_ByCommunity_OrderBuilding_number(
        @RequestParam(value = "community") Integer community) {
        return buildingService.getBuilding_ByCommunity_OrderBuilding_number(community);
    }
}
