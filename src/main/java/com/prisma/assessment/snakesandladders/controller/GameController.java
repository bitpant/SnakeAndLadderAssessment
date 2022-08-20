package com.prisma.assessment.snakesandladders.controller;

import com.prisma.assessment.snakesandladders.dto.PlayerPositionDto;
import com.prisma.assessment.snakesandladders.entity.Board;
import com.prisma.assessment.snakesandladders.service.SnakeAndLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private SnakeAndLadderService snakeAndLadderService;

     @GetMapping("/loadboard")
    public ResponseEntity<Board> loadBoard(){
       return new ResponseEntity<>(snakeAndLadderService.loadBoard(), HttpStatus.OK);
    }

     @GetMapping("/rollandplay")
     public PlayerPositionDto rollDiceAndPlayMove(@RequestParam("id") Long id) throws NoSuchAlgorithmException {
       return snakeAndLadderService.rollDiceAndPlayMove(id);
    }

}
