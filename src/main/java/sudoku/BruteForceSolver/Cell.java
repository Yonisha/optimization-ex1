package sudoku.BruteForceSolver;

public class Cell{
    private int value;
    private boolean isFixed;

    public Cell(int value, boolean isFixed)
    {
        this.value = value;
        this.isFixed = isFixed;
    }

    public boolean isFixed(){
        return this.isFixed;
    }

    public int getValue(){
        return this.value;
    }

    public void initValue(){
        this.value = 0;
    }

    public void incrementValue(){
        this.value++;
    }
}
