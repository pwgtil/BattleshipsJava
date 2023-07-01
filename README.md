# Battleships!
Hyperskill project for Java

Project definition can be found  [here](https://hyperskill.org/projects/125).

### UML Structure
Game class is to start playing Battleships.
It generates two Players. 

Each has its own Board. 
Board is filled with Ships or Shellings that inherit from Area. 
Location is represented by Position class. 

Details are visible below:
<img src=".\task\diagram_uml.png" title="UML"/>


```plantuml
@startuml
@startuml
class battleship.Board {
~ {static} String MSG_ERR_TOO_CLOSE
~ {static} String MSG_YOU_MISS
~ {static} String MSG_YOU_HIT
~ {static} String MSG_YOU_HIT_AND_SANK_SHIP
~ {static} String MSG_YOU_WIN
~ {static} char ICON_FOG
~ {static} char ICON_SHIP
~ {static} char ICON_HIT
~ {static} char ICON_MISS
~ {static} int boardSize
+ {static} HashMap<Integer,String> rowInt2Str
+ {static} HashMap<Integer,String> colInt2Str
+ {static} HashMap<String,Integer> rowStr2Int
+ {static} HashMap<String,Integer> colStr2Int
~ ArrayList<Area> areas
~ HashSet<Position> areaOfInfluence
+ String getBoardForDisplay(boolean)
+ void processShipPlacement(Ship,Position,Position)
- void checkAreaOfInfluence(Ship)
+ void processShelling(Position)
- Result checkHitAndProcess(Area)
+ String lastShotResult()
+ boolean allShipsShelled()
}
enum battleship.Result {
+  MISS
+  HIT
+  HIT_AND_SUNK
+  WIN
~ String msg
}
class battleship.Position {
~ int row
~ int col
+ boolean equals(Object)
+ int hashCode()
+ boolean positionInBounds()
+ {static} int stringNum2Int(String)
+ {static} int stringLetter2Int(String)
+ {static} String integer2StringNum(int)
+ {static} String integer2StringLetter(int)
}
abstract class battleship.Area {
# ArrayList<Position> positions
+ ArrayList<Position> getPositions()
+ void setLocation(Position[])
}
class battleship.Ship {
~ {static} String MSG_ERR_WRONG_LOCATION
~ {static} String MSG_ERR_WRONG_LENGTH
+ int noOfCells
+ String name
# int hitPoints
- boolean alive
~ HashSet<Position> areaOfInfluence
+ boolean isAlive()
+ void setLocation(Position[])
- HashSet<Position> fillAreaOfInfluence(ArrayList<Position>)
- ArrayList<Position> fillShipLocation(int,int,int,int)
+ boolean hitShipAndCheckIfDead()
}
class battleship.Shelling {
~ {static} String MSG_ERR_WRONG_COORDINATES
+ int noOfShootsAt
+ void setLocation(Position[])
}
enum battleship.ShipType {
+  CARRIER
+  BATTLESHIP
+  SUBMARINE
+  CRUISER
+  DESTROYER
~ int noOfCells
~ String name
}
class battleship.Player {
~ {static} String MSG_ENTER_COORDINATES
~ {static} String MSG_TAKE_A_SHOT
~ {static} String MSG_DASH_BAR
- Board board
+ String nickname
- void setShipLocation(Ship)
- Position getPositionFromText(String)
+ void setShipLocationAndDrawBoard(Ship)
- void drawFullBoard()
+ void drawFoggedBoard()
+ void fire()
+ void coordinatesPromptAndCheck()
+ boolean gameContinues()
+ void printBothBoards(Player)
}
class battleship.Game {
~ {static} String MSG_PLACE_YOUR_SHIPS
~ {static} String MSG_ITS_YOUR_TURN
~ {static} String MSG_PRESS_ENTER
~ {static} Scanner sc
~ Player player1
~ Player player2
~ Player currentPlayer
+ void initializationOfBoards()
- void playerBoardSetup(Player)
+ void startGame()
- void switchPlayer(Player)
- Player getEnemy()
+ void passMove()
}
class battleship.Main {
+ {static} void main(String[])
}


battleship.Area <|-- battleship.Ship
battleship.Area <|-- battleship.Shelling
@enduml
@enduml
```


[//]: # (> This sample is a part of the [codeSnippets]&#40;../../README.md&#41; Gradle project.)
[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (```bash)

[//]: # ()
[//]: # (./gradlew :tutorial-server-get-started:run)

[//]: # ()
[//]: # (```)