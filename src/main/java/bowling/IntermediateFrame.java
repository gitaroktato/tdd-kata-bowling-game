package bowling;

public class IntermediateFrame extends BaseFrame implements Frame {

    private Frame next;

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

    public void setNext(Frame next) {
        this.next = next;
    }
}
