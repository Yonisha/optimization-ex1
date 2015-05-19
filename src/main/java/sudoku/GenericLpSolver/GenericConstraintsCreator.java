package sudoku.GenericLpSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericConstraintsCreator {

    public List<Constraint> create(){

        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(createForCells());
//        constraints.addAll(createForRows());
        constraints.addAll(createForColumns());
        constraints.addAll(createForSquares());
        return constraints;
    }


    public List<Constraint> create2(){

        List<Constraint> constraints = new ArrayList<>();
//        constraints.addAll(createForCells());
        constraints.addAll(findVariablesForRowConstraints());
//        constraints.addAll(createForColumns());
//        constraints.addAll(createForSquares());
        return constraints;
    }
    public List<Constraint> createForCells() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                for (int k = 0; k <9; k++) {
                    coefficients[i][j][k] = 1;
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForRows() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                for (int k = 0; k <9; k++) {
                    coefficients[i][k][j] = 1;
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> findVariablesForRowConstraints() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                List<Integer> coefficients = new ArrayList<>();
                for (int k = 0; k <9; k++) {
                    coefficients.add(i*9 + k*9 + j);
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForColumns() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] coefficients = new int[9][9][9];
                for (int k = 0; k <9; k++) {
                    coefficients[k][i][j] = 1;
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    public List<Constraint> createForSquares() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i+=3) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j+=3) {
                for (int k = 0; k <9; k++) {
                // one constraint
                    int[][][] coefficients = new int[9][9][9];
                    coefficients[i][j][k] = 1;
                    coefficients[i+1][j][k] = 1;
                    coefficients[i+2][j][k] = 1;
                    coefficients[i][j+1][k] = 1;
                    coefficients[i][j+2][k] = 1;
                    coefficients[i+1][j+1][k] = 1;
                    coefficients[i+1][j+2][k] = 1;
                    coefficients[i+2][j+1][k] = 1;
                    coefficients[i+2][j+2][k] = 1;

                    constraints.add(new Constraint(coefficients, 1));
                }
            }
        }

        return constraints;
    }
}
