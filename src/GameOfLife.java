import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Map;

public class GameOfLife extends Application {

    private LifeGrid lifeGrid;
    private static int x = 10;
    private static String fileName;
    private static int y = 10;
    private static int cycle;

    private static GraphicsContext graphicsContext;
    private Stage primaryStage;

    private final static int SQUARESIZE = 16;
    private final static Color CELLALIVE = Color.BLACK;
    private final static Color CELLDEAD = Color.WHITE;

    public void init()
    {
        Parameters parameters = getParameters();
        Map<String, String> namedParameters = parameters.getNamed();
        if (namedParameters.containsKey("x"))
            x = Integer.parseInt(namedParameters.get("x"));
        if (namedParameters.containsKey("y"))
            y = Integer.parseInt(namedParameters.get("y"));
        if (namedParameters.containsKey("file"))
            fileName = namedParameters.get("file");
        if (namedParameters.containsKey("runfor"))
            cycle = Integer.parseInt(namedParameters.get("runfor"));
    }

public void start(Stage primaryStage){
    if (fileName != null)
        lifeGrid = new LifeGrid(x, y, fileName);
    else
        lifeGrid = new LifeGrid(x, y);

    VBox vbRoot = new VBox(8); //Vertical column with spacing of 8
//    HBox hbButtons = new HBox(5); //Horizontal row with spacing of 5
//    Button btReset = new Button("Reset");
//    btReset.setOnAction(new resetButtonHandler());
//    Button btClose = new Button("Close");
//    btClose.setOnAction(new closeButtonHandler());
//    hbButtons.getChildren().addAll(btReset, btClose);
//    hbButtons.setAlignment(Pos.CENTER_RIGHT);

    Canvas canvas = new Canvas((x+1)*SQUARESIZE, (y+1)*SQUARESIZE);
    graphicsContext  = canvas.getGraphicsContext2D();
    graphicsContext.setFill(CELLDEAD);
    graphicsContext.fillRect(0, 0, SQUARESIZE*x,SQUARESIZE*y );
    vbRoot.getChildren().addAll(canvas//, hbButtons
            );
    this.primaryStage = primaryStage;
    primaryStage.setScene(new Scene(vbRoot));
    primaryStage.setTitle("Conway's Game of Life");
    primaryStage.show();
    }

    public static void DrawCell (int x, int y, boolean IsAlive){
        if (IsAlive)
            graphicsContext.setFill(CELLALIVE);
        else
            graphicsContext.setFill(CELLDEAD);
        graphicsContext.fillRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
    }

//public static void main(String[]args)
//        {
//        // Args: x size, y size, filename, number of generations
//        LifeGrid lifeGrid=new LifeGrid(x,y,fileName);
//        lifeGrid.show();
//
//        if(args.length>3&&args[3]!=null)
//        {
//        for(int i=0;i<(Integer.parseInt(args[3])-1);i++)
//        {
//        lifeGrid.run();
//        }
//        }
//        }

//    private class resetButtonHandler implements EventHandler<ActionEvent> {
//    }
//
//    private class closeButtonHandler implements EventHandler<ActionEvent> {
//    }
}

