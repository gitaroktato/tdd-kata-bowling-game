package bowling;

abstract class BaseFrame implements Frame {

    static final int TOTAL_NUMBER_OF_PINS = 10;
    private static final int MAXIMUM_TRIES_IN_A_FRAME = 2;
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

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        if (firstRoll + secondRoll + pins > TOTAL_NUMBER_OF_PINS) {
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

    boolean isStrike() {
        return firstRoll == 10;
    }

    boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }

    @Override
    public boolean hasMoreRolls() {
        return !isStrike() && tries < MAXIMUM_TRIES_IN_A_FRAME;
    }
}
