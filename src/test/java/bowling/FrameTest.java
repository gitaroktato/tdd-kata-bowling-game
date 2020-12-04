package bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameTest {

    @Test
    public void testRoll_withTwoTrials() throws Exception {
        var frame = new Frame();
        frame.roll(3);
        frame.roll(7);
        assertEquals(3, frame.getFirstRoll());
        assertEquals(7, frame.getSecondRoll());
    }

    @Test
    public void testRoll_withMoreTrials() throws Exception {
        var frame = new Frame();
        frame.roll(0);
        frame.roll(0);
        assertThrows(NoMoreRollsException.class,
                () -> frame.roll(0));
    }

    @Test
    public void testRoll_withTenPins() throws Exception {
        var frame = new Frame();
        frame.roll(10);
        frame.roll(0);
        assertEquals(10, frame.score());
    }



    @Test
    public void testRoll_withMoreThanTenPins() throws Exception {
        var frame = new Frame();
        frame.roll(10);
        assertThrows(IllegalRollException.class, () -> frame.roll(1));
    }

    @Test
    public void testRoll_withSpare() throws Exception  {
        var frame = new Frame();
        var nextFrame = new Frame();
        frame.setNext(nextFrame);
        frame.roll(3);
        frame.roll(7);
        nextFrame.roll(2);
        assertEquals(12, frame.score());
    }

    // without next frame score?
}
