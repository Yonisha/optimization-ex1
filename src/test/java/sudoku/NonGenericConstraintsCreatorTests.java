package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.GenericLpSolver.Constraint;
import sudoku.NonGenericLpSolver.NonGenericConstraintsCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NonGenericConstraintsCreatorTests {

//    @Test
//    public void createForCells(){
//
//        List<Integer> inputBoard = new ArrayList<>(Arrays.asList(5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0, 0));
//
//        NonGenericConstraintsCreator constraintsCreator = new NonGenericConstraintsCreator();
//        List<Constraint> constraints = constraintsCreator.createForCells(inputBoard);
//        List<String> collect = constraints.stream().map(c -> c.getCoefficientsAsString()).collect(Collectors.toList());
//        Assert.assertEquals(11, constraints.size());
//    }
}
