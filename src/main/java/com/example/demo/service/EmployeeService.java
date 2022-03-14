package com.example.demo.service;
import com.example.demo.enity.Employee;
import com.example.demo.enity.employedashboard;
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
    public Integer insertEmployee(String name,String company,String id,String phone,String email,String password){
        return employeeMapper.InsertEmployee(name, company, id, phone, email, password);
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
    public employedashboard getEmployeeDash(Integer number){
        employedashboard employedashboard =new employedashboard();
        Employee employee = new Employee();
        employee = employeeMapper.getEmployeeByNumber(number);
        employedashboard.setCompanynumber(employee.getCompany());
        employedashboard.setCompanyname(companyMapper.getCompanyByNumber(employee.getCompany()).getName());
        employedashboard.setEmployeename(employee.getName());
        employedashboard.setIfadmin(employee.getActive()==0?"否":"是");
        employedashboard.setNewnumber(caseMapper.getNewCaseAmountByAssignedTo(number));
        Integer inprogressnumber = caseMapper.getInProgressCaseAmountByAssignedTo(number) + caseMapper.getInFixCaseAmountByAssignedTo(number);
        employedashboard.setInprogressnumber(inprogressnumber);
        employedashboard.setEscalationnumber(caseMapper.getEscalationCaseAmountByAssignedTo(number));
        employedashboard.setEmergencynumber(caseMapper.getEmergencyCaseAmountByAssignedTo(number));
        employedashboard.setResolvednumber(caseMapper.getResoledCaseAmountByAssignedTo(number));
        return employedashboard;
    }
}
 