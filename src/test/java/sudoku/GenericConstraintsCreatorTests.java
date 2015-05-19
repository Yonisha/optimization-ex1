package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.GenericLpSolver.Constraint;
import sudoku.GenericLpSolver.GenericConstraintsCreator;
import sudoku.NonGenericLpSolver.NonGenericConstraintsCreator;

import java.util.List;
import java.util.stream.Collectors;

public class GenericConstraintsCreatorTests {

//    @Test
//    public void createForCells(){
//        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
//        List<Constraint> constraints = genericConstraintsCreator.createForCells();
//        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
//        Assert.assertEquals(81, constraints.size());
//    }
//
//    @Test
//    public void createForRows(){
//        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
//        List<Constraint> constraints = genericConstraintsCreator.createForRows();
//        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
//        Assert.assertEquals(81, constraints.size());
//    }
//
//    @Test
//    public void createForColumns(){
//        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
//        List<Constraint> constraints = genericConstraintsCreator.createForColumns();
//        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
//        Assert.assertEquals(81, constraints.size());
//    }
//
//    @Test
//    public void createForSquares(){
//        GenericConstraintsCreator genericConstraintsCreator = new GenericConstraintsCreator();
//        List<Constraint> constraints = genericConstraintsCreator.createForSquares();
//        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
//        Assert.assertEquals(81, constraints.size());
//    }
}
