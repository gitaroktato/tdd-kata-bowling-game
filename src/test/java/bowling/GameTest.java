package bowling;

import bowling.Game;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void test_withOnlyOneRollWhichDoesNotHit() {
        var game = new Game();
        game.roll(0);
        var score = game.score();
        assertEquals(0, score);
    }

    @Test
    public void test_withOnlyOnePinHit() {
        var game = new Game();
        game.roll(1);
        var score = game.score();
        assertEquals(1, score);
    }


    @Test
    public void test_withFullGame() {
        var game = new Game();
        IntStream.range(0, 20).forEach(
                i -> game.roll(1));
        var score = game.score();
        assertEquals(20, score);
    }

    @Test
    public void test_withMorePinsThanPossibleInFirstFrame() {
        var game = new Game();
        game.roll(10);
        game.roll(1);
        // ????
    }

    @Test
    @Disabled
    public void test_withSpare() {
        var game = new Game();
        game.roll(9);
        game.roll(1);
        game.roll(5);
        game.roll(3);
        assertEquals(23, game.score());
    }

    // TODO validation
    // More than 10 pins?
    // More than 10 frames?
    // Getting score before all the roles?
    // Might make sense to show the temporary results?

    // TODO integrity??
    // Two rolls with the sum of 10 in each frame.
}
