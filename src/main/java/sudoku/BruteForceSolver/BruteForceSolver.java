package sudoku.BruteForceSolver;

import javafx.util.Pair;
import sudoku.ISudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver implements ISudokuSolver {

    public double[] Solve(String inputBoard){

//        for (int i = 0; i < inputBoard.length; i++) {
//            if (inputBoard[i] != 0)
//                continue;
//
//            int valueForCurrentCell = solveCurrentCell(inputBoard, i, 1);
//            if (valueForCurrentCell != 0){
//
//            }

//
//        }
//
//        return inputBoard;
        return new double[0];
    }

//    private int[] solveCurrentCellRec(int[] inputBoard, int indexOfCurrentCell){
//        int valueForCurrentCell = solveCurrentCell(inputBoard, i, 1);
//        if (valueForCurrentCell != 0) {
//        }
//    }
//
//    private int solveCurrentCell(int[] inputBoard, int indexOfCurrentCell, int indexToStartFromForValue){
//        for (int i = indexToStartFromForValue; i <=9; i++) {
//            inputBoard[indexOfCurrentCell] = i;
//            if (boardIsLegal(inputBoard))
//                return i;
//        }
//
//        return 0;
//    }
}