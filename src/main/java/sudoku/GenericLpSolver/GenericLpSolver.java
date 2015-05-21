package sudoku.GenericLpSolver;

import sudoku.ISudokuSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericLpSolver implements ISudokuSolver {

    private static LpSolver lpSolver;

    public GenericLpSolver(LpSolver lpSolver){
        this.lpSolver = lpSolver;
    }

    public List<double[]> Solve(List<String> inputBoards) {

        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
        List<Constraint> genericConstraints = genericConstraintsCreator.create();

        List<double[]> results = inputBoards.stream().map(b -> SolveSingleBoard(b, genericConstraints)).collect(Collectors.toList());
        return results;
    }

    private double[] SolveSingleBoard(String inputBoard, List<Constraint> genericConstraints){
        List<Constraint> constraintsWithInput = genericConstraints;
        constraintsWithInput.addAll(findVariablesForInputConstraints(inputBoard));
        double[] solution = lpSolver.Solve(729, constraintsWithInput);
        return solution;
    }

    private List<Constraint> findVariablesForInputConstraints(String inputBoard){
        char[] chars = inputBoard.toCharArray();
        List<Constraint> constraints = new ArrayList<>();

        for (int i = 0; i < 81; i++) {
            int currentInput = Integer.parseInt(chars[i] + "");
            if (currentInput == 0)
                continue;

            List<Integer> variables = new ArrayList<>();
            variables.add((i*81)/9 + (i*9)%9 + currentInput-1);
            constraints.add(new Constraint(variables, 1));
        }

        return constraints;
    }
}
