package bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    private int[] rolls = new int[21];
    private int turn = 0;

    public void roll(int pins) {
        rolls[turn++] = pins;
    }

    public int score() {
        return Arrays.stream(rolls).sum();
    }

}
