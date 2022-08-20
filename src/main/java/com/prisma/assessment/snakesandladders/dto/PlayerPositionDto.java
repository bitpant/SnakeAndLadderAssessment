package com.prisma.assessment.snakesandladders.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlayerPositionDto {
    private long playerId;
    private int position;
    private boolean isGameComplete;
    private String moveDetail="";
    @JsonIgnore
    private boolean sameDiceValues;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isGameComplete() {
        return isGameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        isGameComplete = gameComplete;
    }

    public String getMoveDetail() {
        return moveDetail;
    }

    public void setMoveDetail(String moveDetail) {
        this.moveDetail +=moveDetail;
    }

    public boolean isSameDiceValues() {
        return sameDiceValues;
    }

    public void setSameDiceValues(boolean sameDiceValues) {
        this.sameDiceValues = sameDiceValues;
    }
}
