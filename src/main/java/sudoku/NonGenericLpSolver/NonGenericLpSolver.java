package sudoku.NonGenericLpSolver;

import sudoku.GenericLpSolver.Constraint;
import sudoku.GenericLpSolver.LpSolver;
import sudoku.ISudokuSolver;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NonGenericLpSolver implements ISudokuSolver {

    private static LpSolver lpSolver;

    public NonGenericLpSolver(LpSolver lpSolver){
        this.lpSolver = lpSolver;
    }

    public List<double[]> Solve(List<String> inputBoards){

        return inputBoards.stream().map(b -> solveSingleBoard(b)).collect(Collectors.toList());
    }

    private double[] solveSingleBoard(String inputBoard){
        NonGenericConstraintsCreator genericConstraintsCreator = new NonGenericConstraintsCreator();
        int numberOfVariables = getNumberOfVariables(inputBoard);
        List<Constraint> constraints = genericConstraintsCreator.create(inputBoard);

        double[] solution = lpSolver.Solve(numberOfVariables, constraints);
        double[] solutionWithInput = addInputToSolution(solution, inputBoard);
        return solutionWithInput;
    }

    private int getNumberOfVariables(String inputBoard){
        int numberOfEmptyCells = 0;
        char[] chars = inputBoard.toCharArray();
        for (int i = 0; i < 81; i++) {
            if (chars[i] == '0')
                numberOfEmptyCells++;
        }

        return numberOfEmptyCells * 9;
    }

    private double[] addInputToSolution(double[] solution, String inputBoard){
        List<Integer> inputAsList = InputBoardParser.parse(inputBoard);
        List<Double> result = new ArrayList<>();
        int solutionIndex = 0;
        for (int i = 0; i < inputAsList.size(); i++) {
            Integer currentInput = inputAsList.get(i);
            if (currentInput == 0){
                for (int j = 0; j < 9; j++) {
                    result.add(solution[solutionIndex]);
                    solutionIndex++;
                }
            }else{
                for (int j = 0; j < 9; j++) {
                    if (currentInput == j+1)
                        result.add(1.0);
                    else
                        result.add(0.0);
                }
            }
        }

        return result.stream().mapToDouble(i ->i).toArray();
    }
}
