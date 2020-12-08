package bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    private Frame[] frames = new Frame[10];

    public Game() {
        IntStream.rangeClosed(0, 8).forEach(i -> frames[i] = new IntermediateFrame());
        frames[9] = new FinalFrame();
    }

    public void roll(int pins) {
        rolls[turn++] = pins;
    }

    public int score() {
        return Arrays.stream(rolls).sum();
    }
}
