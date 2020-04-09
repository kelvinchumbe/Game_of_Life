package gameOfLifeModel;

public class Cell {
    /// This is the state of the Cell right now
    boolean currentState_isalive;

    boolean futureState_isalive;

    public Cell(){
        this.currentState_isalive = false;
        this.futureState_isalive = false;
    }

    ///define getter and setter methods to retrive and update the two states of a cell
    public boolean getCurrentState_isalive(){
        return currentState_isalive;
    }

    public boolean getFutureState_isalive(){
        return futureState_isalive;
    }

    public void setCurrentState_isalive(boolean state){
        this.currentState_isalive = state;
    }

    public void setFutureState_isalive(boolean state){
        this.futureState_isalive = state;
    }

    public String toString(){
        return String.valueOf(this.currentState_isalive);
    }

}
