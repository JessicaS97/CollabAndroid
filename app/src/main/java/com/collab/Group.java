package com.collab;

public class Group {
    String groupName, groupSize, groupDescription, groupProfileFit, groupCategory, groupAuthor;

    public Group() {
        
    }

    public Group(String groupName, String groupSize, String groupDescription, String groupProfileFit, String groupCategory, String groupAuthor) {
        this.groupName = groupName;
        this.groupSize = groupSize;
        this.groupDescription = groupDescription;
        this.groupProfileFit = groupProfileFit;
        this.groupCategory = groupCategory;
        this.groupAuthor = groupAuthor;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupProfileFit() {
        return groupProfileFit;
    }

    public void setGroupProfileFit(String groupProfileFit) {
        this.groupProfileFit = groupProfileFit;
    }

    public String getGroupCategory() {
        return groupCategory;
    }

    public void setGroupCategory(String groupCategory) {
        this.groupCategory = groupCategory;
    }

    public String getGroupAuthor() {
        return groupAuthor;
    }

    public void setGroupAuthor(String groupAuthor) {
        this.groupAuthor = groupAuthor;
    }
}
