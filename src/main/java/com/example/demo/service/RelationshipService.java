package com.example.demo.service;
import com.example.demo.mapper.CaseMapper;
import com.example.demo.mapper.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    @Autowired
    RelationshipMapper relationshipMapper;
    @Autowired
    CaseMapper caseMapper;
    public Integer changeRelationship(String company, Integer partner){
        if(relationshipMapper.getAmountByPartnerAndCompany(partner, company)>=1){//合作表里有
            if(relationshipMapper.getActiveByPartnerAndCompany(partner, company)==1){//在合作 解除合作
                if(caseMapper.getCaseAmountByPartner(partner,company)==0){//名下是否有处理中的case
                    return relationshipMapper.updateInActiveByPartnerAndCompany(partner, company);//解除合作
                }    
                else{
                    return 0; //有的话，处理完才能解除合作
                }     
            }else{//恢复合作
                return relationshipMapper.updateActiveByPartnerAndCompany(partner, company);
            }
        }else{//从未合作过 插入
            return relationshipMapper.InsertRelationship(company, partner);
        }
    }
}
