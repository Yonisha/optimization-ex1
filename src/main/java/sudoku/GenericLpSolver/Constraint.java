package sudoku.GenericLpSolver;

import java.util.ArrayList;
import java.util.List;

public class Constraint{
    private List<Integer> coefficients;
    private int sumOfVariables;

    public Constraint(int[][][] coefficients, int sumOfVariables){
        this.coefficients =  getCoefficientsAsFlatList(coefficients);
        this.sumOfVariables = sumOfVariables;
    }

    public Constraint(List<Integer> coefficients, int sumOfVariables){
        this.coefficients = coefficients;
        this.sumOfVariables = sumOfVariables;
    }

    public int[] getCo(){
        int[] result = new int[9];
        for (int i = 0; i < result.length; i++) {
            result[i] = coefficients.get(i)+1;
        }
        return result;
    }

    public String getCoefficientsAsString(){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++){
            stringBuilder.append(coefficients.get(i) + " ");
        }

        return stringBuilder.toString().trim();
    }

    public int getSum(){
        return this.sumOfVariables;
    }

    private List<Integer> getCoefficientsAsFlatList(int[][][] coefficients){
        List<Integer> flatCoefficientList = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                for(int k = 0; k < 9; k++){
                    flatCoefficientList.add(coefficients[i][j][k]);
                }
            }
        }

        return flatCoefficientList;
    }
}
