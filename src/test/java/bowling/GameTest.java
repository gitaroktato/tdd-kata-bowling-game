package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        this.game = new Game();
    }

    private void makeATestRoll(Game game, int pins) {
        try {
            game.roll(pins);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_withOnlyOneRollWhichDoesNotHit() throws Exception {
        game.roll(0);
        var score = game.score();
        assertEquals(0, score);
    }

    @Test
    public void test_withOnlyOnePinHit() throws Exception {
        game.roll(1);
        var score = game.score();
        assertEquals(1, score);
    }


    @Test
    public void test_withFullGame() {
        IntStream.range(0, 20).forEach(
                i -> makeATestRoll(game, 1));
        var score = game.score();
        assertEquals(20, score);
    }

    @Test
    public void test_withMorePinsThanPossibleInFirstFrame() throws Exception {
        game.roll(10);
        game.roll(1);
        // ????
    }

    @Test
    public void test_withSpare() throws Exception {
        game.roll(9);
        game.roll(1);
        game.roll(5);
        game.roll(3);
        assertEquals(23, game.score());
    }


    @Test
    public void test_withTenFrames() {
        IntStream.rangeClosed(1, 20).forEach(i ->
            makeATestRoll(game, 1));
        assertEquals(20, game.score());
    }

    @Test
    public void test_withMoreThanTenFrames(){
        IntStream.rangeClosed(1, 20).forEach(i ->
            makeATestRoll(game, 1));
        assertThrows(NoMoreRollsException.class, () -> game.roll(1));
    }

    @Test
    public void test_withStrike() throws Exception {
        game.roll(10); // strike
        game.roll(3);
        game.roll(4);
        assertEquals(24, game.score());
    }


    @Test
    public void test_withMultipleStrikes() throws Exception {
        game.roll(10); // strike : 30
        game.roll(10); // strike : 20
        game.roll(10); // strike : 10
        assertEquals(60, game.score());
    }

    @Test
    public void test_withPerfectGame() {
        IntStream.rangeClosed(1, 12).forEach(i -> {
            makeATestRoll(game, 10);
        });
        assertEquals(300, game.score());
    }

    @Test
    public void test_withRealGameFromExample() throws Exception {
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
