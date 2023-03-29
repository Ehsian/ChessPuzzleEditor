# My Personal Project - Chess Board Editor

## Project Description

This application is a puzzle creator & custom board editor for the classic game of chess on an
8x8 board. This application will feature two major functions; making a custom position
on an 8x8 chess board (through drag and dropping chess pieces), and adding intended custom moves
on that custom chess board to create a "solution" to the puzzle that you created.

This project is of interest to me because I've always enjoyed playing chess, but within chess,
I enjoyed completing chess puzzles the most. I feel like chess puzzles are a good way to learn 
how to find the best moves in a real game of chess. More importantly, I like the problem-solving
nature of solving chess puzzles. Lately, the only thing that connects me to chess are
solving daily chess puzzles on various websites. My goal for this project is to create something similar,
except unlike those websites, I would like users to be able to create more "custom" puzzles for
themselves and friends to use.


## User Stories:
- As a user, I want to be able to add chess pieces to my chess board [Phase 1 Done]
- As a user, I want to be able to view the chess pieces I placed on my chess board [Phase 1 Done]
- As a user, I want to be able to move a chess piece on my chess board [Phase 1 Done]
- As a user, I want to be able to save a list of moves on a custom chess board as a "puzzle" [Phase 1 Done]
- As a user, I want to be able to add and remove custom moves to a "puzzle" [Phase 1 Done]
- As a user, I want to be able to load a "puzzle" and attempt to solve it [Phase 1 Done]
- As a user, I want to be able to show the answer to a puzzle. [Phase 1 Done]
- As a user, I want to be able to save my current "puzzle" on demand.
- As a user, I want to be able to load a previous "puzzle" on startup.


## Instructions for Grader:
1. Run MainGUI.main()
2. Press "New Puzzle"
3. Complete startup config (E.g. Input ke1)
4. Once board is not empty, input "0" to end config
5. Input "1" to edit the puzzle, (add/remove moves). 
This should cover both instances of actions related to adding/removing Xs to a Y.
   (Adding/removing boards to a puzzle)
6. Note: Initial board setup should also satisfy adding/removing Xs to a Y
   (Adding/removing pieces to a board)

Images are available under the CC0 (creative commons) license
taken from wikimedia commons.

Link to images: https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent



  
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

Can save data as a list of long strings of piece positions rather than the 2D boards themselves
Will only require one JSONArray of Strings. Minor helper/private functions will be needed.
E.g. K01k71Q23q35...
Have a for loop on save/load to convert data to that string or that string to data
On load: Using position string rebuild the puzzle for each item in the JSONArray