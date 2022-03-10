package com.example.demo.controller;

import com.example.demo.enity.Relationship;
import com.example.demo.service.RelationshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RelationshipController {
    @Autowired
    RelationshipService relationshipService;
    @PostMapping("/insertrelationship")
    public Integer insertRelationship(
    @RequestParam(value = "company") String company,
    @RequestParam(value = "partner") Integer partner,
    @RequestParam(value = "type") Integer type){
        return relationshipService.InsertRelationship(company, partner, type);
    } 

    @PostMapping("/getrelationshipbycompany")
    public Relationship[] getRelationshipByCompany(
    @RequestParam(value = "company") String company){
        return relationshipService.getRelationshipByCompany(company);
    }
    
    @PostMapping("/getrelationshipbycompanytype")
    public Relationship[] getRelationshipByCompanyType(
        @RequestParam(value = "company") String company,
        @RequestParam(value = "type") Integer type){
        return relationshipService.getRelationshipByCompanyType(company, type);
    }
    
}
