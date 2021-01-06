package bowling;

public class FinalFrame extends BaseFrame implements Frame  {

    private int thirdRoll;
    private boolean isThirdRollMade = false;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPins(pins);
        if (alreadyHadTwoTriesWithoutThirdRollAllowed() || isThirdRollMade) {
            throw new NoMoreRollsException();
        }
        if (!isThirdRollAllowed()
                && getFirstRoll() + getSecondRoll() + pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }

        if (getNumberOfTries() == 0) {
            setFirstRoll(pins);
        } else if (getNumberOfTries() == 1) {
            setSecondRoll(pins);
        } else {
            this.thirdRoll = pins;
            isThirdRollMade = true;
        }
    }

    private boolean alreadyHadTwoTriesWithoutThirdRollAllowed() {
        return getNumberOfTries() == 2 && !isThirdRollAllowed();
    }

    private boolean isThirdRollAllowed() {
        return !isThirdRollMade && (isSpare() || isStrike());
    }

    @Override
    public int score() {
        return getFirstRoll() + getSecondRoll() + thirdRoll;
    }

    @Override
    public boolean hasMoreRolls() {
        return isThirdRollAllowed() || getNumberOfTries() < 2;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void setNext(Frame frame) {
        throw new UnsupportedOperationException("FinalFrame can't have next frame");
    }

    @Override
    public int getSecondRollForBonus() {
        return getSecondRoll();
    }
}
