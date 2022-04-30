package com.example.demo.enity;


public class CaseDetail {
    Integer number;
    KeyValueString company;
    KeyValue community;
    KeyValue household;
    String subject;
    String description;
    Integer type;
    KeyValue assigned_to;
    Integer state;
    KeyValue fix_assigned_to;
    Integer fix_state;
    String solution;
    Integer escalation;
    Integer emergency;
    Integer created_role;
    Integer created_by;
    String created;
    Integer updated_role;
    Integer updated_by;
    String updated;
    Activity[] activities;
    option[] options_fix;
    option[] options_agent;
    
    
    public option[] getOptions_agent() {
        return options_agent;
    }
    public void setOptions_agent(option[] options_agent) {
        this.options_agent = options_agent;
    }
    public option[] getOptions_fix() {
        return options_fix;
    }
    public void setOptions_fix(option[] options_fix) {
        this.options_fix = options_fix;
    }
    public Activity[] getActivities() {
        return activities;
    }
    public void setActivities(Activity[] activities) {
        this.activities = activities;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public KeyValueString getCompany() {
        return company;
    }
    public void setCompany(KeyValueString company) {
        this.company = company;
    }
    public KeyValue getCommunity() {
        return community;
    }
    public void setCommunity(KeyValue community) {
        this.community = community;
    }
    public KeyValue getHousehold() {
        return household;
    }
    public void setHousehold(KeyValue household) {
        this.household = household;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public KeyValue getAssigned_to() {
        return assigned_to;
    }
    public void setAssigned_to(KeyValue assigned_to) {
        this.assigned_to = assigned_to;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public KeyValue getFix_assigned_to() {
        return fix_assigned_to;
    }
    public void setFix_assigned_to(KeyValue fix_assigned_to) {
        this.fix_assigned_to = fix_assigned_to;
    }
    public Integer getFix_state() {
        return fix_state;
    }
    public void setFix_state(Integer fix_state) {
        this.fix_state = fix_state;
    }
    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution;
    }
    public Integer getEscalation() {
        return escalation;
    }
    public void setEscalation(Integer escalation) {
        this.escalation = escalation;
    }
    public Integer getEmergency() {
        return emergency;
    }
    public void setEmergency(Integer emergency) {
        this.emergency = emergency;
    }
    public Integer getCreated_role() {
        return created_role;
    }
    public void setCreated_role(Integer created_role) {
        this.created_role = created_role;
    }
    public Integer getCreated_by() {
        return created_by;
    }
    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public Integer getUpdated_role() {
        return updated_role;
    }
    public void setUpdated_role(Integer updated_role) {
        this.updated_role = updated_role;
    }
    public Integer getUpdated_by() {
        return updated_by;
    }
    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }
    public String getUpdated() {
        return updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
    }
    
    
}
