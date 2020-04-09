package gameOfLifeModel;

import gameOfLifeUI.UICell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CellGrid{
    int width;
    int height;

    UICell[][] gridUICell;
    Cell[][] grid;

    //Cellgrid constructor creates two grids: a grid of cells and a grid of UIcells both of dimensions width * height
    // It initializes the gridUICell array of arrays and the grid cell array of arrays with default values before the user interacts with the grid and updates them
    public CellGrid(int width, int height){
        this.width = width;
        this.height = height;

        //create an empty grid and empty UIcell grid
        this.grid = new Cell[height][width];
        this.gridUICell = new UICell[height][width];

        //create a new UICell, define it behaviour when clicked and add the cell contained in the UICell to the grid cell array
        for(int i=0; i < this.height; i++){
            for(int j=0; j < this.width; j++){
                //create a new UICell of dimensions 10 * 10 and set if default colout to dimgray
               UICell new_UICell = new UICell(10, 10, Color.DIMGRAY);

                gridUICell[i][j] = new_UICell;
                grid[i][j] = new_UICell.getGameOfLifeCell();
            }
        }
    }

    // compute the future state of each cell in your grid.
    //Go through each cell in the grid and determine its future state without updating its current state
    public void computeFutureState(){
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid[i].length; j++){
                //Get the number of living neighbours that each cell has
                int living_neighbours_count = livingNeighbours(i, j);

                //Based on the state of the current cell and the number of living neighbours surrounding it, determine whether a cell becomes alive or dies
                if(grid[i][j].getCurrentState_isalive()){
                    if(living_neighbours_count <= 1){
                        grid[i][j].setFutureState_isalive(false);
                    }
                    else if(living_neighbours_count >= 4){
                        grid[i][j].setFutureState_isalive(false);
                    }
                    else if(living_neighbours_count == 2 || living_neighbours_count == 3){
                        grid[i][j].setFutureState_isalive(true);
                    }
                }
                //if the cell is dead, determine whether it gets to be alive
                else{
                    if(living_neighbours_count == 3){
                        grid[i][j].setFutureState_isalive(true);
                    }
                }
            }
        }
    }

    // update the current state of each cell in your grid.
    //After comouting the state of each cell, go through the grid and update each cell to its predicted future state. Based on the updated current state,
    // color the cells UICell to Orange if alive or Dimgray if dead
    //Afterwards, set the cells future state to the default value which is false (dead)
    public void updateGrid(){
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid.length; j++){
                //update the cells current state from its future state value
                grid[i][j].setCurrentState_isalive(grid[i][j].getFutureState_isalive());
                if(grid[i][j].getCurrentState_isalive()){
                    //update the UICell's color to orange if alive
                    gridUICell[i][j].setFill(Color.ORANGE);
                }
                else{
                    //update the UICell to dimgray if dead
                    gridUICell[i][j].setFill(Color.DIMGRAY);
                }
                // update the future state of the cell to the default value
                grid[i][j].setFutureState_isalive(false);
            }
        }

    }

    //method to calculate the number of living neighbours of a cell and store them in an array. There's several positions that determine a cells number
    //of neighbours
    public int livingNeighbours(int i, int j){
        Cell[] neighbours;
        //if a cell is in the top row
        if(i == 0){
            //if a cell is on the left corner i.e i = 0 and j = 0
            if(j == 0){
                neighbours = new Cell[]{grid[i][j+1], grid[i+1][j], grid[i+1][j+1]};
            }
            //if a cell is on the right corner i.e i = 0 and j = length of second array - 1
            else if(j == grid[i].length - 1){
                neighbours = new Cell[]{grid[i][j-1], grid[i+1][j-1], grid[i+1][j]};
            }
            //if a cell is in the top row but not a corner cell
            else{
                neighbours = new Cell[]{grid[i][j-1], grid[i][j+1], grid[i+1][j-1], grid[i+1][j], grid[i+1][j+1]};
            }
        }

        //if a cell is  the bottom row
        else if(i == grid.length - 1){
            //if a cell is in the bottom left corner
            if(j == 0){
                neighbours = new Cell[]{grid[i-1][j], grid[i-1][j+1], grid[i][j+1]};
            }
            //if a cell is in the bottom right corner
            else if(j == grid[i].length - 1){
                neighbours = new Cell[]{grid[i-1][j-1], grid[i-1][j], grid[i][j-1]};
            }
            //if a cell is in the bottom row but not a corner cell
            else{
                neighbours = new Cell[]{grid[i-1][j-1], grid[i-1][j], grid[i-1][j+1], grid[i][j-1], grid[i][j+1]};
            }
        }

        //if a cell is on the left column
        else if(j == 0){
            neighbours = new Cell[]{grid[i-1][j], grid[i-1][j+1], grid[i][j+1], grid[i+1][j], grid[i+1][j+1]};
        }

        //if a cell is on the right column
        else if(j == grid[i].length - 1){
            neighbours = new Cell[]{grid[i-1][j-1], grid[i-1][j], grid[i][j-1], grid[i+1][j-1], grid[i+1][j]};
        }

        //if a cell is in the middle, neither on the top or bottom row ,or left and right column
        else{
            neighbours = new Cell[]{grid[i-1][j-1], grid[i-1][j], grid[i-1][j+1], grid[i][j-1], grid[i][j+1], grid[i+1][j-1], grid[i+1][j], grid[i+1][j+1]};
        }

        ///intialize the count of living neighbours to 0
        int living_neighbours_count = 0;

        ///Go through the neighbours and increment the living neighbours count by one if you encounter a living neighbour
        for(Cell neighbour_cell: neighbours){
            if(neighbour_cell.getCurrentState_isalive()){
                living_neighbours_count += 1;
            }
        }

        return living_neighbours_count;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Cell[][] getGrid(){
        return this.grid;
    }

    public UICell[][] getGridUICell(){
        return this.gridUICell;
    }
}
