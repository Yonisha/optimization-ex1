package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.GenericLpSolver.Constraint;
import sudoku.GenericLpSolver.ConstraintsCreator;

import java.util.List;

public class ConstraintsCreatorTests {

    @Test
    public void test1(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForCells();
        Assert.assertEquals(81, constraints.size());
        Assert.assertEquals(729, constraints.get(0).getVariablesCoefficientsAsString().split(" ").length);
    }

    @Test
    public void test2(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForRows();
        Assert.assertEquals(9, constraints.size());
        Assert.assertEquals(729, constraints.get(0).getVariablesCoefficientsAsString().split(" ").length);
    }

    @Test
    public void createForColumns(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForColumns();
        Assert.assertEquals(9, constraints.size());
        Assert.assertEquals(729, constraints.get(0).getVariablesCoefficientsAsString().split(" ").length);
    }

    @Test
    public void createForSquares(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForSquares();
        Assert.assertEquals(9, constraints.size());
        Assert.assertEquals(729, constraints.get(0).getVariablesCoefficientsAsString().split(" ").length);
    }
}
