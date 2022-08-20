package com.prisma.assessment.snakesandladders.entity;

import javax.persistence.*;

@Entity
public class Snake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long snakeId;

    private int startPosition;
    private int endPosition;

    public long getSnakeId() {
        return snakeId;
    }

    public void setSnakeId(long snakeId) {
        this.snakeId = snakeId;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }
}
