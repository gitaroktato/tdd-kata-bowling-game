package bowling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    @Disabled
    public void test_withOnlyOneRollWhichDoesNotHit() throws IllegalRollException, NoMoreRollsException {
        var game = new Game();
        game.roll(0);
        var score = game.score();
        assertEquals(0, score);
    }

    @Test
    @Disabled
    public void test_withOnlyOnePinHit() throws IllegalRollException, NoMoreRollsException {
        var game = new Game();
        game.roll(1);
        var score = game.score();
        assertEquals(1, score);
    }


    @Test
    @Disabled
    public void test_withFullGame() {
        var game = new Game();
        IntStream.range(0, 20).forEach(
                i -> rollSilently(game, 1));
        var score = game.score();
        assertEquals(20, score);
    }

    private void rollSilently(Game game, int pins)  {
        try {
            game.roll(pins);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Disabled
    public void test_withMorePinsThanPossibleInFirstFrame() throws Exception {
        var game = new Game();
        game.roll(7);
        assertThrows(IllegalRollException.class, () -> game.roll(7));
    }

    @Test
    @Disabled
    public void test_withSpare() throws Exception {
        var game = new Game();
        game.roll(9);
        game.roll(1);
        game.roll(5);
        game.roll(3);
        assertEquals(23, game.score());
    }

    @Test
    @Disabled
    public void test_withAllStrikes() throws Exception {
        var game = new Game();
        game.roll(10); // strike : 30
        game.roll(10); // strike : 20
        game.roll(10); // strike : 10
        assertEquals(60, game.score());
    }

    @Test
    @Disabled
    public void test_withPerfectGame() {
        var game = new Game();
        IntStream.rangeClosed(1, 12).forEach(i -> {
            rollSilently(game, 10);
        });
        assertEquals(300, game.score());
    }

    @Test
    @Disabled
    public void test_withRealGameFromExample() throws Exception {
        var game = new Game();
        game.roll(1); // frame1
        game.roll(4);
        game.roll(4); // frame2
        game.roll(5);
        game.roll(6); // frame3
        game.roll(4); // spare
        game.roll(5); // frame4
        game.roll(5); // spare
        game.roll(10); // frame5 // strike
        game.roll(0); // frame6
        game.roll(1);
        game.roll(7); // frame7
        game.roll(3); // spare
        game.roll(6); // frame8
        game.roll(4); // spare
        game.roll(10); // frame9 // strike
        game.roll(2); // frame10
        game.roll(8); // spare
        game.roll(6);
        assertEquals(133, game.score());
    }

}
