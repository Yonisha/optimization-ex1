package sudoku.utils;

import sudoku.BruteForceSolver.Cell;

import java.util.ArrayList;
import java.util.List;

public class InputBoardParser {
    public static List<Integer> parse(String board) {
        char[] chars = board.toCharArray();
        List<Integer> parsedBoard = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            parsedBoard.add(Integer.parseInt(chars[i] + ""));
        }

        return parsedBoard;
    }

    public static int[][] parseToTwoDimensionalArray(String board) {
        char[] chars = board.toCharArray();
        int[][] parsedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                parsedBoard[i][j] = Character.getNumericValue(chars[i * 9 + j]);
            }
        }

        return parsedBoard;
    }
}
