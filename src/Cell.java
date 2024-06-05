/*
 * The Cell Class represents each cell in the grid.
 * It Ssores the current and next state of the cell (alive or dead).
 */


public class Cell {
    private boolean isAlive;
    private boolean nextState;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    // The rules of the Game Of Life
    public void determineNextState(int aliveNeighbors) {
        if (isAlive) {
            nextState = aliveNeighbors == 2 || aliveNeighbors == 3;
        } else {
            nextState = aliveNeighbors == 3;
        }
    }

    public void updateState() {
        isAlive = nextState;
    }
}
