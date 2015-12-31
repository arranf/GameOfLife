import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

//grids are [y][x]
//Looping through each row, then each column

class LifeGrid
{
  private int[][] grid;
  private int[][] neighboursGrid;
  private int currentGeneration;

  public LifeGrid(int x, int y, String filename)
  {
    File file = new File(filename);
    grid = new int[y][x];
    neighboursGrid = new int[y][x];
    currentGeneration = 0;

    try {
      Scanner scanner = new Scanner(file);
      for (int i = 0; i < y; i++)
      {
        String line = "";
        if (scanner.hasNextLine())
          line = scanner.nextLine();
        for (int j = 0; j < x; j++)
        {
          if (j < line.length() && (line.charAt(j) == '*' || line.charAt(j) == 'O') )
          {
            //grid[y][x]
            grid[i][j] = 1;
          }
          else
          {
            grid[i][j] = 0;
          }
        }
      }
    }

    catch (FileNotFoundException e) {

        e.printStackTrace();
        System.out.println("Can't find the specified input, reverting to random data.");
        randomGrid();
    }

    currentGeneration = 0;

  }

  public LifeGrid(int x, int y)
  {
    grid = new int[y][x];
    neighboursGrid = new int[y][x];
    currentGeneration = 0;
      randomGrid();
  }

    public void randomGrid()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (Math.random() < 0.5)
                {
                    //grid[y][x]
                    grid[i][j] = 1;
                } else
                {
                    grid[i][j] = 0;
                }
            }
        }
    }

  public void show()
  {
    GameOfLife.tGenerationCount_SetText("Current Generation: " + currentGeneration);
    for (int i = 0; i < grid.length; i++)
    {
      for (int j = 0; j < grid[i].length; j++)
      {
        if (grid[i][j] == 1)
        {
          GameOfLife.DrawCell(j, i, true);
        }
        else
        {
            GameOfLife.DrawCell(j, i, false);
        }
      }
    }
  }

  public int getHeight()
  {
    return grid.length;
  }

  public int getWidth()
  {
    return grid[0].length;
  }

  public int getCurrentGeneration()
  {
    return currentGeneration;
  }

  public int getCell(int x, int y)
  {
    return grid[y][x];
  }

  public boolean isCellFilled(int x, int y)
  {
    if (getCell(x, y) == 1)
    return true;
    else
    return false;
  }

  public int numberOfNeighbours(int x, int y) //Search the surrounding square of the square, sensitive to array limits
  {
    int count = 0;
    for (int i = Math.max(y-1, 0); i <= Math.min(y+1, grid.length-1); i++)
    {
      for (int j = Math.max(x-1, 0); j <= Math.min(x+1, grid[0].length-1); j++)
      {
        if (grid[i][j] == 1)
          count++;
      }
    }
    if (grid[y][x] == 1)
      return count-1;
    else
      return count;
  }

  public int[][] computeRules(int x, int y, int[][] newGrid)
  {
    if (isCellFilled(x,y) && (neighboursGrid[y][x] < 2 || neighboursGrid[y][x] > 3))
      newGrid[y][x] = 0;
    else if (!isCellFilled(x,y) && neighboursGrid[y][x] == 3)
      newGrid[y][x] = 1;
    return newGrid;
  }

  public int[][] generation()
  {
    int[][] newGrid = new int[grid.length][grid[0].length];
    System.arraycopy(grid, 0, newGrid, 0, grid.length);

    //Generate an array containing the number of neighbours for each cell of grid
    for (int i = 0; i < grid.length; i++)
    {
      for (int j = 0; j < grid[i].length; j++)
      {
        neighboursGrid[i][j] = numberOfNeighbours(j, i);
      }
    }

    for (int i = 0; i < grid.length; i++)
    {
      for (int j = 0; j < grid[i].length; j++)
      {
        computeRules(j, i, newGrid);
      }
    }

    return newGrid;
  }

  public void run()
  {
    grid = generation();
    currentGeneration++;
    show();
  }

  }
