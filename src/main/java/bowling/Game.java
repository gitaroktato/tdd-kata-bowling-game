package bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    private Frame[] frames = new Frame[10];
    private int turn = 0;

    public Game() {
        frames[0] = new IntermediateFrame();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> addFrame(i, new IntermediateFrame()));
        addFrame(9, new TenthFrame());
    }

    private void addFrame(int i, Frame frame) {
        frames[i] = frame;
        frames[i-1].setNext(frame);
    }

    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        frames[turn].roll(pins);
        if (frames[turn].noMoreRolls()) {
            turn++;
        }
    }

    public int score() {
        return Arrays.stream(frames).mapToInt(Frame::score).sum();
    }

}
