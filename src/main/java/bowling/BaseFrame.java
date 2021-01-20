package bowling;

public abstract class BaseFrame implements Frame {
    static final int TOTAL_NUMBER_OF_PINS = 10;
    private int firstRoll;
    private int secondRoll;

    protected static void verifyNumberOfPinsIsLessThanMaximum(int pins) throws IllegalRollException {
        if (pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }
    }

    protected static void verifyNumberOfTriesIsLessThanMaximum(int tries, int maximum) throws NoMoreRollsException {
        if (tries >= maximum) {
            throw new NoMoreRollsException();
        }
    }

    @Override
    public final int getFirstRoll() {
        return firstRoll;
    }

    @Override
    public final int getSecondRoll() {
        return secondRoll;
    }

    final void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    final void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    final boolean isStrike() {
        return firstRoll == 10;
    }

    final boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }
}
