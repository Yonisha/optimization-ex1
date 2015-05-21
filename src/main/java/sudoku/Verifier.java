package sudoku;

import sudoku.BruteForceSolver.Cell;
import sudoku.GenericLpSolver.Constraint;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.List;

public class Verifier {
    public static boolean verify(List<Cell> currentBoardStatus){

        Cell[][] board = InputBoardParser.parseCellsToTwoDimensionalArray(currentBoardStatus);
        if (!rowsAreValid(board))
            return false;

        if (!columnsAreValid(board))
            return false;

        if (!squaresAreValid(board))
            return false;

        return true;
    }

    private static boolean rowsAreValid(Cell[][] board){
        for (int i = 0; i < 9; i++) {
            //one row
            List<Integer> seen = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                int currentInput = board[i][j].getValue();
                if (currentInput == 0)
                    continue;

                if (seen.contains(currentInput))
                    return false;

                seen.add(currentInput);
            }
        }
        return true;
    }

    private static boolean columnsAreValid(Cell[][] board){
        for (int i = 0; i < 9; i++) {
            //one column
            List<Integer> seen = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                int currentInput = board[j][i].getValue();
                if (currentInput == 0)
                    continue;

                if (seen.contains(currentInput))
                    return false;

                seen.add(currentInput);
            }
        }
        return true;
    }

    private static boolean squaresAreValid(Cell[][] board){
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                //one square
                List<Integer> seen = new ArrayList<>();

                int currentInput = board[i][j].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+1][j].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+2][j].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i][j+1].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i][j+2].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+1][j+1].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+1][j+2].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+2][j+1].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

                currentInput = board[i+2][j+2].getValue();
                if (currentInput != 0){
                    if (seen.contains(currentInput))
                        return false;
                    seen.add(currentInput);
                }

            }
        }
        return true;
    }

    public static boolean verifyResult(double[] result) {
        double[][][] doubles = buildMatrix(result);

        boolean success = checkForCells(doubles);
        success &= checkForRows(doubles);
        success &= checkForColumns(doubles);
        success &= checkForSquares(doubles);

        return success;
    }

    private static double[][][] buildMatrix(double[] result) {
        double[][][] matrix = new double[9][9][9];

        for (int i = 0; i < result.length; i++) {
            int row = i / 81;
            int column = (i / 9) % 9;
            int height = i % 9;

            matrix[row][column][height] = result[i];
        }

        return matrix;
    }

    private static boolean checkForCells(double[][][] matrix) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[i][j][k];
                }

                if (sum > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkForRows(double[][][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[i][k][j];
                }

                if (sum > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkForSquares(double[][][] matrix) {

        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {

                for (int k = 0; k < 9; k++) {
                    int sum = 0;

                    sum += matrix[i][j][k];
                    sum += matrix[i+1][j][k];
                    sum += matrix[i+2][j][k];
                    sum += matrix[i][j+1][k];
                    sum += matrix[i][j+2][k];
                    sum += matrix[i+1][j+1][k];
                    sum += matrix[i+1][j+2][k];
                    sum += matrix[i+2][j+1][k];
                    sum += matrix[i+2][j+2][k];

                    if (sum > 1 ) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean checkForColumns(double[][][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[k][i][j];
                }

                if (sum > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
