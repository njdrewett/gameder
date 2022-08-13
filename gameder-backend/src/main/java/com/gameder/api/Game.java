package com.gameder.api;


import java.util.Arrays;
import java.util.Objects;

public class Game {

    private String id;
    private String displayName;
    private int ageRestriction;
    private byte[] gameImage;
    private String descriptionText;

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

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public byte[] getGameImage() {
        return gameImage;
    }

    public void setGameImage(byte[] gameImage) {
        this.gameImage = gameImage;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Game(String id, String displayName, int ageRestriction, byte[] gameImage, String descriptionText) {
        this.id = id;
        this.displayName = displayName;
        this.ageRestriction = ageRestriction;
        this.gameImage = gameImage;
        this.descriptionText = descriptionText;
    }

    public Game() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return ageRestriction == game.ageRestriction && id.equals(game.id) && displayName.equals(game.displayName) && Arrays.equals(gameImage, game.gameImage) && Objects.equals(descriptionText, game.descriptionText);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, displayName, ageRestriction, descriptionText);
        result = 31 * result + Arrays.hashCode(gameImage);
        return result;
    }

}
