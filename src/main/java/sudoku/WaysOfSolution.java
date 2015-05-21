package sudoku;

public enum WaysOfSolution {

    BRUTEFORCE, GENERIC, NONGENERIC;

    public static WaysOfSolution fromInteger(int x) {
        switch(x) {
            case 1:
                return BRUTEFORCE;
            case 2:
                return GENERIC;
            case 3:
                return NONGENERIC;
        }
        return null;
    }
}
