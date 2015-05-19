package sudoku.GenericLpSolver;

import java.util.ArrayList;
import java.util.List;

public class Constraint{
    private List<Integer> coefficients;
    private int sumOfVariables;

    public Constraint(List<Integer> coefficients, int sumOfVariables){
        this.coefficients = coefficients;
        this.sumOfVariables = sumOfVariables;
    }

    public int[] getCoefficients(){
        return coefficients.stream().mapToInt(i->i+1).toArray();
    }

    public int getSum(){
        return this.sumOfVariables;
    }

}
