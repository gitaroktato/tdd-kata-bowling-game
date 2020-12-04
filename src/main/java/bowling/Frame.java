package bowling;

public class Frame {

    private static final int TOTAL_NUMBER_OF_PINS = 10;
    private static final int MAXIMUM_TRIES_IN_A_FRAME = 2;
    private int tries = 0;
    private int firstRoll;
    private int secondRoll;
    private Frame next = new NoFrame();

    void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        if (score() + pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }
        if (tries >= MAXIMUM_TRIES_IN_A_FRAME) {
            throw new NoMoreRollsException();
        }

        if (tries == 0) {
            firstRoll = pins;
        } else if (tries == 1) {
            secondRoll = pins;
        }
        tries++;
    }

    int getFirstRoll() {
        return firstRoll;
    }

    int getSecondRoll() {
        return secondRoll;
    }

    int score() {
        var frameScore = firstRoll + secondRoll;
        if (isSpare()) {
            return frameScore + next.getFirstRoll();
        }
        return frameScore;
    }

    private boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }

    public void setNext(Frame next) {
        this.next = next;
    }
}
