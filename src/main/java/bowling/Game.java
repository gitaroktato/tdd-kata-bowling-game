package bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    private final Frame[] frames = new Frame[10];
    private int turn = 0;

    public Game() {
        frames[0] = new IntermediateFrame();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> addNewFrameAtIndex(new IntermediateFrame(), i));
        addNewFrameAtIndex(new TenthFrame(), 9);
    }

    private void addNewFrameAtIndex(Frame frame, int index) {
        frames[index] = frame;
        frames[index -1].setNext(frames[index]);
    }

    public void roll(int pins) throws IllegalRollException, NoMoreRollsException {
        frames[turn].roll(pins);
        if (frames[turn].noMoreRolls()) {
            turn++;
        }
    }

    public int score() {
        return Arrays.stream(frames)
                .mapToInt(Frame::score).sum();
    }

}
