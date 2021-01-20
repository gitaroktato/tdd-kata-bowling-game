package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntermediateFrameTest {

    private IntermediateFrame frame;

    @BeforeEach
    public void setUp () {
        this.frame = new IntermediateFrame();
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
    public void testRoll_withTenPins() throws Exception {
        
        frame.roll(10);
        frame.roll(0);
        assertEquals(10, frame.score());
    }



    @Test
    public void testRoll_withMoreThanTenPins() throws Exception {
        
        frame.roll(7);
        assertThrows(IllegalRollException.class, () -> frame.roll(7));
    }

    @Test
    public void testRoll_withSpare() throws Exception  {
        
        var nextFrame = new IntermediateFrame();
        frame.setNext(nextFrame);
        frame.roll(3);
        frame.roll(7);
        nextFrame.roll(2);
        assertEquals(12, frame.score());
    }

    @Test
    public void testRoll_withStrike() throws Exception  {
        
        var nextFrame = new IntermediateFrame();
        frame.setNext(nextFrame);
        frame.roll(10);
        nextFrame.roll(2);
        nextFrame.roll(5);
        assertEquals(17, frame.score());
    }

    @Test
    public void testRoll_withAndWithoutNextFrame() throws Exception  {
        
        var nextFrame = new IntermediateFrame();
        frame.setNext(nextFrame);
        frame.roll(10);
        assertEquals(10, frame.score());
    }

    // without next frame score?
}
