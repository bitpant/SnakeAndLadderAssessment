# SnakeAndLadderAssessment
Docker uri: docker.io/bitpant/snakeladder-snakesandladders:0.0.1-SNAPSHOT

docker run -p 8080:8080 bitpant/snakeladder-snakesandladders:0.0.1-SNAPSHOT

#game:
http://localhost:8080/game/loadboard

http://localhost:8080/game/rollandplay?id=3

#player:
http://localhost:8080/player/getallplayer

http://localhost:8080/player/getplayerbyid?id=3

http://localhost:8080/player/createplayer

http://localhost:8080/player/deleteplayerbyid?id=8


#Swagger: 
http://localhost:8080/swagger-ui.html
