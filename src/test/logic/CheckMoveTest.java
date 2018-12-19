package logic;

import logic.CheckMove;
import logic.PossibleMove;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CheckMoveTest {
  private int [][] fields1 = new int[][]{
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 8, 1, 8, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 8, 1, 1, 8, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 8, 8, 2, 2, 2, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };
  private int [][] fields2 = new int[][]{ //for one hop
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 8, 8, 8, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 8, 9, 8, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 8, 8, 8, 1, 1, 1, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };
  private int [][] fields3 = new int[][] { //for one hop
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 8, 1, 8, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 8, 8, 8, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 8, 8, 9, 8, 8, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 8, 8, 8, 1, 1, 1, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 8, 1, 8, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };
  private int [][] fields4 = new int[][]{ //for longhop
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 8, 1, 8, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 8, 1, 9, 1, 8, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 8, 1, 8, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };
  private int [][] fields5 = new int[][]{ //for longhop occupied
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 8, 1, 1, 1, 8, 1, 1, 1, 8, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 8, 1, 8, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 8, 1, 8, 1, 9, 1, 8, 1, 8, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 8, 1, 8, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
    {0, 0, 0, 0, 8, 1, 1, 1, 8, 1, 1, 1, 8, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };

  @Test
  public void shortest_path () {
    //szukana ścieżka od (12,12) do (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(12,12);
    ArrayList <Integer> test = new ArrayList<>();
    test.add(8);
    test.add(8);
    test.add(10);
    test.add(10);
    test.add(12);
    test.add(12);
    assertArrayEquals(test.toArray(), checkMove.getPath(8,8).toArray());
    assertEquals(2, checkMove.possible_move[8][8].number_of_step);
  }
//path to neighbour
  @Test
  public void path_to_adjacent_cross () {
    //path from (5,5) to neighbour cross
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(5,5);
    test.add(6);
    test.add(6);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(6,6).toArray());
    test.clear();
    test.add(4);
    test.add(4);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(4,4).toArray());
  }
  @Test
  public void path_to_adjacent_horizontal () {
    //path from (5,5) to neighbour horizontal
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(5,5);
    test.add(4);
    test.add(5);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(4,5).toArray());
    test.clear();
    test.add(6);
    test.add(5);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(6,5).toArray());
  }
  @Test
  public void path_to_adjacent_vertical () {
    //path from (5,5) to neighbour vertical
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(5,5);
    test.add(5);
    test.add(4);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(5,4).toArray());
    test.clear();
    test.add(5);
    test.add(6);
    test.add(5);
    test.add(5);
    assertArrayEquals(test.toArray(), checkMove.getPath(5,6).toArray());
  }
// path with only one hop
  @Test
  public void path_with_one_hop_cross () {
    //path from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(8,8);
    test.add(10);
    test.add(10);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(10,10).toArray());
    test.clear();
    test.add(6);
    test.add(6);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(6,6).toArray());
  }
  @Test
  public void path_with_one_hop_horizontal () {
    //path from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(8,8);
    test.add(10);
    test.add(8);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(10,8).toArray());
    test.clear();
    test.add(6);
    test.add(8);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(6,8).toArray());
  }
  @Test
  public void path_with_one_hop_vertical () {
    //path from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    ArrayList <Integer> test = new ArrayList<>();
    checkMove.setXY(8,8);
    test.add(8);
    test.add(10);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(8,10).toArray());
    test.clear();
    test.add(8);
    test.add(6);
    test.add(8);
    test.add(8);
    assertArrayEquals(test.toArray(), checkMove.getPath(8,6).toArray());
  }
//possible move to neighbour, free spot
  @Test
  public void possible_move_to_adjacent_cross () {
    // from (5,5) to neighbour cross
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(5,5);
    assertTrue(checkMove.possible_move[6][6].possible);
    assertTrue(checkMove.possible_move[4][4].possible);
  }
  @Test
  public void possible_move_to_adjacent_horizontal () {
    // from (5,5) to neighbour horizontal
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(5,5);
    assertTrue(checkMove.possible_move[4][5].possible);
    assertTrue(checkMove.possible_move[6][5].possible);
  }
  @Test
  public void possible_move_to_adjacent_vertical () {
    //from (5,5) to neighbour vertical
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(5,5);
    assertTrue(checkMove.possible_move[5][4].possible);
    assertTrue(checkMove.possible_move[5][6].possible);

  }
  @Test
  public void possible_move_not_allow_to_adjacent_cross () {
    // from (5,5) to neighbour cross
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(5,5);
    assertFalse(checkMove.possible_move[6][4].possible);
    assertFalse(checkMove.possible_move[4][6].possible);
  }
//possible move to neighbour, free spot
  @Test
  public void possible_move_with_one_hop_cross () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[10][10].possible);
    assertTrue(checkMove.possible_move[6][6].possible);
  }
  @Test
  public void possible_move_with_one_hop_horizontal () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[10][8].possible);
    assertTrue(checkMove.possible_move[6][8].possible);
  }
  @Test
  public void possible_move_with_one_hop_vertical () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[8][10].possible);
    assertTrue(checkMove.possible_move[8][6].possible);
  }
  @Test
  public void possible_move_not_allow_one_hop_cross () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[10][6].possible);
    assertFalse(checkMove.possible_move[6][10].possible);
  }
//possible move to neighbour, with occupied spot
  @Test
  public void possible_move_to_adjacent_cross_with_occupied_spot () {
    // from (5,5) to neighbour cross
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[9][9].possible);
    assertFalse(checkMove.possible_move[7][7].possible);
  }
  @Test
  public void possible_move_to_adjacent_horizontal_with_occupied_spot () {
    // from (5,5) to neighbour horizontal
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[7][8].possible);
    assertFalse(checkMove.possible_move[9][8].possible);
  }
  @Test
  public void possible_move_to_adjacent_vertical_with_occupied_spot () {
    //from (5,5) to neighbour vertical
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields2);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[8][7].possible);
    assertFalse(checkMove.possible_move[8][9].possible);

  }
  @Test
  public void possible_move_not_allow_to_adjacent_cross_with_occupied_spot () {
    // from (5,5) to neighbour cross
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields1);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[9][7].possible);
    assertFalse(checkMove.possible_move[7][9].possible);
  }
//possible move to neighbour, with occupied spot
  @Test
  public void possible_move_with_one_hop_cross_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields3);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[10][10].possible);
    assertFalse(checkMove.possible_move[6][6].possible);
  }
  @Test
  public void possible_move_with_one_hop_horizontal_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields3);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[10][8].possible);
    assertFalse(checkMove.possible_move[6][8].possible);
  }
  @Test
  public void possible_move_with_one_hop_vertical_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields3);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[8][10].possible);
    assertFalse(checkMove.possible_move[8][6].possible);
  }
  @Test
  public void possible_move_not_allow_one_hop_cross_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields3);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[6][10].possible);
    assertFalse(checkMove.possible_move[10][6].possible);
  }
//possible longhop, free spot longop = true
  @Test
  public void possible_move_with_one_longhop_cross_with_free_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[12][12].possible);
    assertTrue(checkMove.possible_move[4][4].possible);
  }
  @Test
  public void possible_move_with_one_longhop_horizontal_with_free_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[12][8].possible);
    assertTrue(checkMove.possible_move[4][8].possible);
  }
  @Test
  public void possible_move_with_one_longhop_vertical_with_free_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[8][12].possible);
    assertTrue(checkMove.possible_move[8][4].possible);
  }

//possible longhop, occupation spot longhop = true
  @Test
  public void possible_move_with_one_longhop_cross_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields5);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[12][12].possible);
    assertFalse(checkMove.possible_move[4][4].possible);
  }
  @Test
  public void possible_move_with_one_longhop_horizontal_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields5);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[12][8].possible);
    assertFalse(checkMove.possible_move[4][8].possible);
  }
  @Test
  public void possible_move_with_one_longhop_vertical_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields5);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[8][12].possible);
    assertFalse(checkMove.possible_move[8][4].possible);
  }
  @Test
  public void possible_move_not_allow_one_longhop_cross_with_occupied_spot () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields5);
    checkMove.setXY(8,8);
    assertTrue(checkMove.possible_move[8][8].possible);
    assertFalse(checkMove.possible_move[6][10].possible);
    assertFalse(checkMove.possible_move[10][6].possible);
  }

//possible longhop, free spot longop = false
  @Test
  public void possible_move_with_one_longhop_cross_with_free_spot_off () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(false,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[12][12].possible);
    assertFalse(checkMove.possible_move[4][4].possible);
  }
  @Test
  public void possible_move_with_one_longhop_horizontal_with_free_spot_off () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(false,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[12][8].possible);
    assertFalse(checkMove.possible_move[4][8].possible);
  }
  @Test
  public void possible_move_with_one_longhop_vertical_with_free_spot_off () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(false,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[8][12].possible);
    assertFalse(checkMove.possible_move[8][4].possible);
  }
  @Test
  public void possible_move_not_allow_one_longhop_cross_with_free_spot_off () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(false,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    assertFalse(checkMove.possible_move[6][10].possible);
    assertFalse(checkMove.possible_move[10][6].possible);
  }
  @Test
  public void getpossiblemve () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    int [][] test = checkMove.getPossible_move_array();
    int [][] tmp = new int[][]{ 
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    assertArrayEquals(test,tmp);
  }
  @Test
  public void getpossiblemveObject () {
    //from (8,8)
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);
    PossibleMove [][] possibleMoves = checkMove.getPossible_move();

    int [][] tmp = new int[][]{
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    for(int i = 0; i< fields4.length; i++)
      for(int j =0; j< fields4.length; j++)
        if(possibleMoves[i][j].possible)
          assertEquals(1,tmp[i][j]);
        else
          assertEquals(0,tmp[i][j]);
  }
  @Test
  public void checkMoveTest () {
    CheckMove checkMove = new CheckMove(true,6);
    checkMove.setFields(fields4);
    checkMove.setXY(8,8);

    int [][] tmp = new int[][]{
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    for(int i = 0; i< fields4.length; i++)
      for(int j =0; j< fields4.length; j++)
        if(checkMove.check_move(i,j))
          assertEquals(1,tmp[i][j]);
        else
          assertEquals(0,tmp[i][j]);
  }

}
