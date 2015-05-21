package sudoku.GenericLpSolver;

import java.util.List;

public class Constraint{
    private List<Integer> variables;
    private int sumOfVariables;

    public Constraint(List<Integer> variables, int sumOfVariables){
        this.variables = variables;
        this.sumOfVariables = sumOfVariables;
    }

    public int[] getVariables(){
        return variables.stream().mapToInt(i->i+1).toArray();
    }

    public int getSum(){
        return this.sumOfVariables;
    }
}
