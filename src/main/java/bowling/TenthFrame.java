package bowling;

public class TenthFrame extends BaseFrame {

    private int tries = 0;
    private int thirdRoll;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPinsIsLessThanMaximum(pins);
        if (!isThirdRollAllowed()) {
            verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
            verifyNumberOfTriesIsLessThanMaximum(tries,2);
        } else {
            verifyNumberOfTriesIsLessThanMaximum(tries,3);
        }

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

}
