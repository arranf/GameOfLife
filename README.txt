Prerequisites:
Compile LifeGrid and LifeGridTest with java

Run LifeGridTest with the command "java LifeGridTest"

LifeGridTest takes the following parameters (which if used must all be provided):

LifeGridTest x y file.txt n

Where:
x is the dimensions of the width of the grid
y is the dimensions of the height of the grid
file.txt is a *string* which is the source file
n is the number of generations to produce

If no paramters are provided the following defaults will be used

LifeGridTest 10 10 "test.txt" 3