package com.prisma.assessment.snakesandladders.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Board {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int size;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Snake> snakes;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Ladder> ladders;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Player> players;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
