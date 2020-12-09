package bowling;

abstract class BaseFrame implements Frame {

    static final int TOTAL_NUMBER_OF_PINS = 10;
    private int tries = 0;
    private int firstRoll;
    private int secondRoll;

    @Override
    public int getFirstRoll() {
        return firstRoll;
    }

    @Override
    public int getSecondRoll() {
        return secondRoll;
    }

    final int getNumberOfTries() {
        return tries;
    }

    final void setFirstRoll(int roll) {
        firstRoll = roll;
        tries++;
    }

    final void setSecondRoll(int roll) {
        secondRoll = roll;
        tries++;
    }

    final boolean isStrike() {
        return firstRoll == 10;
    }

    boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }

    final void verifyNumberOfPins(int pins) throws IllegalRollException {
        if (pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }
    }

}
