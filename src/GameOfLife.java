import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Map;

public class GameOfLife extends Application {

    private LifeGrid lifeGrid;
    private static int x = 30;
    private static String fileName;
    private static int y = 30;
    private static Integer cycle;

    private static GraphicsContext graphicsContext;
    private Stage primaryStage;
    private static Text tGenerationCount;
    private static Button btPlay;
    private static Button btGeneration;
    private static Timeline updateTimeline;

    private final static int SQUARESIZE = 16;
    private final static Color CELLALIVE = Color.BLACK;
    private final static Color CELLDEAD = Color.WHITE;
    private final static double UPDATESCREENDURATION = 0.7;

    public void init() {
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

    public void start(Stage primaryStage) {
        if (fileName != null)
            lifeGrid = new LifeGrid(x, y, fileName);
        else
            lifeGrid = new LifeGrid(x, y);

        VBox vbRoot = new VBox(8); //Vertical column with spacing of 8
        HBox hbBottom = new HBox(5); //Horizontal row with spacing of 5
        tGenerationCount = new Text();
        Button btReset = new Button("Reset");
        btReset.setOnAction(new ResetButtonHandler());
        Button btClose = new Button("Close");
        btClose.setOnAction(new CloseButtonHandler());
        btPlay = new Button("Play");
        btPlay.setOnAction(new PlayButtonHandler());
        btGeneration = new Button("Next Generation");
        btGeneration_UpdateText();
        btGeneration.setOnAction(new GenerationButtonHandler());
        hbBottom.getChildren().addAll(tGenerationCount, btPlay, btGeneration, btReset, btClose);
        hbBottom.setAlignment(Pos.CENTER_RIGHT);

        Canvas canvas = new Canvas(x * SQUARESIZE, y * SQUARESIZE);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(CELLDEAD);
        graphicsContext.fillRect(0, 0, SQUARESIZE * x, SQUARESIZE * y);
        vbRoot.getChildren().addAll(canvas, hbBottom);
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(vbRoot));
        primaryStage.setTitle("Conway's Game of Life");
        lifeGrid.show();
        primaryStage.show();
    }

    public static void DrawCell(int x, int y, boolean isAlive) {
        if (isAlive)
            graphicsContext.setFill(CELLALIVE);
        else
            graphicsContext.setFill(CELLDEAD);
        graphicsContext.fillRect(x * SQUARESIZE, y * SQUARESIZE, SQUARESIZE, SQUARESIZE);
    }

    private void LoopedLifeGridUpdate(boolean continuous) {
        btGeneration.setDisable(true);
        btGeneration.setText("Compute Generation");
        updateTimeline = new Timeline();
        updateTimeline.setCycleCount(continuous ? Timeline.INDEFINITE : cycle);
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(UPDATESCREENDURATION),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        lifeGrid.run();
                    }
                });
        updateTimeline.getKeyFrames().add(keyFrame);
        if (!continuous)
            updateTimeline.setOnFinished(new UpdateFinished());
        updateTimeline.play();
    }

    private class UpdateFinished implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            btGeneration_UpdateText();
            btGeneration.setDisable(false);
        }
    }

    private void btGeneration_UpdateText() {
        if (cycle != null)
            btGeneration.setText("Compute Generation " + (lifeGrid.getCurrentGeneration() + cycle));
    }

    public static void tGenerationCount_SetText(String text)
    {
     tGenerationCount.setText(text);
    }

    private class ResetButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            updateTimeline.stop();
            btPlay.setText("Play");
            btPlay.setOnAction(new PlayButtonHandler());
            btGeneration_UpdateText();
            graphicsContext.setFill(CELLDEAD);
            graphicsContext.fillRect(0, 0, SQUARESIZE * x, SQUARESIZE * y);
            if (fileName != null)
                lifeGrid = new LifeGrid(x, y, fileName);
            else
                lifeGrid = new LifeGrid(x, y);
            lifeGrid.show();
        }
    }

    private class CloseButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            primaryStage.close();
        }
    }

    private class GenerationButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (cycle == null)
                lifeGrid.run();
            else
                LoopedLifeGridUpdate(false);
        }
    }

    private class PlayButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            LoopedLifeGridUpdate(true);
            btPlay.setText("Stop");
            btPlay.setOnAction(new StopButtonHandler());
        }


        private class StopButtonHandler implements EventHandler<ActionEvent> {
            public void handle(ActionEvent e) {
                updateTimeline.stop();
                btGeneration_UpdateText();
                btGeneration.setDisable(false);
                btPlay.setText("Play");
                btPlay.setOnAction(new PlayButtonHandler());
            }
        }
    }
}

