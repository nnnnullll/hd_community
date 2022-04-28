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
    @PostMapping("/changerelationship")
    public Integer insertRelationship(
    @RequestParam(value = "company") String company,
    @RequestParam(value = "partner") Integer partner){
        return relationshipService.changeRelationship(company, partner);
    } 
}
