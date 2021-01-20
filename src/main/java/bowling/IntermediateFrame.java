package bowling;

public class IntermediateFrame extends BaseFrame {

    public static final int MAXIMUM_TRIES = 2;
    private int tries = 0;
    private Frame next;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPinsIsLessThanMaximum(pins);
        verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
        verifyNumberOfTriesIsLessThanMaximum(tries, MAXIMUM_TRIES);

        if (tries == 0) {
            setFirstRoll(pins);
        } else if (tries == 1) {
            setSecondRoll(pins);
        }
        tries++;
    }

    @Override
    public int score() {
        var frameScore = getFirstRoll() + getSecondRoll();
        if (isStrike() && hasNext()) {
            return frameScore + next.getFirstRoll() + next.getSecondRollForBonus();
        }
        if (isSpare() && hasNext()) {
            return frameScore + next.getFirstRoll();
        }
        return frameScore;
    }

    private boolean hasNext() {
        return next != null;
    }

    @Override
    public void setNext(Frame next) {
        this.next = next;
    }

    @Override
    public boolean noMoreRolls() {
        return isStrike() || tries == MAXIMUM_TRIES;
    }

    @Override
    public int getSecondRollForBonus() {
        if (isStrike() && hasNext()) {
            return next.getFirstRoll();
        } else {
            return getSecondRoll();
        }
    }
}
