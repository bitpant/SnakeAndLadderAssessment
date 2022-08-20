package com.prisma.assessment.snakesandladders.service.impl;

import com.prisma.assessment.snakesandladders.dto.PlayerPositionDto;
import com.prisma.assessment.snakesandladders.entity.Board;
import com.prisma.assessment.snakesandladders.entity.Ladder;
import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.entity.Snake;
import com.prisma.assessment.snakesandladders.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SnakeAndLadderServiceImpl implements SnakeAndLadderService {

    private static final Logger logger = LoggerFactory.getLogger(SnakeAndLadderServiceImpl.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private SnakeService snakeService;

    @Autowired
    private LadderService ladderService;


    @Value("${game.dice.values}")
    private int totalDice;

    @Value("${game.total.player}")
    private int totalPlayer;

    @Value("${game.board.default.size}")
    private int defaultSize;

    Board board = new Board();

    boolean gameCompleted = false;

    private static final String GAME_COMPLETE = "Game is completed.Reset the board and play";

    /**
     * @return board
     * loadBoard is used to set board with snakes , ladders and players
     * this method also used to reset the board after game completes or midway
     */
    @Override
    public Board loadBoard() {
        List<Snake> snakes = snakeService.getAllSnakes();
        List<Ladder> ladders = ladderService.getAllLadders();
        List<Player> players = playerService.getAllPlayer();
        players = setMaximumPlayersWithStartingPos(players);
        setValueToBoard(ladders, snakes, players);
        gameCompleted = false;
        logger.debug("Board has been reset");
        return board;
    }

    /**
     * @param id
     * @return PlayerPositionDto
     * @throws NoSuchAlgorithmException rollDiceAndPlayMove methods accepts playerId and roll dice and find new position for player on board
     */
    @Override
    public PlayerPositionDto rollDiceAndPlayMove(Long id) throws NoSuchAlgorithmException {
        PlayerPositionDto positionDto = new PlayerPositionDto();
        if (!isBoardLoaded()) {
            positionDto.setMoveDetail("Load the board first");
            logger.debug("Load the board first");
            return positionDto;
        }
        Player currentPlayer = playerService.getPlayerById(id);

        if (checkIfPlayerIsPartOfCurrentGame(currentPlayer)) {
            positionDto.setMoveDetail(MessageFormat.format("                       {0} is not part of this game", currentPlayer.getPlayerName()));
            logger.debug("{} is not part of this game", currentPlayer.getPlayerName());
            return positionDto;
        }

        if (!gameCompleted) {
            boolean sameDiceValues;
            do {
                play(positionDto, currentPlayer);
                sameDiceValues = positionDto.isSameDiceValues();
            } while (sameDiceValues);
        } else {
            positionDto.setMoveDetail(GAME_COMPLETE);
            positionDto.setGameComplete(gameCompleted);
            logger.debug(GAME_COMPLETE);
        }
        return positionDto;
    }

    /**
     * @param currentPlayer
     * @return to check if player is part of current game
     */
    private boolean checkIfPlayerIsPartOfCurrentGame(Player currentPlayer) {
        Stream<Player> playerStream = boardService.findBoard().stream().findFirst().get().getPlayers()
                .stream().filter(player -> player == currentPlayer);
        return !playerStream.findFirst().isPresent();
    }

    /**
     * @param positionDto
     * @param currentPlayer
     * @throws NoSuchAlgorithmException play method called from rollDiceAndPlayMove and responsible for rolling dice and moving player to new location
     *                                  This method is responsible for handling first extra rule of play again if rolled double
     */
    private void play(PlayerPositionDto positionDto, Player currentPlayer) throws NoSuchAlgorithmException {
        positionDto.setSameDiceValues(false);
        Random rand = SecureRandom.getInstanceStrong();
        int totalValue = 0;
        int temp = 0;
        for (int i = 1; i <= totalDice; i++) {
            temp = rand.nextInt(6) + 1;
            logger.debug("{} rolled {} on dice {}", currentPlayer.getPlayerName(), temp, i);
            positionDto.setMoveDetail(MessageFormat.format("                       {0} rolled {1} on dice {2}", currentPlayer.getPlayerName(), temp, i));
            if (temp == totalValue) {
                positionDto.setSameDiceValues(true);
                logger.debug("{} rolled double so Player will also get second chance after finishing current move", currentPlayer.getPlayerName());
                positionDto.setMoveDetail(MessageFormat.format("                   {0} rolled double so Player will also get second chance after finishing current move", currentPlayer.getPlayerName()));
            }
            totalValue += temp;
        }

        currentPlayer = movePlayer(currentPlayer, totalValue, positionDto);
        if (checkIfPlayerWon(currentPlayer)) {
            gameCompleted = true;
        }

        positionDto.setPlayerId(currentPlayer.getPlayerId());
        positionDto.setPosition(currentPlayer.getPlayerCurrentPosition());
        positionDto.setGameComplete(gameCompleted);
        if (gameCompleted) {
            logger.debug("{} wins the game", currentPlayer.getPlayerName());
            positionDto.setMoveDetail(MessageFormat.format("                   {0} wins the game", currentPlayer.getPlayerName()));
        } else {
            positionDto.setMoveDetail("                     " + currentPlayer.getPlayerName() + " moved to " + currentPlayer.getPlayerCurrentPosition());
        }
    }

    private boolean isBoardLoaded() {
        return null != board.getId();
    }

    /**
     * @param player
     * @return method to check player has won or not on current move
     */
    public boolean checkIfPlayerWon(Player player) {
        int playerPosition = player.getPlayerCurrentPosition();
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }

    /**
     * @param player
     * @param steps
     * @return Responsible for moving player to new location
     * Called from play method
     * This method also take care of second extra rule of moved back position of overshoot board size
     */
    public Player movePlayer(Player player, int steps, PlayerPositionDto positionDto) {
        if (player != null) {
            int oldPosition = player.getPlayerCurrentPosition();
            int newPosition = oldPosition + steps;
            int boardSize = board.getSize();
            if (newPosition > boardSize) {
                newPosition = boardSize - (newPosition - boardSize);
                positionDto.setMoveDetail(MessageFormat.format("                           {0} overshoot the {1} square by {2} so moved back to {3}", player.getPlayerName(), board.getSize(), -1 * (newPosition - boardSize), newPosition));
                logger.debug("{} overshoot the {} square by {} so moved back to {}", player.getPlayerName(), board.getSize(), -1 * (newPosition - boardSize), newPosition);
            }
            newPosition = getNewPositionAfterSnakeLadder(player, newPosition, positionDto);
            player.setPlayerCurrentPosition(newPosition);
            logger.debug("{} rolled total {} and moved to {}", player.getPlayerName(), steps, newPosition);
        }
        return playerService.save(player);
    }

    /**
     * @param player
     * @param position
     * @return finding new position based on snakes and ladders on board
     */
    public int getNewPositionAfterSnakeLadder(Player player, int position, PlayerPositionDto positionDto) {
        Optional<Snake> snakeEnd;
        Optional<Ladder> ladderEnd;
        snakeEnd = board.getSnakes().stream().filter(snake -> snake.getStartPosition() == position)
                .findFirst();
        if (snakeEnd.isPresent()) {
            positionDto.setMoveDetail(MessageFormat.format("                          {0} got snake at {1} position and reached position {2}", player.getPlayerName(), position, snakeEnd.get().getEndPosition()));
            logger.debug("{} got snake at {} position and reached position {}", player.getPlayerName(), position, snakeEnd.get().getEndPosition());
            return snakeEnd.get().getEndPosition();
        }

        ladderEnd = board.getLadders().stream().filter(ladder -> ladder.getStartPosition() == position)
                .findFirst();
        if (ladderEnd.isPresent()) {
            positionDto.setMoveDetail(MessageFormat.format("                          {0} got ladder at {1} position and reached position {2}", player.getPlayerName(), position, ladderEnd.get().getEndPosition()));
            logger.debug("{} got ladder at {} position and reached position {}", player.getPlayerName(), position, ladderEnd.get().getEndPosition());
            return ladderEnd.get().getEndPosition();
        }
        return position;
    }

    /**
     * @param ladders
     * @param snakes
     * @param players
     */
    private void setValueToBoard(List<Ladder> ladders, List<Snake> snakes, List<Player> players) {
        board.setLadders(ladders);
        board.setSnakes(snakes);
        board.setPlayers(players);
        board.setSize(defaultSize);
        boardService.save(board);
    }

    /**
     * @param players
     * @return responsible for setting maximum player based on properties file and putting them on starting location
     */
    private List<Player> setMaximumPlayersWithStartingPos(List<Player> players) {
        if (players.size() > totalPlayer) {
            players = players.subList(0, totalPlayer);
        }
        return players.stream().map(player -> {
            player.setPlayerCurrentPosition(0);
            playerService.save(player);
            return player;
        }).collect(Collectors.toList());
    }

}
