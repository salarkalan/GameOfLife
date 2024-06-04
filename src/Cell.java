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
