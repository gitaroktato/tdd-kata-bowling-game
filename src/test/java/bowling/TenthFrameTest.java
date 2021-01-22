package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TenthFrameTest {

    private TenthFrame frame;

    @BeforeEach
    public void setUp () {
        this.frame = new TenthFrame();
    }

    @Test
    public void testRoll_withTwoTrials() throws Exception {
        frame.roll(3);
        frame.roll(7);
        assertEquals(3, frame.getFirstRoll());
        assertEquals(7, frame.getSecondRoll());
    }

    @Test
    public void testRoll_withMoreTrials() throws Exception {
        frame.roll(0);
        frame.roll(0);
        assertThrows(NoMoreRollsException.class,
                () -> frame.roll(0));
    }

    @Test
    public void testRoll_withMoreTrialsAfterSpare() throws Exception {
        frame.roll(3);
        frame.roll(7);
        frame.roll(2);
        assertThrows(NoMoreRollsException.class,
                () -> frame.roll(2));
    }
    @Test
    public void testRoll_withMoreTrialsAfterStrike() throws Exception {
        frame.roll(10);
        frame.roll(7);
        frame.roll(2);
        assertThrows(NoMoreRollsException.class,
                () -> frame.roll(2));
    }

    @Test
    public void testRoll_withTenPins() throws Exception {
        frame.roll(10);
        frame.roll(0);
        assertEquals(10, frame.score());
    }

    @Test
    public void testRoll_withMoreThanTenPins() throws Exception {
        frame.roll(9);
        assertThrows(IllegalRollException.class, () -> frame.roll(2));
    }

    @Test
    public void testRoll_withSpare() throws Exception  {
        frame.roll(3);
        frame.roll(7);
        frame.roll(2);
        assertEquals(12, frame.score());
    }

    @Test
    public void testRoll_withFourTries() throws Exception  {
        frame.roll(3);
        frame.roll(7);
        frame.roll(2);
        assertThrows(NoMoreRollsException.class, () -> frame.roll(5));
    }

    @Test
    public void testRoll_withStrike() throws Exception  {
        frame.roll(10);
        frame.roll(2);
        assertEquals(12, frame.score());
    }

    @Test
    public void testRoll_withStrikeAndMoreThanTenPinsOnSecondRoll() throws Exception  {
        frame.roll(10);
        assertThrows(IllegalRollException.class, () -> frame.roll(12));
    }

    @Test
    public void testRoll_withThreeStrikes() throws Exception  {
        frame.roll(10);
        frame.roll(10);
        frame.roll(10);
        assertEquals(30, frame.score());
    }

    @Test
    public void testNoMoreRolls_withoutAny() throws Exception {
        assertFalse(frame.noMoreRolls());
    }

    @Test
    public void testNoMoreRolls_withoutStrikeOrSpare() throws Exception {
        frame.roll(3);
        frame.roll(4);
        assertTrue(frame.noMoreRolls());
    }

    @Test
    public void testNoMoreRolls_withSpare() throws Exception {
        frame.roll(3);
        frame.roll(7);
        assertFalse(frame.noMoreRolls());

        frame.roll(10);
        assertTrue(frame.noMoreRolls());
    }

    @Test
    public void testNoMoreRolls_withStrike() throws Exception {
        frame.roll(10);
        assertFalse(frame.noMoreRolls());

        frame.roll(10);
        assertFalse(frame.noMoreRolls());

        frame.roll(10);
        assertTrue(frame.noMoreRolls());
    }
}
