package sudoku.GenericLpSolver;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;

import java.util.Arrays;
import java.util.List;

public class LPDefiner {

     public void Test() throws LpSolveException {

         VariablesCreator variablesCreator = new VariablesCreator();
         BinaryVariable[][][] variables = variablesCreator.create();
         ConstraintsCreator constraintsCreator = new ConstraintsCreator();
         List<Constraint> constraints = constraintsCreator.create();

         LpSolve solver = LpSolve.makeLp(0, 729);
         double[] objectiveFunc = new double[729];
         for (int i = 0; i<729 ; i++) {
             solver.setBinary(i+1, true);
             objectiveFunc[i] = 0;
         }

         solver.setObjFn(objectiveFunc);


         String template = "";
//         for (int i = 0; i <729 ; i++) {
//             template += "0";
//         }
//         solver.strSetObjFn(template);
//         constraints.stream().forEach(c -> addConstraint(solver, c));
//         addConstraintsFromInput(solver);
//
//         int solve = solver.solve();
//         // print solution
//         System.out.println("Value of objective function: " + solver.getObjective());
//         double[] var = solver.getPtrVariables();
//         for (int i = 0; i < var.length; i++) {
//             System.out.println("Value of var[" + i + "] = " + var[i]);
//         }
//
//         // delete the problem and free memory
//         solver.deleteLp();


     }

//    private void addConstraint(LpSolve solver, Constraint constraint)  {
//        try {
//            solver.strAddConstraint(constraint.getVariablesCoefficientsAsString(), LpSolve.EQ, constraint.getSum());
//        } catch (LpSolveException e) {
//            e.printStackTrace();
//        }
//    }

//    private void addConstraintsFromInput(LpSolve solver){
//        char[] input = "000075400000000008080190000300001060000000034000068170204000603900000020530200000".toCharArray();
//        String[] template = new String[729];
//        for (int i = 0; i <template.length ; i++) {
//            template[i] = "0";
//        }
//        for (int i = 0; i <input.length; i++) {
//            int inputAsInt = Integer.parseInt("" + input[i]);
//            if (inputAsInt == 0)
//                continue;
//
//            String[] coefficientsForNewConstraint = template.clone();
//            coefficientsForNewConstraint[i*9 + inputAsInt] = "1";
//            Constraint newConstraint = new Constraint(Arrays.asList(coefficientsForNewConstraint), 1);
//            try {
//                solver.strAddConstraint(newConstraint.getVariablesCoefficientsAsString(), LpSolve.EQ, newConstraint.getSum());
//            } catch (LpSolveException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
