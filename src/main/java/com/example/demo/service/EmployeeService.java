package com.example.demo.service;
import com.example.demo.enity.Employee;
import com.example.demo.enity.LoginDashboard;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    CompanyMapper companyMapper;

    public Integer insertEmployee(String name,String company,String id,String phone,String email,Integer admin){
        if(employeeMapper.validateEmployeeByID(id,company)>=1){
            return 0;
        }else if(employeeMapper.validateEmployeeByPhone(phone,company)>=1){
            return 1;
        }else if(employeeMapper.validateEmployeeByEmail(email,company)>=1){
            return 2;
        }else{
            Employee employee = new Employee();
            employee.setAdmin(admin);
            employee.setCompany(company);
            employee.setEmail(email);
            employee.setId(id);
            employee.setPhone(phone);
            employee.setName(name);
            employeeMapper.InsertEmployee(employee);
            return employee.getNumber();
        }
    }

    // type=1 更新个人信息  type=2 active/inactive复职离职 type=3 admin给予权限/授予权限 type=4 更改密码 type=5 重置密码
    public Integer updateEmployee(Integer number,String phone,String email, Integer type, String password, String oldpassword){
        Employee employeet = new Employee();
        employeet = employeeMapper.getEmployeeByNumber(number);
        if( type==1 ){
            if(employeeMapper.validateNoOtherEmployeeByPhone(number,phone,employeet.getCompany())>=1){
                return 0;
            }else if(employeeMapper.validateNoOtherEmployeeByEmail(number,email,employeet.getCompany())>=1){
                return 2;
            }else{
                Employee employee = new Employee();
                employee.setNumber(number);
                employee.setEmail(email);
                employee.setPhone(phone);
                return employeeMapper.updateEmployee(employee);
            }
        }else if( type==2 ){
            if(employeeMapper.getEmployeeActiveByNumber(number)==1){
                return employeeMapper.updateEmployeeActive(number);
            }else{
                return employeeMapper.updateEmployeeInActive(number);
            }
        }else if(type==3){
            if(employeeMapper.getEmployeeAdminByNumber(number)==0){
                return employeeMapper.updateEmployeeAdmin(number);
            }else{
                return employeeMapper.updateEmployeeInAdmin(number);
            }
        }else if(type==4){
            if(employeeMapper.validateEmployeeByPassword(number, oldpassword)==1){
                return employeeMapper.updateEmployeePassword(number,password);
            }else{
                return 0;
            }
        }else{
            return employeeMapper.updateEmployeeResetPassword(number);
        }   
    }
    

    //type=1 查employee 通过number(唯一一条记录)
    //type=2 查employee 通过company number(多条记录)
    public Employee[] getEmployee(Integer type,Integer number,String company){
        switch(type){
            case 1: 
            Employee[] employee = new Employee[1];
            employee[0] = employeeMapper.getEmployeeByNumber(number);
            return employee;
            case 2:
            return employeeMapper.getEmployeeByCompany(company);
            default:
            return null;
        } 
    }

    //type=1 查employeedash
    public LoginDashboard getEmployeeDash(Integer number){
        LoginDashboard employedashboard =new LoginDashboard();
        Employee employee = new Employee();
        employee = employeeMapper.getEmployeeByNumber(number);
        String companyNumber = employee.getCompany();
        employedashboard.setCompanynumber(companyNumber);
        String companyName = companyMapper.getCompanyByNumber(companyNumber).getName();
        employedashboard.setLoginname(employee.getName());
        employedashboard.setLine1("所属物业公司：             "+ companyName);
        employedashboard.setLine2("是否为超级管理员:             " + (employee.getAdmin()==0?"否":"是"));
        employedashboard.setIfadmin(employee.getAdmin());

        employedashboard.setNewnumber(caseMapper.getNewCaseAmountByCompanyNumber(companyNumber));
        Integer inprogressnumber = caseMapper.getInProgressCaseAmountByAssignedTo(number);
        employedashboard.setInprogressnumber(inprogressnumber);
        employedashboard.setInfixnumber(caseMapper.getInFixCaseAmountByAssignedTo(number));
        employedashboard.setEscalationnumber(caseMapper.getEscalationCaseAmountByAssignedTo(number));
        employedashboard.setEmergencynumber(caseMapper.getEmergencyCaseAmountByAssignedTo(number));
        employedashboard.setResolvednumber(caseMapper.getResoledCaseAmountByAssignedTo(number));

        employedashboard.setStateChartTitle(companyName+"当前投诉单状态");
        Integer[] stateamount = new Integer[5];
        stateamount[0] = caseMapper.getNewCaseAmountByCompanyNumber(companyNumber);
        stateamount[1] = caseMapper.getInProgressCaseAmountByCompanyNumber(companyNumber) + caseMapper.getInFixCaseAmountByCompanyNumber(companyNumber);
        stateamount[2] = caseMapper.getAwaitingInfoCaseAmountByCompanyNumber(companyNumber);
        stateamount[3] = caseMapper.getEscalationCaseAmountByCompanyNumber(companyNumber);
        stateamount[4] = caseMapper.getEmergencyCaseAmountByCompanyNumber(companyNumber);
        employedashboard.setStatechart(stateamount);
        String[] stateChartLabels = new String[] {"新建", "处理中", "待补充", "加急", "逾期"};
        employedashboard.setStateChartLabels(stateChartLabels);

        employedashboard.setTypeChartTitle(companyName+"历史投诉单种类");
        Integer[] typeamount = new Integer[5];
        typeamount[0] = caseMapper.getT1CaseAmountByCompanyNumber(companyNumber);
        typeamount[1] = caseMapper.getT2CaseAmountByCompanyNumber(companyNumber);
        typeamount[2] = caseMapper.getT3CaseAmountByCompanyNumber(companyNumber);
        typeamount[3] = caseMapper.getT4CaseAmountByCompanyNumber(companyNumber);
        typeamount[4] = caseMapper.getT5CaseAmountByCompanyNumber(companyNumber);
        employedashboard.setTypechart(typeamount);
        String[] typeChartLabels = new String[] {"水管", "电路", "绿化", "公共设施", "其他"};
        employedashboard.setTypeChartLabels(typeChartLabels);
        return employedashboard;
    }
}
 