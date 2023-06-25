# Animal Chess
This is a simple CLI jungle game written in Java.

For the game rules, please refer to the description at https://en.wikipedia.org/wiki/Jungle_(board_game).

## System Architecture
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/287ac7af-b09a-4c6a-a10b-ce1fad170d3e)

## Class Diagram
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/d2312469-3d0a-449e-8836-2fa8c5250a25)


## Game Control
In the game, the user only needs to enter two specific values per turn. The piece to be moved and the direction of the move. For ease of user input, pieces and directions will be in abbreviated form. Please see the information below.

|     Pieces      |     Player A    |     Player B    |
|-----------------|-----------------|-----------------|
|     Elephant    |     El_A        |     El_B        |
|     Lion        |     Li_A        |     Li_B        |
|     Tiger       |     Ti_A        |     Ti_B        |
|     Leopard     |     Le_A        |     Le_B        |
|     Wolf        |     Wo_A        |     Wo_B        |
|     Dog         |     Do_A        |     Do_B        |
|     Cat         |     Ca_A        |     Ca_B        |
|     Rat         |     Ra_A        |     Ra_B        |

### Direction
Enter the abbreviated form of the direction to decide which direction of movement.

|     Direction    |     Abbreviation    |
|------------------|---------------------|
|     Up           |     u               |
|     Down         |     d               |
|     Right        |     r               |
|     Left         |     l               |

### Example
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/34c9cb0f-afaa-43e0-8eaa-5bcab7d66321)

![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/d4afee71-9abb-4765-be53-b2cd660a43cf)

## Game Board
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/a1955c33-e3f4-4815-94f4-3f542c16e2e6)

The game board includes a 7 by 9 grid
1.	The player’s den
2.	The river
3.	The trap
4.	The player’s piece

## Game Process
The game will start on player A’s turn. After valid inputs of piece and direction. The game will continue to player B’s turn. If player enters an invalid input, the player is required to re-enter until the input is valid. Every capture must satisfy the capture requirements. Otherwise user is required to re-enter.

### Valid Movement
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/fda75bf8-dc4a-4c8e-bc8f-dcf7bf930790)

The image above shows a valid move for Player A. Player A moved the rat to the right.

### Invalid Movement
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/02a9635c-2c5b-4eff-babc-5094a48b23e8)

The image above shows an invalid move for Player B. Player B is required to re-enter.

### Valid Capture
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/204ec821-bc5b-4b09-a699-6b918096a3fe)

The image above shows a valid capture for Player A. Player A used a rat to capture the opponent's elephant.

### Invalid Capture
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/32da18e6-2d8d-441a-b435-1dea0cf0c549)

The image above shows an invalid capture for Player B. Player B is required to re-enter. 

## Win Conditions
The game will end until one of the players enters the opponent’s den or captures all the opponent’s pieces.

### Enter opponent’s den
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/fe3937f2-7e3b-4f92-b2c3-34c5ea9fa42d)

The image above shows that Player A wins by moving the piece to the opponent's lair.

### Capture all opponent’s pieces
![圖片](https://github.com/sky92i/AnimalChess/assets/48410483/85da13d7-33c1-49b0-b1f4-5a483a108ee6)

The image above shows that Player A wins by capturing all of his opponent's pieces.

