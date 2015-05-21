package sudoku.BruteForceSolver;

import sudoku.ISudokuSolver;
import sudoku.Verifier;
import sudoku.utils.InputBoardParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BruteForceSolver implements ISudokuSolver {
    @Override
    public List<double[]> Solve(List<String> inputBoards) {
        List<double[]> results = new ArrayList<>();
        for (String board : inputBoards) {
            double[] result = solve(board);
            results.add(result);
        }

        return results;
    }

    public double[] solve(String inputBoard){

        InputBoardParser inputBoardParser = new InputBoardParser();
        List<Integer> parsedBoard = inputBoardParser.parse(inputBoard);

        List<Cell> cells = parsedBoard.stream().map(i -> new Cell(i == 0 ? 0 : i, i != 0)).collect(Collectors.toList());

        fillCellRecursive(cells, 0);

        List<Double> result = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 1; j <= 9; j++) {
                result.add(cells.get(i).getValue() == j ? 1d : 0);
            }
        }

        double[] doubles = result.stream().mapToDouble(d -> d).toArray();

        return doubles;
    }

    private boolean fillCellRecursive(List<Cell> input, int currentIndex){
        if (currentIndex == input.size()) {
            return false;
        }

        Cell cell = input.get(currentIndex);
        if (cell.isFixed()) {
            return fillCellRecursive(input, currentIndex + 1);
        } else {
            for (int i = 1; i <= 9; i++) {
                cell.setValue(i);

                double[][][] doubles = convertTo3DMatrix(input);
                boolean valid = Verifier.verifyResult(doubles, false);
                if (valid) {
                    if (Verifier.verifyResult(doubles, true)) {
                        return true;
                    }

                    boolean success = fillCellRecursive(input, currentIndex + 1);

                    if (success) {
                        return true;
                    }
                }
            }
        }

        input.get(currentIndex).initValue();
        return false;
    }

    private double[][][] convertTo3DMatrix(List<Cell> cells) {
        double[][][] matrix = new double[9][9][9];

        for (int i = 0; i < cells.size(); i++) {
            int value = cells.get(i).getValue();
            if (value != 0) {
                matrix[i / 9][i % 9][value - 1] = 1;
            }
        }

        return matrix;
    }
}