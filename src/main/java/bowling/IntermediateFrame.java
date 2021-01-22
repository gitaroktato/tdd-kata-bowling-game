package bowling;

public class IntermediateFrame extends BaseFrame {


    @Override
    public void roll(int pins)  {
    }

    @Override
    public int score() {
        return  0;
    }

    @Override
    public void setNext(Frame next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean noMoreRolls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSecondRollForBonus() {
        throw new UnsupportedOperationException();
    }
}
