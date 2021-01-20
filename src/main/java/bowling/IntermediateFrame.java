package bowling;

public class IntermediateFrame extends BaseFrame {

    private int tries = 0;
    private IntermediateFrame next;

    @Override
    public void roll(int pins) throws NoMoreRollsException, IllegalRollException {
        verifyNumberOfPinsIsLessThanMaximum(pins);
        verifyNumberOfPinsIsLessThanMaximum(getFirstRoll() + pins);
        verifyNumberOfTriesIsLessThanMaximum(tries, 2);

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
            return frameScore + next.getFirstRoll() + next.getSecondRoll();
        }
        if (isSpare() && hasNext()) {
            return frameScore + next.getFirstRoll();
        }
        return frameScore;
    }

    private boolean hasNext() {
        return next != null;
    }

    public void setNext(IntermediateFrame next) {
        this.next = next;
    }
}
