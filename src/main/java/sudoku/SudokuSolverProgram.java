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

        if (args.length != 3)
        {
            System.out.println("Usage:");
            System.out.println("<InputFile> <OutputFile> <Model>");
            System.out.println("You must enter 1-3 as the third argument");
            System.out.println("1 for Brute Force");
            System.out.println("2 for Generic LP");
            System.out.println("3 for Non Generic LP");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        int model = Integer.parseInt(args[2]);
        WaysOfSolution waysOfSolution = WaysOfSolution.fromInteger(model);
        if (waysOfSolution == null){
            System.out.println("You must enter 1-3 as the third argument");
            System.out.println("1 for Brute Force");
            System.out.println("2 for Generic LP");
            System.out.println("3 for Non Generic LP");
            System.exit(1);
        }

        File file = new File(inputFileName);
        if (!file.exists()){
            System.out.println("The input file: " + inputFileName + " doesn't exist!");
            System.exit(1);
        }

        List<String> inputBoards = readInputLine(inputFileName);

        LpSolver lpSolver = new LpSolver();

        ISudokuSolver sudokuSolver = getSudokuSolver(waysOfSolution, lpSolver);
        SudokuDrawer sudokuDrawer = new SudokuDrawer();

        long startTime = System.currentTimeMillis();
        List<double[]> solutions = sudokuSolver.Solve(inputBoards);
        List<String> solutionsAsString = solutions.stream().map(s -> verifySolutionAndGetAsString(s)).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total time: " + TimeFormatter.format(endTime - startTime));

        for (int i = 0; i < inputBoards.size(); i++) {
            sudokuDrawer.draw(inputBoards.get(i), "input" + (i+1));
            sudokuDrawer.draw(solutionsAsString.get(i), "output" + (i+1));
        }

        writeOutputFile(solutionsAsString, outputFileName);
    }

    private static String verifySolutionAndGetAsString(double[] solution){

        boolean result = Verifier.verifyResult(solution, true);
        if (!result) {
            System.out.println("-------> Wrong solution !!!! <-------");
        }

        String normalizedSolution = normalizeSolution(solution);
        return normalizedSolution;
    }

    private static List<String> readInputLine(String inputFileName){

        List<String> inputLines = new ArrayList<>();
//
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

//        String input= "000075400000000008080190000300001060000000034000068170204000603900000020530200000";
//        inputLines.add(input);

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
