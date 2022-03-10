package com.example.demo.service;

import com.example.demo.enity.Relationship;
import com.example.demo.mapper.RelationshipMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    @Autowired
    RelationshipMapper relationshipMapper;
    public Integer InsertRelationship(String company, Integer partner, Integer type){
        return relationshipMapper.InsertRelationship(company, partner, type);
    }
    //查relationship 通过company
    public Relationship[] getRelationshipByCompany(String company){
        return relationshipMapper.getRelationshipByCompany(company);
    }
    //查relationship 通过company & type
    public Relationship[] getRelationshipByCompanyType(String company,Integer type){
        return relationshipMapper.getRelationshipByCompanyType(company, type);
    }
}
