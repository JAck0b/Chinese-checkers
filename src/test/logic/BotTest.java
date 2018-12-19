package logic;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BotTest {
  //Game game = new Game(null,2,2,true);
  Bot bot = new Bot();
  private int [][] fields = new int[][]{
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 1, 2, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
  };

  @Test
  public void setFields() {
    bot.setFields(fields);
    assertArrayEquals(bot.fields,fields);
  }

  @Test
  public void setLonghop() {
    bot.setLonghop(true);
    assertTrue(bot.longhop);
    bot.setLonghop(false);
    assertFalse(bot.longhop);
  }

  @Test
  public void setId() {
    bot.setId(0);
    assertEquals(0,bot.id);
  }

  @Test
  public void setSteps_in_game() {
    bot.setSteps_in_game(0);
    assertEquals(0,bot.steps_in_game);
  }

  @Test
  public void setBases() {
    ArrayList<int[][]> bases;
    bases = new ArrayList<>();
    int[][] base2 = new int[][]{ //pionki z nr2 dążą do ... i symetrycznie 7 do x=y y=x
      {4, 0}, {4, 1}, {5, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 3}, {4, 3}, {6, 3}, {5, 3}
    };
    bases.add(base2);

    int[][] base3 = new int[][]{   //symetrai x=y y=x dla 6
      {12, 4}, {11, 4}, {12, 5}, {11, 5}, {10, 4}, {12, 6}, {9, 4}, {12, 7}, {10, 5}, {11, 6}
    };
    bases.add(base3);

    int[][] base4 = new int[][]{   //symetrai x=y y=x dla 5
      {16, 12}, {15, 12}, {15, 11}, {14, 12}, {14, 11}, {14, 10}, {13, 12}, {13, 9}, {13, 11}, {13, 10}
    };
    bases.add(base4);

    bot.setBases(bases);
    assertEquals(bases,bot.bases);
  }

  @Test
  public void getPath_best_move() {
    ArrayList path_best_move = new ArrayList<>();
    assertEquals(bot.getPath_best_move(),path_best_move);

  }

  @Test
  public void calculate_best_move() {
  }

  @Test
  public void isBot_skip_move() {
  }
}