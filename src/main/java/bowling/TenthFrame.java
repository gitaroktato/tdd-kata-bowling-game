package bowling;

public class TenthFrame extends BaseFrame {

    private static final int MAXIMUM_TRIES = 3;
    private int tries = 0;
    private int thirdRoll;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPinsIsLessThanMaximum(pins);
        if (!isThirdRollAllowed()) {
            verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
        }
        verifyNumberOfTriesIsLessThanMaximum(tries, getMaximumTries());

        if (tries == 0) {
            setFirstRoll(pins);
        } else if (tries == 1) {
            setSecondRoll(pins);
        } else if (tries == 2) {
            thirdRoll = pins;
        }
        tries++;
    }

    private boolean isThirdRollAllowed() {
        return isSpare() || isStrike();
    }

    @Override
    public int score() {
        var frameScore = getFirstRoll() + getSecondRoll();
        if (isStrike()) {
            return frameScore + thirdRoll;
        }
        if (isSpare()) {
            return frameScore + thirdRoll;
        }
        return frameScore;
    }

    @Override
    public void setNext(Frame frame) {
        throw new UnsupportedOperationException("Tenth frame has no next frame");
    }

    private int getMaximumTries() {
        if (isThirdRollAllowed()) {
            return MAXIMUM_TRIES;
        } else {
            return MAXIMUM_TRIES - 1;
        }
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
