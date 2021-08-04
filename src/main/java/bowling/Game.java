package bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int FINAL_FRAME_INDEX = 9;
    public static final int TOTAL_NUMBER_OF_FRAMES = 10;
    int currentFrame = 0;
    List<Frame> frames = new ArrayList<>(TOTAL_NUMBER_OF_FRAMES);

    public Game() {
        frames.add(new IntermediateFrame());
    }

    public void roll(int pins) throws IllegalRollException, NoMoreRollsException {
        frames.get(currentFrame).roll(pins);
        if (!frames.get(currentFrame).hasMoreRolls()
                && canProceedWithNextFrame()) {
            addNextFrame();
            currentFrame++;
        }
    }

    private void addNextFrame() {
        Frame next;
        if (currentFrame == FINAL_FRAME_INDEX - 1) {
            next = new FinalFrame();
        } else {
            next = new IntermediateFrame();
        }
        frames.get(currentFrame).setNext(next);
        frames.add(next);
    }

    private boolean canProceedWithNextFrame() {
        return currentFrame < FINAL_FRAME_INDEX;
    }

    public int score() {
        return frames.stream().mapToInt(Frame::score).sum();
    }
}
