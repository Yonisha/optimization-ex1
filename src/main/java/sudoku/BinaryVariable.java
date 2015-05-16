package sudoku;

public class BinaryVariable{

    private int row;
    private int column;
    private int depth;

    public BinaryVariable(int row, int column, int depth){
        this.row = row;
        this.column = column;
        this.depth = depth;
    }

    public int getDepth(){
        return this.depth;
    }

    @Override
    public String toString(){
        return row + "," + column + "," + depth;
    }
}
