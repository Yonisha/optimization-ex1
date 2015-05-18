package sudoku;

public class SudokuDrawer{
    public void draw(String board, String tag){
        System.out.println("---------");
        System.out.print(tag + ":");
        char[] chars = board.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 9 == 0)
                System.out.println();


            System.out.print(chars[i]);


        }
        System.out.println();
        System.out.println("---------");
        System.out.println();
    }
}
