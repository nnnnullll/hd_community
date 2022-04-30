package com.example.demo.enity;

public class LoginDashboard {
    String loginname;
    String companynumber;
    String Line1;
    Integer communitynumber;
    Integer partnernumber;
    String Line2;
    Integer ifadmin;

    //employee household
    Integer newnumber;//e h
    Integer awaitnumber;//h
    Integer inprogressnumber;//e h
    Integer infixnumber;//e h
    Integer resolvednumber;//e h
    Integer closednumber;//h
    Integer escalationnumber;//e
    Integer emergencynumber;//e

    //partner
    Integer fixwaite_number;
    Integer fixassigned_number;
    Integer infix_number;
    Integer fixfinish_number;
    Integer fixclose_number;
    
    
    String stateChartTitle;
    Integer[] statechart;
    String[] stateChartLabels;
    String typeChartTitle;
    Integer[] typechart;
    String[] typeChartLabels;

    
    public Integer getInfixnumber() {
        return infixnumber;
    }
    public void setInfixnumber(Integer infixnumber) {
        this.infixnumber = infixnumber;
    }
    public Integer getClosednumber() {
        return closednumber;
    }
    public void setClosednumber(Integer closednumber) {
        this.closednumber = closednumber;
    }
    public Integer getAwaitnumber() {
        return awaitnumber;
    }
    public void setAwaitnumber(Integer awaitnumber) {
        this.awaitnumber = awaitnumber;
    }
    public Integer getPartnernumber() {
        return partnernumber;
    }
    public void setPartnernumber(Integer partnernumber) {
        this.partnernumber = partnernumber;
    }
    public Integer getIfadmin() {
        return ifadmin;
    }
    public void setIfadmin(Integer ifadmin) {
        this.ifadmin = ifadmin;
    }
    public Integer getFixwaite_number() {
        return fixwaite_number;
    }
    public void setFixwaite_number(Integer fixwaite_number) {
        this.fixwaite_number = fixwaite_number;
    }
    public Integer getFixassigned_number() {
        return fixassigned_number;
    }
    public void setFixassigned_number(Integer fixassigned_number) {
        this.fixassigned_number = fixassigned_number;
    }
    public Integer getInfix_number() {
        return infix_number;
    }
    public void setInfix_number(Integer infix_number) {
        this.infix_number = infix_number;
    }
    public Integer getFixfinish_number() {
        return fixfinish_number;
    }
    public void setFixfinish_number(Integer fixfinish_number) {
        this.fixfinish_number = fixfinish_number;
    }
    public Integer getFixclose_number() {
        return fixclose_number;
    }
    public void setFixclose_number(Integer fixclose_number) {
        this.fixclose_number = fixclose_number;
    }
    public String getLine1() {
        return Line1;
    }
    public void setLine1(String line1) {
        Line1 = line1;
    }
    public String getLine2() {
        return Line2;
    }
    public void setLine2(String line2) {
        Line2 = line2;
    }
    public Integer getCommunitynumber() {
        return communitynumber;
    }
    public void setCommunitynumber(Integer integer) {
        this.communitynumber = integer;
    }
    public String getLoginname() {
        return loginname;
    }
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String[] getStateChartLabels() {
        return stateChartLabels;
    }
    public void setStateChartLabels(String[] stateChartLabels) {
        this.stateChartLabels = stateChartLabels;
    }
    public String[] getTypeChartLabels() {
        return typeChartLabels;
    }
    public void setTypeChartLabels(String[] typeChartLabels) {
        this.typeChartLabels = typeChartLabels;
    }
    public String getStateChartTitle() {
        return stateChartTitle;
    }
    public void setStateChartTitle(String stateChartTitle) {
        this.stateChartTitle = stateChartTitle;
    }
    public String getTypeChartTitle() {
        return typeChartTitle;
    }
    public void setTypeChartTitle(String typeChartTitle) {
        this.typeChartTitle = typeChartTitle;
    }
    public Integer[] getStatechart() {
        return statechart;
    }
    public void setStatechart(Integer[] statechart) {
        this.statechart = statechart;
    }
    public Integer[] getTypechart() {
        return typechart;
    }
    public void setTypechart(Integer[] typechart) {
        this.typechart = typechart;
    }
    public Integer getResolvednumber() {
        return resolvednumber;
    }
    public void setResolvednumber(Integer resolvednumber) {
        this.resolvednumber = resolvednumber;
    }
    
    public Integer getNewnumber() {
        return newnumber;
    }
    public void setNewnumber(Integer newnumber) {
        this.newnumber = newnumber;
    }
    public Integer getInprogressnumber() {
        return inprogressnumber;
    }
    public void setInprogressnumber(Integer inprogressnumber) {
        this.inprogressnumber = inprogressnumber;
    }
    public Integer getEscalationnumber() {
        return escalationnumber;
    }
    public void setEscalationnumber(Integer escalationnumber) {
        this.escalationnumber = escalationnumber;
    }
    public Integer getEmergencynumber() {
        return emergencynumber;
    }
    public void setEmergencynumber(Integer emergencynumber) {
        this.emergencynumber = emergencynumber;
    }
    public String getCompanynumber() {
        return companynumber;
    }
    public void setCompanynumber(String companynumber) {
        this.companynumber = companynumber;
    }
}
