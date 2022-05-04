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
        if(partnerMapper.getPartnerAmountByName(name)>=1){
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

    //type=1改信息 2-改密码 3-改active 4-重置密码
    public Integer updatePartner( String num, String address, String phone, String email, String description, Integer one, Integer two, Integer three, Integer four, Integer five, Integer type, String password, String oldpassword,Integer active){
        if(type==1){
            if(partnerMapper.getPartnerAmountByPhone(phone)>=2){
                return 0;
            }else{
                partner partner = new partner();
                partner.setNum(Integer.valueOf(num));;
                partner.setEmail(email);
                partner.setPhone(phone);
                partner.setAddress(address);
                partner.setDescription(description);
                partner.setOne(one);
                partner.setTwo(two);
                partner.setThree(three);
                partner.setFour(four);
                partner.setFive(five);
                return partnerMapper.updatePartner(partner);
            }
        }else if(type==2){
            if(partnerMapper.validatePartnerByPassword(Integer.valueOf(num), oldpassword)==1){
                partner partner = new partner();
                partner.setNum(Integer.valueOf(num));;
                partner.setPassword(password);
                return partnerMapper.updatePartnerPassword(partner);
            }else{
                return 0;
            }
        }else if(type==3){
            partner partner = new partner();
            partner.setNum(Integer.valueOf(num));;
            partner.setActive(active);
            if(relationshipMapper.updateInActiveByPartner(Integer.valueOf(num))==1){
                return partnerMapper.updatePartnerActive(partner);
            }else{
                return 0;
            }
        }else{
            return partnerMapper.updatePartnerResetPassword(Integer.valueOf(num));
        }
        
    }

    public partner getPartnerByNum(Integer num, String company){
        partner partner = new partner();
        partner = partnerMapper.getPartnerByNum(num);
        if(company!="0"){
            if(relationshipMapper.getActiveByPartnerAndCompany(partner.getNum(), company)==1){
                partner.setIspartner(0);
            }else{
                partner.setIspartner(1);
            }
        }
        return partner;
    }
    //type=1 查company的合作维修公司
    //type=2 所有维修公司
    public partner[] getPartners(String company, Integer type){
        if( type == 1 ){
            Integer amount = relationshipMapper.getPartnerAmountFromRelationshipByCompany(company);
            Integer[] partnersnumber =new Integer[amount];
            partnersnumber = relationshipMapper.getPartnerNumberFromRelationshipByCompany(company);
            partner[] partners = new partner[amount];
            for(Integer i=0;i<amount;i++){
                partners[i]=partnerMapper.getPartnerByNum(partnersnumber[i]);
            }
            return partners;
        } else if( type == 2 ){
            Integer amount = partnerMapper.getNonePartnerAmount(company);
            partner[] partners = new partner[amount];
            partners = partnerMapper.getNonePartner(company);
            for(Integer k=0;k<amount;k++){
                // 0-是合作 1-不是合作
                if(relationshipMapper.getActiveByPartnerAndCompany(partners[k].getNum(), company)==1){
                    partners[k].setIspartner(0);
                }else{
                    partners[k].setIspartner(1);
                }
            }
            return partners;
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
        partnerDashboard.setLine2("负责类别：            " + (partner.getOne()==1?"水管":" ")+(partner.getTwo()==1?"电路":" ")+(partner.getThree()==1?"绿化":" ")+(partner.getFour()==1?"公共设施":" ")+(partner.getFive()==1?"其他":" "));
        
        partnerDashboard.setFixwaite_number(caseMapper.getFixwaite_numberCaseAmountByPartner(number));
        partnerDashboard.setFixassigned_number(caseMapper.getFixassigned_numberCaseAmountByPartner(number));
        partnerDashboard.setInfix_number(caseMapper.getInfix_numberCaseAmountByPartner(number));
        partnerDashboard.setFixfinish_number(caseMapper.getFixfinish_numberCaseAmountByPartner(number));
        partnerDashboard.setFixclose_number(caseMapper.getFixclose_numberCaseAmountByPartner(number));
        partnerDashboard.setEmergencynumber(caseMapper.getFixEmergency_numberCaseAmountByPartner(number));
        partnerDashboard.setEscalationnumber(caseMapper.getFixEscalation_numberCaseAmountByPartner(number));
        
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
        typeamount[0] = caseMapper.getT1CaseAmountByPartner(number);
        typeamount[1] = caseMapper.getT2CaseAmountByPartner(number);
        typeamount[2] = caseMapper.getT3CaseAmountByPartner(number);
        typeamount[3] = caseMapper.getT4CaseAmountByPartner(number);
        typeamount[4] = caseMapper.getT5CaseAmountByPartner(number);
        System.out.println(typeamount[0]);
        System.out.println(typeamount[1]);
        System.out.println(typeamount[2]);
        System.out.println(typeamount[3]);
        System.out.println(typeamount[4]);
        partnerDashboard.setTypechart(typeamount);
        String[] typeChartLabels = new String[] {"水管", "电路", "绿化", "公共设施", "其他"};
        partnerDashboard.setTypeChartLabels(typeChartLabels);
        return partnerDashboard;
    }

} 
