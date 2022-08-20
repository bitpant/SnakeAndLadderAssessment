package com.prisma.assessment.snakesandladders.service;

import com.prisma.assessment.snakesandladders.entity.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);
    List<Player> getAllPlayer();
    Player getPlayerById(Long id);
    void deletePlayerById(Long id);




}
