package sudoku.GenericLpSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintsCreator{

    int SUM_FOR_SINGLE_CELL = 1;
    int SUM_FOR_ROW_OR_COLUMN_OR_SQUARE = 45;

    public List<Constraint> create(){

        List<Constraint> constraints = new ArrayList<>();
//        constraints.addAll(createForCells());
        constraints.addAll(createForRows());
//        constraints.addAll(createForColumns(variables));
//        constraints.addAll(createForSquares(variables));
        return constraints;
    }

    public List<Constraint> createForCells(){
        List<Constraint> constraintsForAllCells = new ArrayList<>();
        List<String> relevantCoefficients = Arrays.asList("1 1 1 1 1 1 1 1 1".split(" "));
        for (int i=0 ; i < 81 ; i++) {
            List<String> currentCoefficients = new ArrayList<>();
            // add zeros before
            for (int j=0 ; j < i*9 ; j++){
                currentCoefficients.add("0");
            }
            // add ones
            currentCoefficients.addAll(relevantCoefficients);
            // add zeros after
            int numberOfMissingCoefficients = 729-currentCoefficients.size();
            for (int j=0 ; j < numberOfMissingCoefficients ; j++){
                currentCoefficients.add("0");
            }

//            constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_SINGLE_CELL));
        }
        return constraintsForAllCells;
    }

    public List<Constraint> createForRows() {
        List<Constraint> constraints = new ArrayList<>(); //we should have 9 per row (total 81)

        for (int i = 0; i < 9; i++) {
            // we need to create 9 constraints per iteration
            for (int j = 0; j < 9; j++) {
                // one constraint
                int[][][] constraint = new int[9][9][9];
                for (int k = 0; k <9; k++) {
                    constraint[i][k][j] = 1;
                }
                constraints.add(new Constraint(constraint, 1));
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
                    int[][][] constraint = new int[9][9][9];
                    constraint[i][j][k] = 1;
                    constraint[i+1][j][k] = 1;
                    constraint[i+2][j][k] = 1;
                    constraint[i][j+1][k] = 1;
                    constraint[i][j+2][k] = 1;
                    constraint[i+1][j+1][k] = 1;
                    constraint[i+1][j+2][k] = 1;
                    constraint[i+2][j+1][k] = 1;
                    constraint[i+2][j+2][k] = 1;

                    constraints.add(new Constraint(constraint, 1));
                }
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
                int[][][] constraint = new int[9][9][9];
                for (int k = 0; k <9; k++) {
                    constraint[k][i][j] = 1;
                }
                constraints.add(new Constraint(constraint, 1));
            }
        }

        return constraints;
    }


//    public List<Constraint> createForRows(){
//        List<Constraint> constraintsForAllCells = new ArrayList<>();
//        List<String> relevantCoefficients = Arrays.asList("1 2 3 4 5 6 7 8 9".split(" "));
//        for (int i=0 ; i < 9 ; i++) {
//            List<String> currentCoefficients = new ArrayList<>();
//            // add zeros before
//            for (int j=0 ; j < i*81 ; j++){
//                currentCoefficients.add("0");
//            }
//            // add ones
//            for (int k=0 ; k < 9 ; k++){
//                currentCoefficients.addAll(relevantCoefficients);
//            }
//            // add zeros after
//            int numberOfMissingCoefficients = 729-currentCoefficients.size();
//            for (int j=0 ; j < numberOfMissingCoefficients ; j++){
//                currentCoefficients.add("0");
//            }
//
//            constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_ROW_OR_COLUMN_OR_SQUARE));
//        }
//        return constraintsForAllCells;
//    }




}
