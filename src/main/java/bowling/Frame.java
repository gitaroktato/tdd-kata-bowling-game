package bowling;

public class Frame {

    private static final int TOTAL_NUMBER_OF_PINS = 10;
    private int trials = 0;
    private int firstRoll;
    private int secondRoll;

    void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        if (score() + pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }
        if (trials == 0) {
            firstRoll = pins;
        } else if (trials == 1) {
            secondRoll = pins;
        } else {
            throw new NoMoreRollsException();
        }
        trials++;
    }

    int getFirstRoll() {
        return firstRoll;
    }

    int getSecondRoll() {
        return secondRoll;
    }

    int score() {
        return firstRoll + secondRoll;
    }
}
