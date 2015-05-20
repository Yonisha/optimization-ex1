package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;

import java.util.ArrayList;
import java.util.List;

public class NonGenericConstraintsCreator {

    public List<Constraint> create(String inputBoard, int numberOfVariables) {

        List<Constraint> constraints = new ArrayList<>();
//        constraints.addAll(findVariablesForCellConstraints(inputBoard));
        constraints.addAll(findVariablesForRowConstraints(inputBoard));
        constraints.addAll(findVariablesForColumnConstraints(inputBoard));
        constraints.addAll(findVariablesForSquareConstraints(inputBoard));

        return constraints;
    }

//    private List<Constraint> findVariablesForCellConstraints(String inputBoard) {
//        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per cell (total 81)
//
//        for (int i = 0; i < 9; i++) {
//            // we need to create 9 constraints per iteration
//            for (int j = 0; j < 9; j++) {
//                // one constraint
//                if ()
//                List<Integer> coefficients = new ArrayList<>();
//                for (int k = 0; k <9; k++) {
//                    coefficients.add(i*81 + j*9 + k);
//                }
//                constraints.add(new Constraint(coefficients, 1));
//            }
//        }
//
//        return constraints;
//    }

    private List<Constraint> findVariablesForRowConstraints(String inputBoard) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                List<Integer> coefficients = new ArrayList<>();
                for (int k = 0; k <9; k++) {
                    coefficients.add(i*81 + k*9 + j);
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    private List<Constraint> findVariablesForColumnConstraints(String inputBoard) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per column (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                List<Integer> coefficients = new ArrayList<>();
                for (int k = 0; k <9; k++) {
                    coefficients.add(k*81 + i*9 + j);
                }
                constraints.add(new Constraint(coefficients, 1));
            }
        }

        return constraints;
    }

    private List<Constraint> findVariablesForSquareConstraints(String inputBoard) {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per square (total 81)

        for (int i = 0; i < 9; i+=3) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j+=3) {
                for (int k = 0; k <9; k++) {
                    // one constraint
                    List<Integer> coefficients = new ArrayList<>();
                    coefficients.add(i*81 + j*9 + k);
                    coefficients.add((i+1)*81 + j*9 + k);
                    coefficients.add((i+2)*81 + j*9 + k);
                    coefficients.add(i*81 + (j+1)*9 + k);
                    coefficients.add(i*81 + (j+2)*9 + k);
                    coefficients.add((i+1)*81 + (j+1)*9 + k);
                    coefficients.add((i+1)*81 + (j+2)*9 + k);
                    coefficients.add((i+2)*81 + (j+1)*9 + k);
                    coefficients.add((i+2)*81 + (j+2)*9 + k);

                    constraints.add(new Constraint(coefficients, 1));
                }
            }
        }

        return constraints;
    }
}
