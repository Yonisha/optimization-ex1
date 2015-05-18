package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;
import sudoku.GenericLpSolver.LpSolver;
import sudoku.ISudokuSolver;

import java.util.List;

public class NonGenericLpSolver implements ISudokuSolver {

    private static LpSolver lpSolver;

    public NonGenericLpSolver(LpSolver lpSolver){
        this.lpSolver = lpSolver;
    }

    public double[] Solve(String inputBoard){

        NonGenericConstraintsCreator genericConstraintsCreator = new NonGenericConstraintsCreator();
        int numberOfVariables = getNumberOfVariables(inputBoard);
        List<Constraint> constraints = genericConstraintsCreator.create(inputBoard, numberOfVariables);

        double[] solution = lpSolver.Solve(numberOfVariables, constraints);
        return solution;
    }

    private int getNumberOfVariables(String inputBoard){
        int numberOfEmptyCells = 0;
        char[] chars = inputBoard.toCharArray();
        for (int i = 0; i < 81; i++) {
            if (chars[i] != '0')
                numberOfEmptyCells++;
        }

        return numberOfEmptyCells * 9;
    }
}
