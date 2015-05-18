package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;

import java.util.ArrayList;
import java.util.List;

public class NonGenericConstraintsCreator {

    public List<Constraint> create(String inputBoard, int numberOfVariables) {
        char[] chars = inputBoard.toCharArray();
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            int currentChar = Integer.parseInt(chars[i] + "");
            input.add(currentChar);
        }

        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(createForCells(input));
        constraints.addAll(createForRows(input));
        constraints.addAll(createForColumns(input));
        constraints.addAll(createForSquares(input));
        return constraints;
    }

    public List<Constraint> createForCells(List<Integer> input) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                Integer currentValue = input.get(i* 9 + j);
                if (currentValue != 0)
                    coefficients[i][j][currentValue-1] = 1;
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForRows(List<Integer> input) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                Integer currentValue = input.get(i* 9 + j);
                if (currentValue != 0)
                    coefficients[i][currentValue-1][j] = 1;
//                for (int k = 0; k < 9; k++) {
//                    Integer currentValue = input.get(i* 9 + k);
//                    if (currentValue != j)
//                        coefficients[i][k][j] = 1;
//                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForColumns(List<Integer> input) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                Integer currentValue = input.get(i* 9 + j);
                if (currentValue != 0)
                    coefficients[currentValue-1][i][j] = 1;
//                for (int k = 0; k < 9; k++) {
//                    coefficients[k][i][j] = 1;
//                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForSquares(List<Integer> input) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i += 3) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j += 3) {
                int[][][] coefficients = new int[9][9][9];
                Integer currentValue = input.get(i* 9 + j);
                if (currentValue != 0){
                    coefficients[i][j][currentValue-1] = 1;
                    coefficients[i + 1][j][currentValue-1] = 1;
                    coefficients[i + 2][j][currentValue-1] = 1;
                    coefficients[i][j + 1][currentValue-1] = 1;
                    coefficients[i][j + 2][currentValue-1] = 1;
                    coefficients[i + 1][j + 1][currentValue-1] = 1;
                    coefficients[i + 1][j + 2][currentValue-1] = 1;
                    coefficients[i + 2][j + 1][currentValue-1] = 1;
                    coefficients[i + 2][j + 2][currentValue-1] = 1;
                }

                constraints.add(new Constraint(coefficients, 1));

//                for (int k = 0; k < 9; k++) {
//                    // one constraint
//                    int[][][] coefficients = new int[9][9][9];
//                    coefficients[i][j][k] = 1;
//                    coefficients[i + 1][j][k] = 1;
//                    coefficients[i + 2][j][k] = 1;
//                    coefficients[i][j + 1][k] = 1;
//                    coefficients[i][j + 2][k] = 1;
//                    coefficients[i + 1][j + 1][k] = 1;
//                    coefficients[i + 1][j + 2][k] = 1;
//                    coefficients[i + 2][j + 1][k] = 1;
//                    coefficients[i + 2][j + 2][k] = 1;
//
//                    constraints.add(new Constraint(coefficients, 1));
//                }
            }
        }

        return constraints;
    }
}
