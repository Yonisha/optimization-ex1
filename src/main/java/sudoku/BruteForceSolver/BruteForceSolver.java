package sudoku.BruteForceSolver;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver {

    public int[][] solve(int[][] inputBoard){

        for (int i = 0; i < inputBoard.length; i++) {
            for (int j = 0; j < inputBoard[0].length; j++) {
                if (inputBoard[i][j] != 0)
                    continue;

                inputBoard[i][j] = solveNextCell(inputBoard, new Pair<>(i, j));
            }
        }

        return inputBoard;
    }

    private int solveNextCell(int[][] inputBoard, Pair<Integer, Integer> indexOfNextCell){
        return 1;
    }
}
