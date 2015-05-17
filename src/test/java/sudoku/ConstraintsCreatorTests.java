package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.GenericLpSolver.Constraint;
import sudoku.GenericLpSolver.ConstraintsCreator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstraintsCreatorTests {

    @Test
    public void createForCells(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForCells();
        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
        Assert.assertEquals(81, constraints.size());
    }

    @Test
    public void createForRows(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForRows();
        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
        Assert.assertEquals(81, constraints.size());
    }

    @Test
    public void createForColumns(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForColumns();
        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
        Assert.assertEquals(81, constraints.size());
    }

    @Test
    public void createForSquares(){
        ConstraintsCreator constraintsCreator = new ConstraintsCreator();
        List<Constraint> constraints = constraintsCreator.createForSquares();
        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
        Assert.assertEquals(81, constraints.size());
    }
}
