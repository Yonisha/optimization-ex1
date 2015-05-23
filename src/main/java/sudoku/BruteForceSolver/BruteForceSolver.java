package sudoku.BruteForceSolver;

import sudoku.ISudokuSolver;
import sudoku.Verifier;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver implements ISudokuSolver {
    double[][][] matrix = new double[9][9][9];


    @Override
    public List<double[]> Solve(List<String> inputBoards) {
        List<double[]> results = new ArrayList<>();
        for (String board : inputBoards) {
            double[] result = solve(board);
            results.add(result);
        }

        return results;
    }

    public double[] solve(String inputBoard){

        InputBoardParser inputBoardParser = new InputBoardParser();
        List<Integer> parsedBoard = inputBoardParser.parse(inputBoard);

        fillCellRecursive(parsedBoard, 0);

        // TODO dup code - we already have that code somewhere.
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    result.add(parsedBoard.get(i * 9 + j) == k ? 1d : 0);
                }
            }
        }

        double[] doubles = result.stream().mapToDouble(d -> d).toArray();

        return doubles;
    }

    private boolean fillCellRecursive(List<Integer> parsedBoard, int currentIndex){
        if (currentIndex == 81) {
            return false;
        }

        if (parsedBoard.get(currentIndex) != 0) {
            matrix[currentIndex / 9][currentIndex % 9][parsedBoard.get(currentIndex) - 1] = 1;
            return fillCellRecursive(parsedBoard, currentIndex + 1);
        } else {
            for (int i = 0; i < 9; i++) {
                matrix[currentIndex / 9][currentIndex % 9][i] = 1;

                boolean valid = Verifier.verifyResult(matrix, false);
                if (valid) {
                    if (Verifier.verifyResult(matrix, true)) {
                        System.out.println("Done");
                        return true;
                    }

                    boolean success = fillCellRecursive(parsedBoard, currentIndex + 1);

                    if (success) {
                        return true;
                    }
                }

                matrix[currentIndex / 9][currentIndex % 9][i] = 0;
            }
        }

        return false;
    }
}