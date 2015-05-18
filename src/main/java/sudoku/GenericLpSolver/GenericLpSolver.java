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

         ConstraintsCreator constraintsCreator = new ConstraintsCreator();
         List<Constraint> constraints = constraintsCreator.create();
         constraints.addAll(getConstraintsFromInput(inputBoard));

        int numberOfVariables = 729;
        double[] solution = lpSolver.Solve(numberOfVariables, constraints);
        return solution;
    }

    private List<Constraint> getConstraintsFromInput(String input){
        char[] chars = input.toCharArray();
        List<Constraint> constraints = new ArrayList<>();

        for (int i = 0; i < 81; i++) {
            int currentInput = Integer.parseInt(chars[i] + "");
            if (currentInput == 0)
                continue;

            int[][][] coefficients = new int[9][9][9];
            coefficients[i/9][i%9][currentInput-1] = 1;
            constraints.add(new Constraint(coefficients, 1));
        }

        return constraints;
    }
}
