class LifeGridTest
{
  public static void main (String[] args)
  {
    // Args: x size, y size, filename, number of generations
    int x = (Integer.parseInt(args[0])); //!= null) ? Integer.parseInt(args[0]) :  10;
    int y = (Integer.parseInt(args[1])); // != null) ? Integer.parseInt(args[1]) :  10;
    String filename = (args[2] != null && !args[2].isEmpty()) ? args[2] :  "test.txt";

    LifeGrid lifeGrid = new LifeGrid(x, y, filename);
    lifeGrid.show();

    System.out.println("******Neighbours:" + lifeGrid.neighbours(1, 0) + "******");
    if(args.length > 3 && args[3] != null)
    {
      for (int i = 0; i < (Integer.parseInt(args[3])-1); i++)
      {
        lifeGrid.run();
        System.out.println("******Neighbours:" + lifeGrid.neighbours(1, 0) + "******");
      }
    }
  }
}
