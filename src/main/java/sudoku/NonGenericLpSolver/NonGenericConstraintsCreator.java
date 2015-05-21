package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonGenericConstraintsCreator {

    public List<Constraint> create(String inputBoard) {

        int[][] parseInput = InputBoardParser.parseToTwoDimensionalArray(inputBoard);
        List<Constraint> constraints = new ArrayList<>();
        Map<Integer, Integer> oldToNewVariables = mapVariables(parseInput);
        constraints.addAll(findVariablesForCellConstraints(parseInput, oldToNewVariables));
        constraints.addAll(findVariablesForRowConstraints(parseInput, oldToNewVariables));
        constraints.addAll(findVariablesForColumnConstraints(parseInput, oldToNewVariables));
        constraints.addAll(findVariablesForSquareConstraints(parseInput, oldToNewVariables));
        return constraints;
    }

    private List<Constraint> findVariablesForCellConstraints(int[][] input, Map<Integer, Integer> oldToNewVariables) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per cell (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create max 9 constraints per iteration
                for (int j = 0; j < 9; j++) {
                    if (input[i][j] != 0){
                        continue;
                    }

                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        int newVariable = getNewVariable(i * 81 + j * 9 + k, oldToNewVariables);
                        if (newVariable != -1)
                            variables.add(newVariable);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForRowConstraints(int[][] input, Map<Integer, Integer> oldToNewVariables) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create max 9 constraints per iteration
                int[] relevantInputRow = input[i];
                for (int j = 0; j < 9; j++) {
                    if (thisNumberIsAlreadyInInput(relevantInputRow, j + 1)){
                        continue;
                    }

                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        int newVariable = getNewVariable(i * 81 + k * 9 + j, oldToNewVariables);
                        if (newVariable != -1)
                            variables.add(newVariable);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForColumnConstraints(int[][] input, Map<Integer, Integer> oldToNewVariables) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per column (total 81)

            for (int i = 0; i < 9; i++) {
                // we need to create max 9 constraints per iteration
                int[] relevantInputColumn = getColumn(input, i);
                for (int j = 0; j < 9; j++) {
                    if (thisNumberIsAlreadyInInput(relevantInputColumn, j + 1)){
                        continue;
                    }

                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    for (int k = 0; k <9; k++) {
                        int newVariable = getNewVariable(k * 81 + i * 9 + j, oldToNewVariables);
                        if (newVariable != -1)
                            variables.add(newVariable);
                    }
                    constraints.add(new Constraint(variables, 1));
                }
            }

            return constraints;
        }

    private List<Constraint> findVariablesForSquareConstraints(int[][] input, Map<Integer, Integer> oldToNewVariables) {
            List<Constraint> constraints = new ArrayList<>(); //we should have 9 per square (total 81)

        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                // we need to create max 9 constraints per iteration
                int[] relevantInputSquare = getSquare(input, i, i+2, j, j+2);
                for (int k = 0; k <9; k++) {
                    if (thisNumberIsAlreadyInInput(relevantInputSquare, k + 1)){
                        continue;
                    }

                    // one constraint
                    List<Integer> variables = new ArrayList<>();
                    int newVariable = getNewVariable(i * 81 + j * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 1) * 81 + j * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 2) * 81 + j * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable(i * 81 + (j + 1) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable(i * 81 + (j + 2) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 1) * 81 + (j + 1) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 1) * 81 + (j + 2) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 2) * 81 + (j + 1) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    newVariable = getNewVariable((i + 2) * 81 + (j + 2) * 9 + k, oldToNewVariables);
                    if (newVariable != -1)
                        variables.add(newVariable);

                    constraints.add(new Constraint(variables, 1));
                }
            }
        }

        return constraints;
    }

    private boolean thisNumberIsAlreadyInInput(int[] relevantInput, int j) {
        for (int i = 0; i < relevantInput.length; i++) {
            if (relevantInput[i] == j)
                return true;
        }

        return false;
    }

    private int[] getColumn(int[][] input, int columnIndex) {
        int[] column = new int[9];
        for (int i = 0; i < input.length; i++) {
            column[i] = input[i][columnIndex];
        }

        return column;
    }

    private int[] getSquare(int[][] input, int rowStartIndex, int rowEndIndex, int columnStartIndex, int columnEndIndex) {
        List<Integer> square = new ArrayList<>();
        for (int i = rowStartIndex; i <= rowEndIndex; i++) {
            for (int j = columnStartIndex; j <= columnEndIndex; j++) {
                square.add(input[i][j]);
            }
        }

        int[] result = square.stream().mapToInt(i -> i).toArray();
        return result;
    }

    private Map<Integer, Integer> mapVariables(int[][] input){

        Map<Integer, Integer> oldToNewVariables = new HashMap<>();
        int amountOfFixedCells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                if (input[i][j] == 0) {
                    for (int k = 0; k < 9; k++) {
                        oldToNewVariables.put(i*81 + j*9 + k, i*81 + j*9 - amountOfFixedCells*9 + k);
                    }
                } else {
                    amountOfFixedCells++;
                }
            }
        }

        return oldToNewVariables;
    }

    private int getNewVariable(int oldVariable, Map<Integer, Integer> oldToNewVariables){
        Integer newVariable = oldToNewVariables.get(oldVariable);
        if (newVariable != null)
            return newVariable;

        return -1;
    }
}
