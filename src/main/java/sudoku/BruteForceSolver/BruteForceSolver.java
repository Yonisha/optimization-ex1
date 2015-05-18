package sudoku.BruteForceSolver;

import sudoku.ISudokuSolver;
import sudoku.utils.InputBoardParser;

import java.util.List;
import java.util.stream.Collectors;

public class BruteForceSolver implements ISudokuSolver {

    public double[] Solve(String inputBoard){

        InputBoardParser inputBoardParser = new InputBoardParser();
        List<Integer> parsedBoard = inputBoardParser.parse(inputBoard);

        List<Cell> cells = parsedBoard.stream().map(i -> new Cell(i, i != 0)).collect(Collectors.toList());

        List<Cell> cells1 = fillCellRecursive(cells, 0);


        return new double[0];
    }

    private List<Cell> fillCellRecursive(List<Cell> input, int currentIndex){
        if (currentIndex == input.size()-1)
            return input;

        if (input.get(currentIndex).isFixed())
            fillCellRecursive(input, currentIndex - 1);

        int value = input.get(currentIndex).getValue();
        if (value == 9)
        {
            input.get(currentIndex).initValue();
            fillCellRecursive(input, currentIndex - 1);
        }

        input.get(currentIndex).incrementValue();
        return fillCellRecursive(input, currentIndex + 1);
    }
}