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

  }

  @Test
  public void getPath_best_move() {
  }

  @Test
  public void calculate_best_move() {
  }

  @Test
  public void isBot_skip_move() {
  }
}