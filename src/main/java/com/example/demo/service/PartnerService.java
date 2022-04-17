package com.example.demo.service;
import com.example.demo.enity.LoginDashboard;
import com.example.demo.enity.partner;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    @Autowired
    PartnerMapper partnerMapper;
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    RelationshipMapper relationshipMapper;
    public Integer insertPartner( String name, String address, String phone, String email, String description, Integer one, Integer two, Integer three, Integer four, Integer five){
        if(partnerMapper.getPartnerAmountByPhone(phone)>=1 || partnerMapper.getPartnerAmountByName(name)>=1){
            return 0;
        }else{
            partner partner = new partner();
            partner.setName(name);
            partner.setEmail(email);
            partner.setPhone(phone);
            partner.setAddress(address);
            partner.setPassword(phone);
            partner.setDescription(description);
            partner.setOne(one);
            partner.setTwo(two);
            partner.setThree(three);
            partner.setFour(four);
            partner.setFive(five);
            partnerMapper.InsertPartner(partner);
            return partner.getNum();
        }
    }

    public Integer updatePartner( String num, String address, String phone, String email, String description, Integer one, Integer two, Integer three, Integer four, Integer five){
        if(partnerMapper.getPartnerAmountByPhone(phone)>=2){
            return 0;
        }else{
            partner partner = new partner();
            partner.setNum(Integer.valueOf(num));;
            partner.setEmail(email);
            partner.setPhone(phone);
            partner.setAddress(address);
            partner.setPassword(phone);
            partner.setDescription(description);
            partner.setOne(one);
            partner.setTwo(two);
            partner.setThree(three);
            partner.setFour(four);
            partner.setFive(five);
            return partnerMapper.updatePartner(partner);
        }
    }

    public partner getPartnerByNum(Integer num){
        return partnerMapper.getPartnerByNum(num);
    }
    //type=1 查company的合作维修公司
    //type=2 所有active的维修公司
    public partner[] getPartners(String company, Integer type){
        if( type == 1 ){
            Integer amount = relationshipMapper.getPartnerAmountFromRelationshipByPartner(company);
            Integer[] partnersnumber =new Integer[amount];
            partnersnumber = relationshipMapper.getPartnerNumberFromRelationshipByPartner(company);
            partner[] partners = new partner[amount];
            for(Integer i=0;i<amount;i++){
                partners[i]=partnerMapper.getPartnerByNum(partnersnumber[i]);
                Integer[] relationshiptype = new Integer[5];
                relationshiptype = relationshipMapper.getRelationshipTypeByPartnerAndCompany(partnersnumber[i],company);
                String typeStr = "";
                for(int j=0;j<relationshiptype.length;j++){
                    String t= "";
                    if(relationshiptype[j]==0){
                        t="水管 ";
                    } else if(relationshiptype[j]==1){
                        t="电路 ";
                    }else if(relationshiptype[j]==2){
                        t="绿化 ";
                    }else if(relationshiptype[j]==3){
                        t="公共设施 ";
                    }else{
                        t="其他 ";
                    }
                    typeStr = typeStr + t;
                }
                partners[i].setDescription("合作类别：" + typeStr);
            }
            return partners;
        } else if( type == 2 ){
            return partnerMapper.getAllPartner();
        }else{
            partner[] partners = new partner[0];
            return partners;
        }
    }
     //查Logindash
     public LoginDashboard getPartnerDash(Integer number){
        LoginDashboard partnerDashboard =new LoginDashboard();
        partner partner= new partner();
        partner = partnerMapper.getPartnerByNum(number);
        partnerDashboard.setLoginname(partner.getName());
        partnerDashboard.setLine1("维修公司：            " + partner.getName());
        Integer[] relationshiptype = new Integer[5];
        relationshiptype = relationshipMapper.getRelationshipTypeByPartner(number);
        String type = "";
        for(int i=0;i<relationshiptype.length;i++){
            String t= "";
            if(relationshiptype[i]==0){
                t="水管 ";
            } else if(relationshiptype[i]==1){
                t="电路 ";
            }else if(relationshiptype[i]==2){
                t="绿化 ";
            }else if(relationshiptype[i]==3){
                t="公共设施 ";
            }else{
                t="其他 ";
            }
            type = type + t;
        }
        partnerDashboard.setLine2("负责类别：            " + type);
        
        partnerDashboard.setFixwaite_number(caseMapper.getFixwaite_numberCaseAmountByPartner(number));
        partnerDashboard.setFixassigned_number(caseMapper.getFixassigned_numberCaseAmountByPartner(number));
        partnerDashboard.setInfix_number(caseMapper.getInfix_numberCaseAmountByPartner(number));
        partnerDashboard.setFixfinish_number(caseMapper.getFixfinish_numberCaseAmountByPartner(number));
        partnerDashboard.setFixclose_number(caseMapper.getFixclose_numberCaseAmountByPartner(number));
        
        Integer partnerNumber = partner.getNum();
        String partnerName = partner.getName();
        partnerDashboard.setStateChartTitle(partnerName+"当前投诉单状态");
        Integer[] stateamount = new Integer[5];
        stateamount[0] = partnerDashboard.getFixwaite_number();
        stateamount[1] = partnerDashboard.getFixassigned_number();
        stateamount[2] = partnerDashboard.getInfix_number();
        stateamount[3] = partnerDashboard.getFixfinish_number();
        stateamount[4] = partnerDashboard.getFixclose_number();
        partnerDashboard.setStatechart(stateamount);
        String[] stateChartLabels = new String[] {"待分配", "已分配", "维修中", "已解决", "已关闭"};
        partnerDashboard.setStateChartLabels(stateChartLabels);

        partnerDashboard.setTypeChartTitle(partnerName+"历史投诉单种类");
        Integer[] typeamount = new Integer[5];
        typeamount[0] = caseMapper.getT1CaseAmountByPartner(partnerNumber);
        typeamount[1] = caseMapper.getT2CaseAmountByPartner(partnerNumber);
        typeamount[2] = caseMapper.getT3CaseAmountByPartner(partnerNumber);
        typeamount[3] = caseMapper.getT4CaseAmountByPartner(partnerNumber);
        typeamount[4] = caseMapper.getT5CaseAmountByPartner(partnerNumber);
        partnerDashboard.setTypechart(typeamount);
        String[] typeChartLabels = new String[] {"水管", "电路", "绿化", "公共设施", "其他"};
        partnerDashboard.setTypeChartLabels(typeChartLabels);
        return partnerDashboard;
    }

} 
