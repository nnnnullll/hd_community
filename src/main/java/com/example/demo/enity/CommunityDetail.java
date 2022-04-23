package com.example.demo.enity;


public class CommunityDetail {
    Integer number;
    String name;
    String region;
    String company;
    Integer active;
    Integer building_amount;
    Integer household_amount;
    Household[] households;
    
    public Household[] getHouseholds() {
        return households;
    }
    public void setHouseholds(Household[] households) {
        this.households = households;
    }
    public Integer getBuilding_amount() {
        return building_amount;
    }
    public void setBuilding_amount(Integer building_amount) {
        this.building_amount = building_amount;
    }
    public Integer getHousehold_amount() {
        return household_amount;
    }
    public void setHousehold_amount(Integer household_amount) {
        this.household_amount = household_amount;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public Integer getActive() {
        return active;
    }
    public void setActive(Integer active) {
        this.active = active;
    }
    
}
