class LifeGridTest
{
  public static void main (String[] args)
  {
    LifeGrid lifeGrid = new LifeGrid(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
    lifeGrid.show();
    System.out.println("******Neighbours:" + lifeGrid.neighbours(2, 0) + "******");
    if(args.length > 3 && args[3] != null)
    for (int i = 0; i < (Integer.parseInt(args[3])); i++)
    {
      lifeGrid.run();
      System.out.println("******Neighbours:" + lifeGrid.neighbours(2, 0) + "******");
    }
  }
}
