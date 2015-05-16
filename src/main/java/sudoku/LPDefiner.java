package sudoku;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.List;

public class LPDefiner {

     public void Test() throws LpSolveException {


         VariablesCreator variablesCreator = new VariablesCreator();
         BinaryVariable[][][] variables = variablesCreator.create();
         ConstraintsCreator constraintsCreator = new ConstraintsCreator();
         List<Constraint> constraints = constraintsCreator.create();

         LpSolve solver = LpSolve.makeLp(108, 729);
         constraints.stream().forEach(c -> addConstraint(solver, c));


     }

    private void addConstraint(LpSolve solver, Constraint constraint)  {
        try {
            solver.strAddConstraint(constraint.getVariablesCoefficientsAsString(), LpSolve.EQ, constraint.getSum());
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}
