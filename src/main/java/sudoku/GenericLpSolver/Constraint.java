package sudoku.GenericLpSolver;

import java.util.List;

public class Constraint{
    private int[][][] coefficients;
    private int sumOfVariables;

    public Constraint(int[][][] coefficients, int sumOfVariables){
        this.coefficients = coefficients;
        this.sumOfVariables = sumOfVariables;
    }

    public int[][][] getCoefficients(){
        return coefficients;
    }

    public String getCoefficientsAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    stringBuilder.append(coefficients[i][j][k]);
                }
            }
        }

        return stringBuilder.toString();
    }

    public int getSum(){
        return this.sumOfVariables;
    }
}
