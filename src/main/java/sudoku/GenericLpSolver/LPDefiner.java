package sudoku.GenericLpSolver;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LPDefiner {

     public void Test() throws LpSolveException {

         String input = "000075400000000008080190000300001060000000034000068170204000603900000020530200000";

         ConstraintsCreator constraintsCreator = new ConstraintsCreator();
         List<Constraint> constraints = constraintsCreator.create();
         constraints.addAll(getConstraintsFromInput(input));

         LpSolve solver = LpSolve.makeLp(constraints.size(), 729);
         double[] objectiveFunc = new double[729];
         for (int i = 0; i<729 ; i++) {
             solver.setBinary(i+1, true);
             objectiveFunc[i] = 0;
         }

         solver.setObjFn(objectiveFunc);
         solver.setMaxim();

         constraints.stream().forEach(c -> addConstraint(solver, c));

//         solver.writeLp("F:/lp.txt");
        solver.solve();


         solver.getVariables(objectiveFunc);

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
