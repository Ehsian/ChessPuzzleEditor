# My Personal Project - Classic Chess 

## Project Description

This application is my take on the classic game of chess on an
8x8 board. The application will have a singleplayer mode that uses a simple
chess engine (with varying degrees of depth depending on the chosen
difficulty), as well as a multiplayer pass-and-play mode, where
the visual board flips after each move to fit the perspectives
of the player whose turn it is. This application can be used by
chess players of any skill level, especially in the absence of access to
the internet to play with others online.

This project is of interest to me because I've always enjoyed playing chess,
and I've always been intrigued by how chess engines worked. I have an additional
interest in chess applications, since I have grown up alongside a major 
chess movement in software. Apps such as *Chess Titans* on Windows XP and the original
*Chess Free* app for IOS and Android will always be remembered as 
parts of my childhood. 


## User Stories:
- As a user, I want to be able to start a new game
- As a user, I want to be able to load a saved game
- As a user, I want to be able to play pass-and-play chess with a friend
- As a user, I want to be able to play singleplayer against a chess engine
with different difficulty levels
  - List of possible future boards will be needed to perform evaluation 
- As a user, I want to be able to see which chess moves are valid 
upon selecting a piece
- As a user, I want to be able to undo moves
- As a user, I want to be able to create a new (valid) chess move
  - This will form a new position/board (which will likely be
  represented by a 2D array of pieces)
- As a user, I want to be able to see a list of previous moves
- As a user, I want to be able to take a piece, removing it from
the board and adding it to a list of taken pieces (graveyard)
- As a user, I want to be able to play using chess coordinates in addition
to using a GUI.

  
### Sidenotes/Additional Ideas
Boards/positions may potentially be a 2D array of Strings or custom Objects.

E.g. Starting position (Uppercase is White, Lowercase is Black)

["r", "n", "b", "q", "k", "b", "n", "r"]</br>
["p", "p", "p", "p", "p", "p", "p", "p"]</br>
[" ", " ", " ", " ", " ", " ", " ", " "]</br>
[" ", " ", " ", " ", " ", " ", " ", " "]</br>
[" ", " ", " ", " ", " ", " ", " ", " "]</br>
[" ", " ", " ", " ", " ", " ", " ", " "]</br>
["P", "P", "P", "P", "P", "P", "P", "P"]</br>
["R", "N", "B", "Q", "K", "B", "N", "R"]