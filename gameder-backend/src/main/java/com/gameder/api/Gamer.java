package com.gameder.api;

import java.util.Date;

public class Gamer {

    private String id;
    private String displayName;
    private Date dateOfBirth;

    public Gamer() {
        super();
    }

    public Gamer(String id, String displayName, Date dateOfBirth) {
        this.id = id;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id: " + id);
        builder.append("DisplayName: " + displayName);
        builder.append("DateOfBirth: " + dateOfBirth);

        return builder.toString();
    }

}
