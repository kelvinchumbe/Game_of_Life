package gameOfLifeUI;

import gameOfLifeModel.CellGrid;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Each game instance needs to keep track of its cellGrid, the state of the game and the number of times the start button has been clicked so as to alter the state of the game
public class Main extends Application {
    static boolean gameRunning = false;
    int start_btn_clicks = 0;
    CellGrid cellGrid;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Life");

        //create a new cellGrid of dimensions 60 * 60
        cellGrid = new CellGrid(60, 60);

        //create a new gridpane. Set its hgap and vgap to 1 each and set it to the center of the window
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setAlignment(Pos.CENTER);

        //Add each UICell to the gridpane
        for(int i=0; i < cellGrid.getHeight(); i++){
            for(int j=0; j < cellGrid.getWidth(); j++){
                gridPane.add(cellGrid.getGridUICell()[i][j],j, i);
            }
        }

        //Create a next button that computes and updates the grid in one step
        Button next = new Button("NEXT");
        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cellGrid.computeFutureState();
                cellGrid.updateGrid();
            }
        });

        //Reset button resets the cellGrid to the initial state i.e all cells are dead
        Button reset = new Button("RESET");
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cellGrid = new CellGrid(60, 60);

                for(int i=0; i < cellGrid.getHeight(); i++){
                    for(int j=0; j < cellGrid.getWidth(); j++){
                        gridPane.add(cellGrid.getGridUICell()[i][j],j, i);
                    }
                }
            }
        });

        //Start button continuously computes and updates the grid until its clicked a second time to stop it
        //START button doesnt quite work as its expected yet. Will modify it and update the code

        Button start = new Button("START");
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               start_btn_clicks += 1;

               if(start_btn_clicks % 2 == 1){
                   gameRunning = true;
                   while(gameRunning){
                       start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent event) {
                               start_btn_clicks += 1;
                               if(start_btn_clicks % 2 == 0){
                                   gameRunning = false;
                               }
                               else{
                                   cellGrid.computeFutureState();
                                   cellGrid.updateGrid();
                               }
                           }
                       });


                   }
               }
            }
        });

        //create a borderpane object and set the grid to the center pane and buttons to the bottom pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        BorderPane bottom_borderPane = new BorderPane();
        borderPane.setBottom(bottom_borderPane);

        BorderPane bottom_center = new BorderPane();

        bottom_borderPane.setCenter(bottom_center);
        bottom_center.setLeft(next);
        bottom_center.setCenter(start);
        bottom_center.setRight(reset);

        Scene scene = new Scene(borderPane, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
