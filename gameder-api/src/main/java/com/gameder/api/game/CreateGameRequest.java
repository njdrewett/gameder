package com.gameder.api.game;

public class CreateGameRequest {

    private String displayName;
    private String description;
    private byte[] displayImage;
    private int ageRestriction;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGameRequest that = (CreateGameRequest) o;
        return ageRestriction == that.ageRestriction && displayName.equals(that.displayName);
    }


    public CreateGameRequest(String displayName, String description, byte[] displayImage, int ageRestriction) {
        this.displayName = displayName;
        this.description = description;
        this.displayImage = displayImage;
        this.ageRestriction = ageRestriction;
    }

    public CreateGameRequest() {
    }

    public byte[] getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(byte[] displayImage) {
        this.displayImage = displayImage;
    }

    @Override
    public String toString() {
        return "CreateGameRequest{" +
                "displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", ageRestriction=" + ageRestriction +
                '}';
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}
