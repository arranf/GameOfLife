import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class LifeGrid
{
  private int[][] grid;

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
  }
}
