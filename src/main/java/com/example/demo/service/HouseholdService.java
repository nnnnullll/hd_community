package com.example.demo.service;
import com.example.demo.enity.Community;
import com.example.demo.enity.Company;
import com.example.demo.enity.Household;
import com.example.demo.enity.LoginDashboard;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    CompanyMapper companyMapper;

    public Integer insertHousehold(Integer community, Integer building,String room_number, String password){
        return householdMapper.insertHousehold(community, building, room_number, password);
    } 

    // type=1 更新个人信息 type=2 修改密码
    public Integer updateHousehold(Integer number, String email, String phone,  Integer type, String password, String oldpassword){
        if(type==1){
            return householdMapper.updateHousehold(number, email, phone);
        }else{
            if(householdMapper.validateHouseholdByPassword(number, oldpassword)==1){
                return householdMapper.updateHouseholdPassword(number, password);
            }else{
                return 0;
            }
        }
        
    } 

    //查household 通过number(唯一一条记录)
    //查household 通过community number(多条记录)
    public Household[] getHousehold(Integer type,Integer number,Integer community,String company){
        switch(type){
            case 1: 
            Household[] household = new Household[1]; 
            household[0]= householdMapper.getHouseholdByNumber(number);
            return household;
            case 2:
            return householdMapper.getHouseholdByCommunity(community);
            case 3:
            return householdMapper.getHouseholdByCompany(company);
            default:
            return null;
        } 
    }

    //type=1 查employeedash
    public LoginDashboard getHouseholdDash(Integer number){
        LoginDashboard householdDashboard =new LoginDashboard();
        Household household= new Household();
        household = householdMapper.getHouseholdByNumber(number);
        householdDashboard.setLoginname(household.getBuilding()+"号楼"+household.getRoom_number()+"室");
        Community community = new Community();

        community = communityMapper.getCommunityByNumber(household.getCommunity());
        householdDashboard.setCommunitynumber(community.getNumber());
        householdDashboard.setLine1("所属社区：            " + community.getName());
        Company company = new Company();
        company = companyMapper.getCompanyByNumber(community.getCompany());
        householdDashboard.setCompanynumber(company.getNumber()); 
        householdDashboard.setLine2("所属物业：            " + company.getName());
        
        householdDashboard.setNewnumber(caseMapper.getNewCaseAmountByHousehold(number));
        Integer inprogressnumber = caseMapper.getInProgressCaseAmountByHousehold(number);
        householdDashboard.setInprogressnumber(inprogressnumber);
        householdDashboard.setEscalationnumber(caseMapper.getEscalationCaseAmountByHousehold(number));
        householdDashboard.setEmergencynumber(caseMapper.getEmergencyCaseAmountByHousehold(number));
        householdDashboard.setResolvednumber(caseMapper.getResoledCaseAmountByHousehold(number));
        householdDashboard.setClosednumber(caseMapper.getClosedCaseAmountByHousehold(number));
        householdDashboard.setAwaitnumber(caseMapper.getAwaitingInfoCaseAmountByHousehold(number));
        householdDashboard.setInfixnumber(caseMapper.getInFixCaseAmountByHousehold(number));
        
        Integer communityNumber = community.getNumber();
        String companyName = community.getName();
        householdDashboard.setStateChartTitle(companyName+"当前投诉单状态");
        Integer[] stateamount = new Integer[5];
        stateamount[0] = caseMapper.getNewCaseAmountByCommunity(communityNumber);
        stateamount[1] = caseMapper.getInProgressCaseAmountByCommunity(communityNumber) + caseMapper.getInFixCaseAmountByCommunity(communityNumber);
        stateamount[2] = caseMapper.getAwaitingInfoCaseAmountByCommunity(communityNumber);
        stateamount[3] = caseMapper.getEscalationCaseAmountByCommunity(communityNumber);
        stateamount[4] = caseMapper.getEmergencyCaseAmountByCommunity(communityNumber);
        householdDashboard.setStatechart(stateamount);
        String[] stateChartLabels = new String[] {"新建", "处理中", "待补充", "加急", "逾期"};
        householdDashboard.setStateChartLabels(stateChartLabels);

        householdDashboard.setTypeChartTitle(companyName+"历史投诉单种类");
        Integer[] typeamount = new Integer[5];
        typeamount[0] = caseMapper.getT1CaseAmountByCommunity(communityNumber);
        typeamount[1] = caseMapper.getT2CaseAmountByCommunity(communityNumber);
        typeamount[2] = caseMapper.getT3CaseAmountByCommunity(communityNumber);
        typeamount[3] = caseMapper.getT4CaseAmountByCommunity(communityNumber);
        typeamount[4] = caseMapper.getT5CaseAmountByCommunity(communityNumber);
        householdDashboard.setTypechart(typeamount);
        String[] typeChartLabels = new String[] {"水管", "电路", "绿化", "公共设施", "其他"};
        householdDashboard.setTypeChartLabels(typeChartLabels);
        return householdDashboard;
    }

}
