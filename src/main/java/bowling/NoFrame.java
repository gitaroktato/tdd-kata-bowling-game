package bowling;

public class NoFrame implements Frame {

    @Override
    public void roll(int pins) throws IllegalRollException {
        throw new IllegalRollException();
    }

    @Override
    public int score() {
        return 0;
    }

    @Override
    public boolean hasMoreRolls() {
        return false;
    }

    @Override
    public int getFirstRoll() {
        return 0;
    }

    @Override
    public int getSecondRoll() {
        return 0;
    }

    @Override
    public void setNext(Frame frame) {
        throw new UnsupportedOperationException("NoFrame should not have next");
    }

    @Override
    public int getSecondRollForBonus() {
        return 0;
    }
}
