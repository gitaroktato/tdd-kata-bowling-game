package bowling;

public class IntermediateFrame extends BaseFrame {


    private static final int FIRST_TRY = 0;
    private static final int MAXIMUM_TRIES = 2;
    private int tries;
    private Frame next;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfTriesIsLessThanMaximum(tries, MAXIMUM_TRIES);
        verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
        if (tries == FIRST_TRY) {
            setFirstRoll(pins);
        } else {
            setSecondRoll(pins);
        }
        tries++;
    }

    @Override
    public int score() {
        var frameScore =  getFirstRoll() + getSecondRoll();
        if (!hasNext()) {
            return frameScore;
        } else if (isStrike()) {
            return frameScore + next.getFirstRoll() + next.getSecondRollForBonus();
        } else if (isSpare()) {
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
        if (isStrike()) {
            return next.getFirstRoll();
        } else {
            return getSecondRoll();
        }
    }
}
