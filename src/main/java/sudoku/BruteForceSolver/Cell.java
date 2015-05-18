package sudoku.BruteForceSolver;

public class Cell{
    private int value;
    private boolean isFixed;

    public Cell(int value, boolean isFixed)
    {
        this.value = value;
        this.isFixed = isFixed;
    }
}
