package gameOfLifeTests;

import gameOfLifeModel.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell testcell = new Cell();

    @Test
    void testCell() {
        assertFalse(testcell.getCurrentState_isalive());
        testcell.setFutureState_isalive(true);
        assertFalse(testcell.getFutureState_isalive());
        assertTrue(testcell.getFutureState_isalive());
        testcell.setCurrentState_isalive(true);
        assertTrue(testcell.getCurrentState_isalive());
        assertFalse(testcell.getCurrentState_isalive());
    }

}