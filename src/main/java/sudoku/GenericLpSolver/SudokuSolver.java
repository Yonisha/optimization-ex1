package sudoku.GenericLpSolver;

import lpsolve.LpSolveException;

public class SudokuSolver {
    public static void main(String[] args) {
        LPDefiner lpDefiner = new LPDefiner();
        try {
            lpDefiner.Test();
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}
