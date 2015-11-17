import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class LifeGrid
{
  private int[][] grid;
  private int currentGeneration;

  public LifeGrid(int x, int y, String filename)
  {
    File file = new File(filename);
    grid = new int[y][x];
    int lineCount = 0;

    try
    {
      Scanner scanNumOfLines = new Scanner(file);
      while (scanNumOfLines.hasNextLine())
      {
        lineCount++;
      }
      scanNumOfLines.close();
    }
    catch (FileNotFoundException e)
    {
        e.printStackTrace();
    }

    //Not valid input
    if (lineCount != y)
    {
      //Exception
    }

    Scanner scanner = new Scanner(file);
    for (int i = 0; i < y; i++)
    {
      String line = scanner.nextLine();
      for (int j = 0; j < x; j++)
      {
        if (line.charAt(i) == '*')
        {
          grid[i][j] = 1;
        }
        else
        {
          grid[i][j] = 0;
        }
      }
    }
    currentGeneration = 0;
  }

  public void Show()
  {
    System.out.println(currentGeneration);
    for (int i = 0; i < y; i++)
    {
      String line = "";
      for (int j = 0; j < x; j++)
      {
        if (grid[i][j] = 1)
        {
          line =+ "*";
        }
        else
        {
          line =+ " ";
        }
        System.out.println(line);
      }
    }
  }

  public int getHeight()
  {
    return grid[].length();
  }

  public int getWidth()
  {
    return grid[][].length();
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
      return "*"
    else
      return " ";
  }


}
