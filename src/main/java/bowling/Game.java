package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public static final int FINAL_FRAME_INDEX = 9;
    public static final int TOTAL_NUMBER_OF_FRAMES = 10;
    int currentFrame = 0;
    List<Frame> frames = new ArrayList<>(TOTAL_NUMBER_OF_FRAMES);

    public Game() {
        IntermediateFrame current = new IntermediateFrame();
        frames.add(current);
        for (int i = 2; i <= 9; i++) {
            var next = new IntermediateFrame();
            current.setNext(next);
            current = next;
            frames.add(next);
        }
        var next = new FinalFrame();
        current.setNext(next);
        frames.add(next);
        currentFrame = 0;
    }

    public void roll(int pins) throws IllegalRollException, NoMoreRollsException {
        frames.get(currentFrame).roll(pins);
        if (!frames.get(currentFrame).hasMoreRolls() && canProceedWithNextFrame()) {
            currentFrame++;
        }
    }

    private boolean canProceedWithNextFrame() {
        return currentFrame < FINAL_FRAME_INDEX;
    }

    public int score() {
        return frames.stream().mapToInt(Frame::score).sum();
    }
}
