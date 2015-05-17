package sudoku.GenericLpSolver;

public class VariablesCreator{
    public BinaryVariable[][][] create(){

        BinaryVariable[][][] variables = new BinaryVariable[9][9][9];

        for (int i=1 ; i <= 9 ; i++){
            for (int j=1 ; j <= 9 ; j++){
                for (int k=1 ; k <= 9 ; k++){
                    BinaryVariable binaryVariable = new BinaryVariable(i, j, k);
                    variables[i-1][j-1][k-1] = binaryVariable;
                }
            }
        }

        return variables;
    }
}
