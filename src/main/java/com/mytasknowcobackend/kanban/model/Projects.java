package com.mytasknowcobackend.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Projects implements Serializable {

    String projectName;
    String projectId;
    String projectType;
    String projectPriority;
    String projectDescription;
    String projectOwner;
    String projectCreatedBy;
    String projectStartTime;
    String projectEndTime;
    String projectDuration;
    String projectStatus;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(String projectPriority) {
        this.projectPriority = projectPriority;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectCreatedBy() {
        return projectCreatedBy;
    }

    public void setProjectCreatedBy(String projectCreatedBy) {
        this.projectCreatedBy = projectCreatedBy;
    }

    public String getProjectStartTime() {
        return projectStartTime;
    }

    public void setProjectStartTime(String projectStartTime) {
        this.projectStartTime = projectStartTime;
    }

    public String getProjectEndTime() {
        return projectEndTime;
    }

    public void setProjectEndTime(String projectEndTime) {
        this.projectEndTime = projectEndTime;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Projects() {
    }

    public Projects(@JsonProperty("projectName") String projectName, @JsonProperty("projectId") String projectId, @JsonProperty("projectType") String projectType, @JsonProperty("projectPriority") String projectPriority, @JsonProperty("projectDescription") String projectDescription, @JsonProperty("projectOwner") String projectOwner, @JsonProperty("projectCreatedBy") String projectCreatedBy, @JsonProperty("projectStartTime") String projectStartTime, @JsonProperty("projectEndTime") String projectEndTime, @JsonProperty("projectDuration") String projectDuration, @JsonProperty("projectStatus") String projectStatus) {
        this.projectName = projectName;
        this.projectId = projectId;
        this.projectType = projectType;
        this.projectPriority = projectPriority;
        this.projectDescription = projectDescription;
        this.projectOwner = projectOwner;
        this.projectCreatedBy = projectCreatedBy;
        this.projectStartTime = projectStartTime;
        this.projectEndTime = projectEndTime;
        this.projectDuration = projectDuration;
        this.projectStatus = projectStatus;
    }
}
