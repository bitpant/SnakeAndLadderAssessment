package com.prisma.assessment.snakesandladders.entity;

import javax.persistence.*;

@Entity
public class Ladder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ladderId;

    private int startPosition;
    private int endPosition;

    public long getLadderId() {
        return ladderId;
    }

    public void setLadderId(long ladderId) {
        this.ladderId = ladderId;
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
