package gameOfLifeTests;

import gameOfLifeModel.CellGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellGridTest {
CellGrid testcellgrid = new CellGrid(5, 5);


    @Test
    void testCellGrid(){

    }

    @Test
    void testgetWidth(){
        assertEquals(5, testcellgrid.getWidth());
        assertEquals(4, testcellgrid.getWidth());
    }

    @Test
    void testgetHeight(){
        assertEquals(5, testcellgrid.getHeight());
        assertEquals(7, testcellgrid.getHeight());
    }

    @Test
    void testcomputeFutureState(){
        testcellgrid.getGrid()[1][3].setCurrentState_isalive(true);
        testcellgrid.getGrid()[1][4].setCurrentState_isalive(true);
        testcellgrid.getGrid()[2][3].setCurrentState_isalive(true);
        testcellgrid.getGrid()[0][4].setCurrentState_isalive(true);
        testcellgrid.computeFutureState();

        assertTrue(testcellgrid.getGrid()[1][4].getFutureState_isalive());
        assertTrue(testcellgrid.getGrid()[2][3].getFutureState_isalive());
        assertFalse(testcellgrid.getGrid()[4][1].getFutureState_isalive());
    }

    @Test
    void testUpdateGrid(){
        testcellgrid.updateGrid();

        assertFalse(testcellgrid.getGrid()[4][1].getCurrentState_isalive());
        assertTrue(testcellgrid.getGrid()[2][3].getCurrentState_isalive());
        assertFalse(testcellgrid.getGrid()[2][3].getCurrentState_isalive());
    }

    @Test
    void testLivingNeighbours(){
        assertEquals(3, testcellgrid.livingNeighbours(1,4));
        assertEquals(0, testcellgrid.livingNeighbours(0,2));
        assertEquals(1, testcellgrid.livingNeighbours(3,3));
    }

}

