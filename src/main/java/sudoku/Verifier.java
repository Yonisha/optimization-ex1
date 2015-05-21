package sudoku;

import sudoku.GenericLpSolver.Constraint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yonisha on 5/18/2015.
 */
public class Verifier {

    public static boolean verifyResult(double[][][] result, boolean full) {
        return checkForCells(result, full) && checkForRows(result, full) && checkForColumns(result, full) && checkForSquares(result, full);
    }

    public static boolean verifyResult(double[] result, boolean full) {
        double[][][] doubles = buildMatrix(result);

        return verifyResult(doubles, full);
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

    private static boolean checkForCells(double[][][] matrix, boolean full) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[i][j][k];
                }

                if (sum > 1) {
                    return false;
                } if (full && sum != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkForRows(double[][][] matrix, boolean full) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[i][k][j];
                }

                if (sum > 1) {
                    return false;
                } else if (full && sum != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkForSquares(double[][][] matrix, boolean full) {

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
                    } else if (full && sum != 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean checkForColumns(double[][][] matrix, boolean full) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int sum = 0;
                for (int k = 0; k <9; k++) {
                    sum += matrix[k][i][j];
                }

                if (sum > 1) {
                    return false;
                } else if (full && sum != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}