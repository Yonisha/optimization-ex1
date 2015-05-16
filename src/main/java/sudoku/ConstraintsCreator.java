package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstraintsCreator{

    int SUM_FOR_SINGLE_CELL = 1;
    int SUM_FOR_ROW_OR_COLUMN_OR_SQUARE = 45;

    public List<Constraint> create(){

        List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(createForCells());
//        constraints.addAll(createForRows(variables));
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

            constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_SINGLE_CELL));
        }
        return constraintsForAllCells;
    }


    public List<Constraint> createForRows(){
        List<Constraint> constraintsForAllCells = new ArrayList<>();
        List<String> relevantCoefficients = Arrays.asList("1 2 3 4 5 6 7 8 9".split(" "));
        for (int i=0 ; i < 9 ; i++) {
            List<String> currentCoefficients = new ArrayList<>();
            // add zeros before
            for (int j=0 ; j < i*81 ; j++){
                currentCoefficients.add("0");
            }
            // add ones
            for (int k=0 ; k < 9 ; k++){
                currentCoefficients.addAll(relevantCoefficients);
            }
            // add zeros after
            int numberOfMissingCoefficients = 729-currentCoefficients.size();
            for (int j=0 ; j < numberOfMissingCoefficients ; j++){
                currentCoefficients.add("0");
            }

            constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_ROW_OR_COLUMN_OR_SQUARE));
        }
        return constraintsForAllCells;
    }

    public List<Constraint> createForColumns(){
        List<Constraint> constraintsForAllCells = new ArrayList<>();
        List<String> relevantCoefficients = Arrays.asList("1 2 3 4 5 6 7 8 9".split(" "));
        for (int i=0 ; i < 9 ; i++) {
            List<String> currentCoefficients = new ArrayList<>();
            // add zeros before
            for (int j=0 ; j < i*9 ; j++){
                currentCoefficients.add("0");
            }
            // add ones
           currentCoefficients.addAll(relevantCoefficients);
            // add zeros after
            int numberOfMissingCoefficients = 81-currentCoefficients.size();
            for (int j=0 ; j < numberOfMissingCoefficients ; j++){
                currentCoefficients.add("0");
            }
            List<String> tmp = new ArrayList(currentCoefficients);
            for (int j=0 ; j < 8 ; j++){
                currentCoefficients.addAll(tmp);
            }

            constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_ROW_OR_COLUMN_OR_SQUARE));
        }
        return constraintsForAllCells;
    }

    public List<Constraint> createForSquares(){
        List<Constraint> constraintsForAllCells = new ArrayList<>();
        List<String> relevantCoefficients = Arrays.asList("1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9".split(" "));
        List<String> zerosCoefficients = Arrays.asList("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ".split(" "));
        List<String> currentCoefficients = new ArrayList<>();
        currentCoefficients.addAll(relevantCoefficients);
        currentCoefficients.addAll(zerosCoefficients);
        currentCoefficients.addAll(zerosCoefficients);
        currentCoefficients.addAll(relevantCoefficients);
        currentCoefficients.addAll(zerosCoefficients);
        currentCoefficients.addAll(zerosCoefficients);
        currentCoefficients.addAll(relevantCoefficients);
        currentCoefficients.addAll(zerosCoefficients);
        currentCoefficients.addAll(zerosCoefficients);

        for (int i=0 ; i < 18 ; i++) {
            currentCoefficients.addAll(zerosCoefficients);
        }

        constraintsForAllCells.add(new Constraint(currentCoefficients, SUM_FOR_ROW_OR_COLUMN_OR_SQUARE));
        for (int i=1 ; i < 9; i++) {
            List<String> tmpCoefficients = new ArrayList<>();
            tmpCoefficients.addAll(currentCoefficients);
            for (int j=0 ; j < 27; j++){
                tmpCoefficients.add(0, "0");
            }

            constraintsForAllCells.add(new Constraint(tmpCoefficients.subList(0, 729), SUM_FOR_ROW_OR_COLUMN_OR_SQUARE));
        }



        return constraintsForAllCells;
    }


}
