package sudoku;

import java.util.ArrayList;
import java.util.List;

public class InputBoardParser {
    public List<Integer> parse(String board) {
        char[] chars = board.toCharArray();
        List<Integer> parsedBoard = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            parsedBoard.add(Integer.parseInt(chars[i] + ""));
        }

        return parsedBoard;
    }

    public int[][] parseToTwoDimensionalArray(String board) {
        char[] chars = board.toCharArray();
        int[][] parsedBoard = new int[9][9];
        for (int i = 0; i < chars.length; i+=9) {
            for (int j = 0; j <9; j++) {
                parsedBoard[i][j] = chars[i+j];
            }
        }

        return parsedBoard;
    }
}
