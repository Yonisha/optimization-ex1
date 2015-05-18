package sudoku;

import sudoku.BruteForceSolver.BruteForceSolver;
import sudoku.GenericLpSolver.GenericLpSolver;
import sudoku.GenericLpSolver.LpSolver;
import sudoku.NonGenericLpSolver.NonGenericLpSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolverProgram {
    public static void main(String[] args) {

        // Change in order to replace ways of solution
        int questionNumber = 2;
        String inputFileName = "";//args[0];
        String outputFileName = "";//args[1];

        List<String> inputBoards = readInputLine(inputFileName);

        LpSolver lpSolver = new LpSolver();
        ISudokuSolver sudokuSolver = getSudokuSolver(questionNumber, lpSolver);
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        List<String> solutions = inputBoards.stream().map(b -> solveSingleBoard(sudokuSolver, b, sudokuDrawer)).collect(Collectors.toList());
        writeOutputFile(solutions, outputFileName);
    }

    private static String solveSingleBoard(ISudokuSolver sudokuSolver, String board, SudokuDrawer sudokuDrawer){
        sudokuDrawer.draw(board, "input");
        double[] solution = sudokuSolver.Solve(board);
        String normalizedSolution = normalizeSolution(solution);
        sudokuDrawer.draw(normalizedSolution, "output");
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

    private static ISudokuSolver getSudokuSolver(int questionNumber, LpSolver lpSolver){
        if (questionNumber == 1)
            return new BruteForceSolver();

        if (questionNumber == 2)
            return new GenericLpSolver(lpSolver);

        if (questionNumber == 3)
            return new NonGenericLpSolver(lpSolver);

        throw new IllegalArgumentException("We only have 3 different implementations!");
    }

    private static String normalizeSolution(double[] lpSolution){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lpSolution.length; i+=9) {
            for (int j = 0; j < 9; j++) {
                if (lpSolution[i+j] == 1.0){
                    stringBuilder.append(String.valueOf(j+1));
                    continue;
                }
            }
        }

        return stringBuilder.toString();
    }
}
