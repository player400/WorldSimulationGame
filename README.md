World Simulation Game
___________________________________________________

This is a turn-based game on a 2D map, with either square or hexagonal tiles.
It uses Java Swing for GuI. It was developed as a university assignment.
Animals and plants are represented by numbers and letters.


How it works
________________________

After starting the game you will be prompted for map width and height and asked if you want hexagonal or square tiles.
On map animals and plants will be automatically spawned. Each of them has it's stats: strength and initiative.

Initiative determines who moves first in the turn. All plants have it set to 0.
Strength determines which organism will stay alive if they happen to meet on the same tile.

Plants spread by randomly extending to the next tile. Animals move randomly and replicate if the same kind of animal meets on one tile.

There is also one human on the map. Human's moves are not random (decided by the player).

Organism also have different skills/traits.
Tortoise can defend against animals stronger than itself (as long as it's strength is under 5) and the attacker must return to it's tile.
Antilope has 2-tile moving range and a 50% chance to escape from a fight with a stronger opponent.
Fox will never move to a tile taken by a stronger opponent.
Guarana will hive +2 strength to whatever animal eats it.
Some other plants will poison and kill any animal that tries to eat it.
Human has a special shield it can activate, that will scare off all animalns trying to attack it.
It lasts for 5 turns and then can't be used for another 5.


Controls:
________________________

You have 4 buttons, that enable you to: save the game, load a save of a game, go to next turn, activate human's shield.

After starting a turn, animals will move in orger determined by their stats. When it's human's turn, the player will be prompted for human's move.
All tiles the human can move to will light up in yellow. The tile that human will move to will light up in red. It can be changed using arrows on the keyboard.

There is also possiblility of ,,cheating" - you can add new organism on any empty tile by clicking the tile with left mouse, and then typing the symbol of animal to spawn.


Final notes
________________________

While writing this program I tried to adhere to OOP basic 4 principles (abstraction, polymorphism, encapsulation, inheritance) as closely as possible.
The main goal was to learn Java.


License
________________________

Software is thereby released under MIT Licence. See opensource.org for details. Copy of the license below:

Copyright 2023 Mateusz Nurczyński

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
