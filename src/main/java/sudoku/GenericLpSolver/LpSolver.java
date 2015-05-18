package sudoku.GenericLpSolver;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.List;

public class LpSolver {
    public double[] Solve(int numberOfVariables, List<Constraint> constraints){
        try {
            LpSolve solver = LpSolve.makeLp(0, numberOfVariables);
            double[] objectiveFunc = new double[numberOfVariables];
            for (int i = 0; i<numberOfVariables ; i++) {
                solver.setBinary(i+1, true);
                objectiveFunc[i] = i;
            }

            solver.setObjFn(objectiveFunc);
            solver.setMinim();
            constraints.stream().forEach(c -> addConstraint(solver, c));

            // solver.writeLp("c:/lp.lp");
            solver.solve();
            solver.getVariables(objectiveFunc);
            return objectiveFunc;

        } catch (LpSolveException e) {
            e.printStackTrace();
        }

        return new double[0];
    }

    private void addConstraint(LpSolve solver, Constraint constraint)  {
        try {
            solver.strAddConstraint(constraint.getCoefficientsAsString(), LpSolve.EQ, constraint.getSum());
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}
