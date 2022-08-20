package com.prisma.assessment.snakesandladders.service;

import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.exception.PlayerNotFoundException;
import com.prisma.assessment.snakesandladders.repository.PlayerRepository;
import com.prisma.assessment.snakesandladders.service.impl.PlayerServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class PlayerServiceTests {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    public Player getPlayerForTest(){
        Player player=new Player(1l,"Test Name",0);
        return  player;
    }

    @Test
    public void testGetPlayerById() throws PlayerNotFoundException {
        Player player = getPlayerForTest();
        Mockito.when(playerRepository.findById(1l)).thenReturn(Optional.ofNullable(player));
        Player result = playerService.getPlayerById(1l);
        Assert.assertEquals(1l, result.getPlayerId());
    }

    @Test
    public void savePlayerTest() {
        Player player = getPlayerForTest();
        Mockito.when(playerRepository.save(player)).thenReturn(player);
        Player result = playerService.save(player);
        Assert.assertEquals(1, result.getPlayerId());
        Assert.assertEquals("Test Name", result.getPlayerName());
    }

    @Test
    public void removePlayerTest() throws PlayerNotFoundException {
        Player player = getPlayerForTest();
        Mockito.when(playerRepository.findById(1l)).thenReturn(Optional.ofNullable(player));
        playerService.deletePlayerById(player.getPlayerId());
        Mockito.verify(playerRepository, Mockito.times(1)).delete(player);
    }
}
