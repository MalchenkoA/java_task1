package ru.stqa.pft.mantis.model;

public class IssueStatus {
    private int id;
    private String subject;
    private String description;
    private String state_name;


    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getId() {
        return id;
    }

    public IssueStatus withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public IssueStatus withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueStatus withDescription(String description) {
        this.description = description;
        return this;
    }
    public String getState_name() {
        return state_name;
    }

    public IssueStatus withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }
}