package sudoku;

import sudoku.BruteForceSolver.BruteForceSolver;
import sudoku.GenericLpSolver.GenericLpSolver;
import sudoku.GenericLpSolver.LpSolver;
import sudoku.NonGenericLpSolver.NonGenericLpSolver;
import sudoku.utils.SudokuDrawer;
import sudoku.utils.TimeFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolverProgram {
    public static void main(String[] args) {

        // Change in order to replace ways of solution
        WaysOfSolution waysOfSolution = WaysOfSolution.GENERIC;

        if (args.length != 2)
        {
            System.out.println("Usage:");
            System.out.println("<InputFile> <OutputFile>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        List<String> inputBoards = readInputLine(inputFileName);

        LpSolver lpSolver = new LpSolver();

        ISudokuSolver sudokuSolver = getSudokuSolver(waysOfSolution, lpSolver);
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        long startTime = System.currentTimeMillis();
        List<String> solutions = inputBoards.stream().map(b -> solveSingleBoard(sudokuSolver, b)).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total time: " + TimeFormatter.format(endTime - startTime));

        for (int i = 0; i < inputBoards.size(); i++) {
            sudokuDrawer.draw(inputBoards.get(i), "input" + (i+1));
            sudokuDrawer.draw(solutions.get(i), "output" + (i+1));
        }

        writeOutputFile(solutions, outputFileName);
    }

    private static String solveSingleBoard(ISudokuSolver sudokuSolver, String board){
        double[] solution = sudokuSolver.Solve(board);
        String normalizedSolution = normalizeSolution(solution);
        return normalizedSolution;
    }

    private static List<String> readInputLine(String inputFileName){

        List<String> inputLines = new ArrayList<>();
        BufferedReader bufferedReader = null;
        String currentLine = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFileName));
            while ((currentLine = bufferedReader.readLine()) != null) {
                inputLines.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

//        String inputLevel1 = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
//        inputLines.add(inputLevel1);

        return inputLines;
    }

    private static void writeOutputFile(List<String> solutions, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFileName), "utf-8"))) {
            solutions.stream().forEach(s -> {
                try {
                    writer.write(s + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ISudokuSolver getSudokuSolver(WaysOfSolution waysOfSolution, LpSolver lpSolver){
        if (waysOfSolution == WaysOfSolution.BRUTEFORCE)
            return new BruteForceSolver();

        if (waysOfSolution == WaysOfSolution.GENERIC)
            return new GenericLpSolver(lpSolver);

        if (waysOfSolution == WaysOfSolution.NONGENERIC)
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
