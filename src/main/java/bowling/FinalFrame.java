package bowling;

public class FinalFrame extends BaseFrame implements Frame  {

    private int thirdRoll;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        if (pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }

        if (isThirdRollAllowed()) {
            this.thirdRoll = pins;
        } else {
            super.roll(pins);
        }
    }

    private boolean isThirdRollAllowed() {
        return (isSpare() || isStrike());
    }

    @Override
    public int score() {
        return getFirstRoll() + getSecondRoll() + thirdRoll;
    }
}
