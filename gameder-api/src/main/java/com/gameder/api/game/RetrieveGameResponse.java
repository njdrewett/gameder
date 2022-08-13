package com.gameder.api.game;

import java.util.Arrays;
import java.util.Objects;

public class RetrieveGameResponse {

    private String id;
    private String displayName;
    private String description;
    private byte[] displayImage;
    private int ageRestriction;

    public RetrieveGameResponse(String id, String displayName, String description, byte[] displayImage, int ageRestriction) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.displayImage = displayImage;
        this.ageRestriction = ageRestriction;
    }

    public RetrieveGameResponse() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(byte[] displayImage) {
        this.displayImage = displayImage;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveGameResponse that = (RetrieveGameResponse) o;
        return ageRestriction == that.ageRestriction && id.equals(that.id) && displayName.equals(that.displayName) && Objects.equals(description, that.description) && Arrays.equals(displayImage, that.displayImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, displayName, description, ageRestriction);
        result = 31 * result + Arrays.hashCode(displayImage);
        return result;
    }
}
