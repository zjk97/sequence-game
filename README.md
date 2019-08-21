# Sequence Game
Version 1.0.1 (8/20/19)
zjk97
JRE System Library: JavaSE-1.8

### Single Player Mode
- Color options: red(r), blue(b), green(g)
- Default user name: Rotty Coyo
- CPU moves: random
- Player order: random
- _Two_ decks of standard playing cards (excluding jokers)
- To recycle a card (both spots on the game board have been taken), select the card in hand by clicking it and then click on the card pile to discard it and draw a new card
- One-eyed jacks can remove ANY token on the board, **including your own**

### Multi-Player Mode
- Coming in the next version update

### SequenceLog
- Starting in version 1.0.0, a text file (SequenceLog<timestamp>.txt) will be generated every time the game is played in the directory of SequenceGame.jar
- If you experience any crashes and/or glitches, please consider sending back a copy of the log file for future improvement

### Updates
#### Version 1.0.1 (8/20/19)
- Changed how isGameOver method determines whether a one eyed jack has been played (from setting lastPlayedX to -1 to using boolean isOneEyedJackPlayed)
- Fixed the issue that writes to SequenceLog the incorrect x value of a player's move when an one eyed jack is played

### Previous Versions
- Version 1.0.0 (7/19/19)