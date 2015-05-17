package sudoku.GenericLpSolver;

import java.util.List;

public class Constraint{
    private List<String> coefficients;
    private int sumOfVariables;

    public Constraint(List<String> coefficients, int sumOfVariables){
        this.coefficients = coefficients;
        this.sumOfVariables = sumOfVariables;
    }

    public String getVariablesCoefficientsAsString(){
        return String.join(" ", coefficients);
    }

    public int getSum(){
        return this.sumOfVariables;
    }
}
