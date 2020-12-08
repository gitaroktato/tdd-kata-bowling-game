package bowling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    public void test_withOnlyOneRollWhichDoesNotHit() throws Exception {
        var game = new Game();
        game.roll(0);
        var score = game.score();
        assertEquals(0, score);
    }

    @Test
    public void test_withOnlyOnePinHit() throws Exception {
        var game = new Game();
        game.roll(1);
        var score = game.score();
        assertEquals(1, score);
    }


    @Test
    public void test_withFullGame() {
        var game = new Game();
        IntStream.range(0, 20).forEach(
                i -> {
                    try {
                        game.roll(1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        var score = game.score();
        assertEquals(20, score);
    }

    @Test
    public void test_withMorePinsThanPossibleInFirstFrame() throws Exception {
        var game = new Game();
        game.roll(10);
        game.roll(1);
        // ????
    }

    @Test
    public void test_withSpare() throws Exception {
        var game = new Game();
        game.roll(9);
        game.roll(1);
        game.roll(5);
        game.roll(3);
        assertEquals(23, game.score());
    }


    @Test
    public void test_withTenFrames() throws Exception {
        var game = new Game();
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                game.roll(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(20, game.score());
    }

    @Test
    public void test_withMoreThanTenFrames() throws Exception {
        var game = new Game();
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                game.roll(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        assertThrows(NoMoreRollsException.class, () -> game.roll(1));
    }

    @Test
    public void test_withStrike() throws Exception {
        var game = new Game();
        game.roll(10); // strike
        game.roll(3);
        game.roll(4);
        assertEquals(24, game.score());
    }


    @Test
    public void test_withMultipleStrikes() throws Exception {
        var game = new Game();
        game.roll(10); // strike : 30
        game.roll(10); // strike : 20
        game.roll(10); // strike : 10
        assertEquals(60, game.score());
    }

    @Test
    public void test_withPerfectGame() throws Exception {
        var game = new Game();
        IntStream.rangeClosed(1, 11).forEach(i -> {
            try {
                game.roll(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(300, game.score());
    }
}
