package com.prisma.assessment.snakesandladders.service;

import com.prisma.assessment.snakesandladders.dto.PlayerPositionDto;
import com.prisma.assessment.snakesandladders.entity.Board;

import java.security.NoSuchAlgorithmException;

public interface SnakeAndLadderService {
    Board loadBoard();
    PlayerPositionDto rollDiceAndPlayMove(Long id) throws NoSuchAlgorithmException;
}
