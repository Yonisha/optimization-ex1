package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.List;

public class NonGenericConstraintsCreator {

    public List<Constraint> create(String inputBoard, int numberOfVariables) {

        int[][] parseInput = InputBoardParser.parseToTwoDimensionalArray(inputBoard);
        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(findVariablesForCellConstraints(parseInput));
        constraints.addAll(findVariablesForRowConstraints(parseInput));
//        constraints.addAll(findVariablesForColumnConstraints());
//        constraints.addAll(findVariablesForSquareConstraints());
        return constraints;
    }

    private List<Constraint> findVariablesForCellConstraints(int[][] input) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per cell (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create 9 constraints per iteration
                for (int j = 0; j < 9; j++) {
                    if (input[i][j] != 0)
                        continue;
                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        variables.add(i*81 + j*9 + k);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForRowConstraints(int[][] input) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create 9 constraints per iteration
                int[] relevantInputRow = input[i];
                for (int j = 0; j < 9; j++) {
                    if (thisNumberIsAlreadyInInputRow(relevantInputRow, j+1))
                        continue;
                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        variables.add(i*81 + k*9 + j);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForColumnConstraints() {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per column (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create 9 constraints per iteration
                for (int j = 0; j < 9; j++) {
                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        variables.add(k*81 + i*9 + j);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForSquareConstraints() {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per square (total 81)

            for (int i = 0; i < 9; i+=3) {
                // we need to create 9 constraints per iteration
                for (int j = 0; j < 9; j+=3) {
                    for (int k = 0; k <9; k++) {
                        // one constraint
                        List<Integer> variables = new ArrayList<>();
                        variables.add(i * 81 + j * 9 + k);
                        variables.add((i + 1) * 81 + j * 9 + k);
                        variables.add((i + 2) * 81 + j * 9 + k);
                        variables.add(i * 81 + (j + 1) * 9 + k);
                        variables.add(i * 81 + (j + 2) * 9 + k);
                        variables.add((i + 1) * 81 + (j + 1) * 9 + k);
                        variables.add((i + 1) * 81 + (j + 2) * 9 + k);
                        variables.add((i + 2) * 81 + (j + 1) * 9 + k);
                        variables.add((i + 2) * 81 + (j + 2) * 9 + k);

                        constraints.add(new Constraint(variables, 1));
                    }
                }
            }

            return constraints;
        }

    private boolean thisNumberIsAlreadyInInputRow(int[] relevantInputRow, int j) {
        for (int i = 0; i < relevantInputRow.length; i++) {
            if (relevantInputRow[i] == j)
                return true;
        }

        return false;
    }
}
