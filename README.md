# SnakeAndLadderAssessment
# My docker image
https://hub.docker.com/repository/docker/bitpant/snakeladder-snakesandladders

# Docker uri
docker.io/bitpant/snakeladder-snakesandladders:0.0.1-SNAPSHOT

# command to run image
docker run -p 8080:8080 bitpant/snakeladder-snakesandladders:0.0.1-SNAPSHOT


# We will need these two apis to play the game

http://localhost:8080/game/loadboard - to load snakes,ladders and players to the board and also to reset the game.

http://localhost:8080/game/rollandplay?id=3  We can pass player in this api and it will roll dice and move in board

So front ends can call these apis and players can play.

# Swagger
http://localhost:8080/swagger-ui.html
