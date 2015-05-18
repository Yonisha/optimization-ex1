package sudoku;

import sudoku.BruteForceSolver.BruteForceSolver;
import sudoku.GenericLpSolver.GenericLpSolver;
import sudoku.NonGenericLpSolver.NonGenericLpSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolverProgram {
    public static void main(String[] args) {

        // Change in order to replace ways of solution
        int questionNumber = 2;
        String inpurFileName = ""; //args[0];
        String outpurFileName = ""; //args[1];

        List<String> inputBoards = readInputLine(inpurFileName);

        ISudokuSolver sudokuSolver = getSudokuSolver(questionNumber);
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        List<String> solutions = inputBoards.stream().map(b -> solveSingleBoard(sudokuSolver, b, sudokuDrawer)).collect(Collectors.toList());
        writeOutputFile(solutions, outpurFileName);
    }

    private static String solveSingleBoard(ISudokuSolver sudokuSolver, String board, SudokuDrawer sudokuDrawer) {
        double[] solution = sudokuSolver.Solve(board);

        // TODO: verify according to input as well?
        boolean result = Verifier.verifyResult(solution);

        if (!result) {
            System.out.println("-------> Wrong solution !!!! <-------");
        }

        sudokuDrawer.draw(solution);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < solution.length; i++) {
            stringBuilder.append(String.valueOf(solution[i]));
        }

        return stringBuilder.toString();
    }

    private static List<String> readInputLine(String inputFileName){
        String inputLevel1 = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
        List<String> inputLines = new ArrayList<>();
        inputLines.add(inputLevel1);

        return inputLines;
    }

    private static void writeOutputFile(List<String> solutions, String outputFileName) {

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
