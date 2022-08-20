package com.prisma.assessment.snakesandladders.service.impl;

import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.exception.PlayerNotFoundException;
import com.prisma.assessment.snakesandladders.repository.PlayerRepository;
import com.prisma.assessment.snakesandladders.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
           Optional<Player> player = playerRepository.findById(id);
        return player.orElseThrow(()->new PlayerNotFoundException("Player with Id-"+ id +" not found"));
    }

    @Override
    public void deletePlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        playerRepository.delete(player.orElseThrow(()->new PlayerNotFoundException("Player with Id-"+ id +" not found")));
    }
}
