package sudoku;

import sudoku.BruteForceSolver.BruteForceSolver;
import sudoku.GenericLpSolver.GenericLpSolver;
import sudoku.NonGenericLpSolver.NonGenericLpSolver;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolverProgram {
    public static void main(String[] args) {

        // Change in order to replace ways of solution
        int questionNumber = 2;

        List<String> inputBoards = readInputLine(args[0]);
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        ISudokuSolver sudokuSolver = getSudokuSolver(questionNumber);
        double[] solution = sudokuSolver.Solve(inputBoards.get(0));
        sudokuDrawer.draw(solution);
    }

    private static List<String> readInputLine(String fileName){
        String inputLevel1 = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
        List<String> inputLines = new ArrayList<>();
        inputLines.add(inputLevel1);

        return inputLines;
    }

    private static ISudokuSolver getSudokuSolver(int questionNumber){
        if (questionNumber == 1)
            return new BruteForceSolver();

        if (questionNumber == 2)
            return new GenericLpSolver();

        if (questionNumber == 3)
            return new NonGenericLpSolver();

        throw new IllegalArgumentException("We only have 3 different implementations!");
    }
}
