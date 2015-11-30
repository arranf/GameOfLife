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

    if(args.length > 3 && args[3] != null)
    {
      for (int i = 0; i < (Integer.parseInt(args[3])-1); i++)
      {
        lifeGrid.run();
      }
    }
  }
}

//    _-====-__-======-__-========-_____-============-__
//            _(                                                 _)
//            OO(                                                   )_
//            0  (_                                                   _)
//            o0     (_                                                _)
//            o         '=-___-===-_____-========-___________-===-dwb-='
//            .o                                _________
//            . ______          ______________  |         |      _____
//            _()_||__|| ________ |            |  |_________|   __||___||__
//            (BNSF 1995| |      | |            | __Y______00_| |_         _|
//            /-OO----OO""="OO--OO"="OO--------OO"="OO-------OO"="OO-------OO"=P
//            #####################################################################
