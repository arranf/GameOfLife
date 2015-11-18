import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math.*;

class LifeGrid
{
  private int[][] grid;
  private int currentGeneration;

  public LifeGrid(int x, int y, String filename)
  {
    File file = new File(filename);
    grid = new int[y][x];
    // int lineCount = 0;
    // try
    // {
    //   Scanner scanNumOfLines = new Scanner(file);
    //   while (scanNumOfLines.hasNextLine())
    //   {
    //     lineCount++;
    //   }
    //   scanNumOfLines.close();
    //   //Not valid input
    //   if (lineCount != y)
    //   {
    //     //Exit, bad file
    //   }
    // }
    // catch (FileNotFoundException e) {
    //   e.printStackTrace();
    // }

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
    }

    currentGeneration = 0;
  }

  public void show()
  {
    System.out.println("Current Generation: " + currentGeneration);
    String topLine = " ";
    for (int i = 0; i < grid.length; i++)
    {
      topLine += i;
    }
    System.out.println(topLine);
    for (int i = 0; i < grid.length; i++)
    {
      String line = ""+i; // Vertical
      for (int j = 0; j < grid[i].length; j++)
      {
        if (grid[i][j] == 1)
        {
          line += "*";
        }
        else
        {
          line += " ";
        }
      }
      System.out.println(line);
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

  public String getCellString(int x, int y)
  {
    if (getCell(x, y) == 1)
    return "*";
    else
    return " ";
  }

  public boolean isCellFilled(int x, int y)
  {
    if (getCell(x, y) == 1)
    return true;
    else
    return false;
  }

  public int neighbours(int x, int y) //Search the surrounding square of the square, sensitive to array limits
  {
    int count = -1; // It counts itself
    for (int i = Math.max(y-1, 0); i <= Math.min(y+1, grid.length-1); i++)
    {
      for (int j = Math.max(x-1, 0); j <= Math.min(x+1, grid[0].length-1); j++)
      {
        if (grid[i][j] == 1)
          count++;
      }
    }

    return count-1;
  }

  public int[][] computeRules(int x, int y, int numberOfNeighbours, boolean filled, int[][] grid)
  {
    if (filled && numberOfNeighbours < 2 || numberOfNeighbours > 3)
      grid[y][x] = 0;
    else if (!filled && (numberOfNeighbours == 2 || numberOfNeighbours == 3))
      grid[y][x] = 1;
    return grid;
  }

  public int[][] generation()
  {
    int[][] newGrid = new int[grid.length][grid[0].length];
    System.arraycopy(grid, 0, newGrid, 0, grid.length);

    for (int i = 0; i < grid.length; i++)
    {
      for (int j = 0; j < grid[i].length; j++)
      {
        newGrid = computeRules(j, i, this.neighbours(j, i), this.isCellFilled(j, i), newGrid);
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

  public void runFor(int iterations)
  {
    for (int i = 0; i < iterations; i++)
    {
      run();
    }
  }

  }
