package com.mytasknowcobackend.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issues {

    String issueName;
    String issueId;
    String issueType;
    String issuePriority;
    String issueDescription;
    String issueTags;
    String issueAssignee;
    String issueSummary;
    String issueStartTime;
    String issueEndTime;
    String issueDuration;
    String issueStatus;

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssuePriority() {
        return issuePriority;
    }

    public void setIssuePriority(String issuePriority) {
        this.issuePriority = issuePriority;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueTags() {
        return issueTags;
    }

    public void setIssueTags(String issueTags) {
        this.issueTags = issueTags;
    }

    public String getIssueAssignee() {
        return issueAssignee;
    }

    public void setIssueAssignee(String issueAssignee) {
        this.issueAssignee = issueAssignee;
    }

    public String getIssueSummary() {
        return issueSummary;
    }

    public void setIssueSummary(String issueSummary) {
        this.issueSummary = issueSummary;
    }

    public String getIssueStartTime() {
        return issueStartTime;
    }

    public void setIssueStartTime(String issueStartTime) {
        this.issueStartTime = issueStartTime;
    }

    public String getIssueEndTime() {
        return issueEndTime;
    }

    public void setIssueEndTime(String issueEndTime) {
        this.issueEndTime = issueEndTime;
    }

    public String getIssueDuration() {
        return issueDuration;
    }

    public void setIssueDuration(String issueDuration) {
        this.issueDuration = issueDuration;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Issues(@JsonProperty("issueName") String issueName, @JsonProperty("issueId") String issueId, @JsonProperty("issueType") String issueType,@JsonProperty("issuePriority") String issuePriority,@JsonProperty("issueDescription") String issueDescription,@JsonProperty("issueTags") String issueTags,@JsonProperty("issueAssignee") String issueAssignee, @JsonProperty("issueSummary") String issueSummary, @JsonProperty("issueStartTime") String issueStartTime, @JsonProperty("issueEndTime") String issueEndTime, @JsonProperty("issueDuration") String issueDuration, @JsonProperty("issueStatus") String issueStatus) {
        this.issueName = issueName;
        this.issueId = issueId;
        this.issueType = issueType;
        this.issuePriority = issuePriority;
        this.issueDescription = issueDescription;
        this.issueTags = issueTags;
        this.issueAssignee = issueAssignee;
        this.issueSummary = issueSummary;
        this.issueStartTime = issueStartTime;
        this.issueEndTime = issueEndTime;
        this.issueDuration = issueDuration;
        this.issueStatus = issueStatus;
    }

    public Issues() {
    }
}
