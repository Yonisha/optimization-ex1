package sudoku.GenericLpSolver;

import java.util.ArrayList;
import java.util.List;

public class GenericConstraintsCreator {

    public List<Constraint> create(){

        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(findVariablesForCellAndRowsAndColumnsConstraints());
        constraints.addAll(findVariablesForSquareConstraints());
        return constraints;
    }

    private List<Constraint> findVariablesForCellAndRowsAndColumnsConstraints() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per cell (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                List<Integer> variablesForCell = new ArrayList<>();
                List<Integer> variablesForRow = new ArrayList<>();
                List<Integer> variablesForColumn = new ArrayList<>();
                for (int k = 0; k <9; k++) {
                    variablesForCell.add(i*81 + j*9 + k);
                    variablesForRow.add(i*81 + k*9 + j);
                    variablesForColumn.add(k*81 + i*9 + j);
                }
                constraints.add(new Constraint(variablesForCell, 1));
                constraints.add(new Constraint(variablesForRow, 1));
                constraints.add(new Constraint(variablesForColumn, 1));
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
}
