package gameOfLifeUI;

import gameOfLifeModel.Cell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UICell extends Rectangle {
    Cell gameOfLifeCell;
    int click_count = 0;

    // A UICell is a rectangle, so to create one, we call the parent's class constructor and pass in the required parameters.
    // A UICell also contains a cell, so we create a new cell whenever we create a new UICell
    public UICell(double width, double height, Paint fill){
        super(width, height, fill);
        this.gameOfLifeCell = new Cell() ;

        //define the cells behaviour when clicked by the user.
        // When clicked an odd number of times, the UICell's cell becomes alive and the UICell's colour changes to orange
        //When clcked an even number of times, the UICell's cell become dead and the UICell's colour change to dimgray
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                click_count += 1;

                if(click_count % 2 == 1){
                    getGameOfLifeCell().setCurrentState_isalive(true);
                    setFill(Color.ORANGE);
                }

                else{
                    getGameOfLifeCell().setCurrentState_isalive(false);
                    setFill(Color.DIMGRAY);
                }
            }
        });
    }

    public Cell getGameOfLifeCell(){
        return this.gameOfLifeCell;
    }

}
