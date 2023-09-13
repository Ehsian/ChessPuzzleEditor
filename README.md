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

**Phase 4: Task 2**

Tue Apr 11 22:43:28 PDT 2023
A Piece has been set on a board.

Tue Apr 11 22:43:36 PDT 2023
A Piece has been set on a board.

Tue Apr 11 22:43:48 PDT 2023
A Piece has been moved on a board.


**Phase 4: Task 3**

Reflecting upon my UML diagram, I believe that my current design's biggest strong point is its simplicity.
However, as I was designing the class structure in phases 1 and 2, I intentionally chose simplicity, sacrificing
scalability. If I were given more time, I would have liked to add many more features. However, due to the way I
designed the class structure, designing new features would be messier and potentially more time-consuming. I
believe that I put too much functionality into the classes in the "ui" package. Though I don't believe I broke any
important design rules, I do believe there is a way to move most of the functionality from the 
"Puzzle" and "PuzzleGUI" classes into classes that would work in the "model" package. (For example, functions
in the "Puzzle" and "PuzzleGUI" classes such as "addMove" would still work if there is a model class for puzzles)
This would improve scalability. I believe it would be better for the UI classes' sole purpose was to display
a working UI, with all functionality in the model.