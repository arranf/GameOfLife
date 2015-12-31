Prerequisites:
Compile LifeGrid and GameOfLife with java

Run GameOfLife with the command "java GameOfLife"

GameOfLife takes the following parameters, all of which are optional.

GameOfLife --x=50 --y=50 --runfor=10 --file="test_blinker.txt"

Where:
x is the dimensions of the width of the grid
y is the dimensions of the height of the grid
file is a *string* which is the source file to initially populate the grid
runfor is the number of generations that should be calculated at a time

If no paramters are provided the program will default to a 30 by 30 grid calculating one generation at a time with a random initial seed.