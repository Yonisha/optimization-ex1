package sudoku;

public class SudokuDrawer{
    public void draw(double[] board){
        for (int i = 1; i <= board.length; i++) {
            if (board[i - 1] != 0.0) {
                System.out.print(i % 9 + 1);
            }

            if (i % 81 == 0)
                System.out.println();
        }
    }
}
