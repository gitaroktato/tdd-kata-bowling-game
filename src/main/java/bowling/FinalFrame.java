package bowling;

public class FinalFrame extends BaseFrame implements Frame  {

    private int thirdRoll;
    private boolean isThirdRollMade = false;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        if (pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }
        if (isThirdRollMade) {
            throw new NoMoreRollsException();
        }

        if (isThirdRollAllowed()) {
            this.thirdRoll = pins;
            isThirdRollMade = true;
        } else {
            super.roll(pins);
        }
    }

    private boolean isThirdRollAllowed() {
        return !isThirdRollMade && (isSpare() || isStrike());
    }

    @Override
    public int score() {
        if (isStrike()) {
            return getFirstRoll() + thirdRoll * 2;
        } else {
            return getFirstRoll() + getSecondRoll() + thirdRoll;
        }
    }

    @Override
    public boolean hasMoreRolls() {
        return super.hasMoreRolls() || isThirdRollAllowed();
    }

    @Override
    public int getNextRoll() {
        if (isStrike()) {
            return thirdRoll;
        } else {
            return getSecondRoll();
        }
    }
}
