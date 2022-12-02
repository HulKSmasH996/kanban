package com.mytasknowcobackend.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users implements Serializable {

    String userName;
    String userEmail;
    String userId;
    String userDetails;
    String userIssues;

    String userPicture;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getUserIssues() {
        return userIssues;
    }

    public void setUserIssues(String userIssues) {
        this.userIssues = userIssues;
    }

    public Users(@JsonProperty("userName") String userName, @JsonProperty("userEmail") String userEmail, @JsonProperty("userId") String userId, @JsonProperty("userDetails") String userDetails, @JsonProperty("userIssues") String userIssues) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userDetails = userDetails;
        this.userIssues = userIssues;
    }

    public Users() {
    }

}
