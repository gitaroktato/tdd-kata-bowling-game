package bowling;

public class IntermediateFrame extends BaseFrame implements Frame {

    private static final int MAXIMUM_TRIES_IN_A_FRAME = 2;
    private Frame next = new NoFrame();

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPins(pins);
        if (getNumberOfTries() >= MAXIMUM_TRIES_IN_A_FRAME) {
            throw new NoMoreRollsException();
        }

        if (getFirstRoll() + getSecondRoll() + pins > TOTAL_NUMBER_OF_PINS) {
            throw new IllegalRollException();
        }

        if (getNumberOfTries() == 0) {
            setFirstRoll(pins);
        } else if (getNumberOfTries() == 1) {
            setSecondRoll(pins);
        }
    }

    @Override
    public int score() {
        var frameScore = getFirstRoll() + getSecondRoll();
        if (isStrike()) {
            return frameScore + next.getFirstRoll() + next.getSecondRollForBonus();
        } else if (isSpare()) {
            return frameScore + next.getFirstRoll();
        } else {
            return frameScore;
        }
    }

    @Override
    public int getSecondRollForBonus() {
        if (isStrike()) {
            return next.getFirstRoll();
        } else {
            return getSecondRoll();
        }
    }

    @Override
    public void setNext(Frame next) {
        this.next = next;
    }

    @Override
    public boolean hasMoreRolls() {
        return !isStrike() && getNumberOfTries() < MAXIMUM_TRIES_IN_A_FRAME;
    }

}
