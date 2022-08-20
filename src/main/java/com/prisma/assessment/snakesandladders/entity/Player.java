package com.prisma.assessment.snakesandladders.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    @Size(min=2, message="Name should have at least 2 characters")
    private String playerName;

    @Column(columnDefinition = "int default 0")
    private int playerCurrentPosition;

    public Player() {
    }

    public Player(String playerName, int playerCurrentPosition) {
        this.playerName = playerName;
        this.playerCurrentPosition = playerCurrentPosition;
    }

    public Player(long playerId, String playerName, int playerCurrentPosition) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerCurrentPosition = playerCurrentPosition;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerCurrentPosition() {
        return playerCurrentPosition;
    }

    public void setPlayerCurrentPosition(int playerCurrentPosition) {
        this.playerCurrentPosition = playerCurrentPosition;
    }

}
