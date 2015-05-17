package sudoku;

import lpsolve.LpSolveException;
import sudoku.BruteForceSolver.BruteForceSolver;
import sudoku.GenericLpSolver.GenericLpSolver;
import sudoku.NonGenericLpSolver.NonGenericLpSolver;

public class SudokuSolver {
    public static void main(String[] args) {

        String inputLevel1 = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        // Generic LP solution
        GenericLpSolver genericLpSolver = new GenericLpSolver();
        try {
            double[] solution = genericLpSolver.Solve(inputLevel1);
            sudokuDrawer.draw(solution);
        } catch (LpSolveException e) {
            e.printStackTrace();
        }

        // Non-generic LP solution
        NonGenericLpSolver nonGenericLpSolver = new NonGenericLpSolver();
        try {
            double[] solution = nonGenericLpSolver.Solve(inputLevel1);
            sudokuDrawer.draw(solution);
        } catch (LpSolveException e) {
            e.printStackTrace();
        }

        // Brute force solution
        BruteForceSolver bruteForceSolver = new BruteForceSolver();
        double[] solution = bruteForceSolver.Solve(inputLevel1);
        sudokuDrawer.draw(solution);
    }
}
