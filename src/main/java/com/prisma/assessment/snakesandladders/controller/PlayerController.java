package com.prisma.assessment.snakesandladders.controller;

import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/createplayer")
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody Player player)  {
        return new ResponseEntity<>(playerService.save(player), HttpStatus.CREATED);
    }

    @GetMapping("/getallplayer")
    public ResponseEntity<List<Player>> getAllPlayer()  {
        return new ResponseEntity<>(playerService.getAllPlayer(), HttpStatus.OK);
    }

    @GetMapping("/getplayerbyid")
    public ResponseEntity<Player> getPlayerById(@RequestParam("id") Long id)  {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteplayerbyid")
    public ResponseEntity<String> deletePlayerById(@RequestParam("id") Long id)  {
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
