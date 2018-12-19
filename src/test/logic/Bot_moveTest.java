package logic;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class Bot_moveTest {
  Bot_move bot_move = mock(Bot_move.class);
  @Test
  public void setDestinationX() {
    bot_move.setDestinationX(0);
    assertEquals(bot_move.destinationX,0);
  }

  @Test
  public void setDestinationY() {
    bot_move.setDestinationY(0);
    assertEquals(bot_move.destinationY,0);
  }

  @Test
  public void calculate_best_move_of_one_checker() {
  }

  @Test
  public void getPath() {
  }
}