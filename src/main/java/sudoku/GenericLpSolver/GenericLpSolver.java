package sudoku.GenericLpSolver;

import sudoku.ISudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class GenericLpSolver implements ISudokuSolver {

    private static LpSolver lpSolver;

    public GenericLpSolver(LpSolver lpSolver){
        this.lpSolver = lpSolver;
    }

    public double[] Solve(String inputBoard) {

        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
        List<Constraint> constraints = genericConstraintsCreator.create(inputBoard);

        int numberOfVariables = 729;
        double[] solution = lpSolver.Solve(numberOfVariables, constraints);
        return solution;
    }
}
