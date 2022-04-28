package com.example.demo.service;
import com.example.demo.mapper.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    @Autowired
    RelationshipMapper relationshipMapper;
    public Integer changeRelationship(String company, Integer partner){
        if(relationshipMapper.getAmountByPartnerAndCompany(partner, company)>=1){
            if(relationshipMapper.getActiveByPartnerAndCompany(partner, company)==1){//解除合作
                return relationshipMapper.updateInActiveByPartnerAndCompany(partner, company);
            }else{//恢复合作
                return relationshipMapper.updateActiveByPartnerAndCompany(partner, company);
            }
        }else{//从未合作过 插入
            return relationshipMapper.InsertRelationship(company, partner);
        }
    }
}
