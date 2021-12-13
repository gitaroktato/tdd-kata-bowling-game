package bowling;

public class TenthFrame extends BaseFrame {

    private static final int FIRST_TRY = 0;
    private static final int MAXIMUM_TRIES = 2;
    private static final int SECOND_TRY = 1;
    private int tries;
    private int thirdRoll;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfTriesIsLessThanMaximum(tries, getMaximumTries());
        verifyNumberOfPinsIsLessThanMaximum(pins);
        if (!isThirdRollAllowed()) {
            verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
        }
        if (tries == FIRST_TRY) {
            setFirstRoll(pins);
        } else if (tries == SECOND_TRY){
            setSecondRoll(pins);
        } else {
            thirdRoll = pins;
        }
        tries++;
    }

    private int getMaximumTries() {
        if (isThirdRollAllowed()) {
            return MAXIMUM_TRIES + 1;
        }
        return MAXIMUM_TRIES;
    }

    private boolean isThirdRollAllowed() {
        return isSpare() || isStrike();
    }

    @Override
    public int score() {
        return getFirstRoll() + getSecondRoll() + thirdRoll;
    }

    @Override
    public void setNext(Frame frame) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean noMoreRolls() {
        return tries == getMaximumTries();
    }

    @Override
    public int getSecondRollForBonus() {
        return getSecondRoll();
    }
}
