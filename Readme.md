# Introduction

This repo is meant as a tool to explore different programming concepts by implementing the game of Chess.  The project itself provides a very bare bones starting place.  You are encouraged to ask questions throughout this process; please don't be shy.

We assume you have at least passing knowledge of the game of chess, but no strategy is necessary for this project.  If you have little knowledge of chess, the Wikipedia article is an excellent reference:

http://en.wikipedia.org/wiki/Chess


# Getting Started With The App
The initial state of the project provides very little; not much more than a basic structure for displaying an empty chess board via a CLI.  After you clone the directory, you can run the program via Maven:

```Shell
$ mvn compile exec:java
{{ Maven output deleted for brevity }}
Welcome to Chess!
Type 'help' for a list of commands.
Current Game:
    a   b   c   d   e   f   g   h
  +---+---+---+---+---+---+---+---+
8 |   |   |   |   |   |   |   |   | 8
  +---+---+---+---+---+---+---+---+
7 |   |   |   |   |   |   |   |   | 7
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   | 6
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   | 5
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   |   |   | 4
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   | 3
  +---+---+---+---+---+---+---+---+
2 |   |   |   |   |   |   |   |   | 2
  +---+---+---+---+---+---+---+---+
1 |   |   |   |   |   |   |   |   | 1
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h

White's Move
> help
Possible commands:
    'help'                       Show this menu
    'quit'                       Quit Chess
    'new'                        Create a new game
    'board'                      Show the chess board
    'list'                       List all possible moves
    'move <colrow> <colrow>'     Make a move
{{ Board redisplayed }}
> quit
Goodbye!
```

# Your Goals
The overall goal is to build the game of chess so that you can play it from the CLI against another person.  Specific goals are listed below, but please ask any questions that come to mind.

## Goal #1: Modify the application to display a new game with pieces in place.
Your first goal is place the pieces of a chess set on the board.  The types of pieces on a chess board are:

- The King
- The Queen
- The Bishop
- The Knight
- The Rook
- The Pawn

The initial version of the application just shows you an empty board.  The positions of the pieces at the beginning of the game should be:

- White Pieces:
    - A1  Rook
    - B1  Knight
    - C1  Bishop
    - D1  Queen
    - E1  King
    - F1  Bishop
    - G1  Knight
    - H1  Rook
    - A2, B2, C2, D2, E2, F2, G2, H2
    All Pawns

- Black Pieces
    - A8  Rook
    - B8  Knight
    - C8  Bishop
    - D8  Queen
    - E8  King
    - F8  Bishop
    - G8  Knight
    - H8  Rook
    - A7, B7, C7, D7, E7, F7, G7, H7
    All Pawns

After you are done, you should be able to see the pieces on the board when you launch the program or use the 'new' command in the CLI:
```Shell
$ mvn compile exec:java
...
Welcome to Chess!
Type 'help' for a list of commands.
    a   b   c   d   e   f   g   h  
  +---+---+---+---+---+---+---+---+
8 | R | N | B | Q | K | B | N | R | 8
  +---+---+---+---+---+---+---+---+
7 | P | P | P | P | P | P | P | P | 7
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   | 6
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   | 5
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   |   |   | 4
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   | 3
  +---+---+---+---+---+---+---+---+
2 | p | p | p | p | p | p | p | p | 2
  +---+---+---+---+---+---+---+---+
1 | r | n | b | q | k | b | n | r | 1
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h  

White's Move
> 
```

## Goal #2:  Give a list of all the possible moves on the board
Each piece can move in specific ways.  In the CLI, you may have noticed a command, 'list', which is not currently implemented.  Your goal in this step is to implement that command, showing all the moves that the current player can make.

For the purposes of this exercise, you can specifically *ignore* these more complex moves:
- Castling the King
- Pawn En passant
- Pawn Promotion

The CLI already has a _list_ command, but it doesn't do anything.  After you're done, it should output all the possible moves:

```Shell
White's Move
> list
White's Possible Moves: 
    f2 f4
    f2 f3
    b1 a3
    b1 c3
    d2 d4
    d2 d3
    {{ ... other moves removed for brevity }}
    a   b   c   d   e   f   g   h  
  +---+---+---+---+---+---+---+---+
8 | R | N | B | Q | K | B | N | R | 8
  +---+---+---+---+---+---+---+---+
7 | P | P | P | P | P | P | P | P | 7
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   | 6
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   | 5
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   |   |   | 4
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   | 3
  +---+---+---+---+---+---+---+---+
2 | p | p | p | p | p | p | p | p | 2
  +---+---+---+---+---+---+---+---+
1 | r | n | b | q | k | b | n | r | 1
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h 

White's Move
> 
```

## Goal #3:  Implement the ability for White and Black to make moves via the CLI
When a new game is launched, the CLI prompts the White player to enter their first move.  However, the 'move' command is not currently implemented.  Your goal in this step is to implement the 'move' command.

Note that, traditionally, chess has a variety of ways to indicate moves.  For this exercise, we recommend you implement a system that represents a move as "move {from} {to}".

The CLI already includes a _move_ command, but it too is unimplemented.  When you are finished, you should be able to issue a _move_ command and have it reflected in the display of the game board:

```Shell
...
White's Move
> move d2 d4
    a   b   c   d   e   f   g   h  
  +---+---+---+---+---+---+---+---+
8 | R | N | B | Q | K | B | N | R | 8
  +---+---+---+---+---+---+---+---+
7 | P | P | P | P | P | P | P | P | 7
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   | 6
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   | 5
  +---+---+---+---+---+---+---+---+
4 |   |   |   | p |   |   |   |   | 4
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   | 3
  +---+---+---+---+---+---+---+---+
2 | p | p | p |   | p | p | p | p | 2
  +---+---+---+---+---+---+---+---+
1 | r | n | b | q | k | b | n | r | 1
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h  

Black's Move
> 
```

## Goal #4:  Detect when the game is over via checkmate
The game should be able to detect when the game is over, either from a Checkmate or a Draw.  You can ignore the possibility of a game ending by repetitive moves, or by there not being enough pieces left on either side to complete the game.  For instance, if the game were to follow the simple Fool's Mate game (http://en.wikipedia.org/wiki/Fool's_mate), the final move in the CLI will look like:

```Shell
Black's Move
> move d8 h4
    a   b   c   d   e   f   g   h  
  +---+---+---+---+---+---+---+---+
8 | R | N | B |   | K | B | N | R | 8
  +---+---+---+---+---+---+---+---+
7 | P | P | P | P |   | P | P | P | 7
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   | 6
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   | P |   |   |   | 5
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   | p | Q | 4
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   | p |   |   | 3
  +---+---+---+---+---+---+---+---+
2 | p | p | p | p | p |   |   | p | 2
  +---+---+---+---+---+---+---+---+
1 | r | n | b | q | k | b | n | r | 1
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h  

The game is over.  Congrats to Black.
> 
```

# Congrats!
If you've met the four goals above, congratulations!  Please send us a Pull Request so we can review your work.
