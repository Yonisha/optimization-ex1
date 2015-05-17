package sudoku.GenericLpSolver;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import sudoku.ISudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class GenericLpSolver implements ISudokuSolver {

     public double[] Solve(String inputBoard) {
         try {
         ConstraintsCreator constraintsCreator = new ConstraintsCreator();
         List<Constraint> constraints = constraintsCreator.create();
         constraints.addAll(getConstraintsFromInput(inputBoard));

         LpSolve  solver = LpSolve.makeLp(0, 729);

         double[] objectiveFunc = new double[729];
         for (int i = 0; i<729 ; i++) {
             solver.setBinary(i+1, true);
             objectiveFunc[i] = i;
         }

         solver.setObjFn(objectiveFunc);
         solver.setMinim();

         constraints.stream().forEach(c -> addConstraint(solver, c));

//         solver.writeLp("c:/lp.lp");
         solver.solve();

         solver.getVariables(objectiveFunc);

            return objectiveFunc;
         } catch (LpSolveException e) {
             e.printStackTrace();
         }

         return new double[0];
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

    private void addConstraint(LpSolve solver, Constraint constraint)  {
        try {
            solver.strAddConstraint(constraint.getCoefficientsAsString(), LpSolve.EQ, constraint.getSum());
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }


}
