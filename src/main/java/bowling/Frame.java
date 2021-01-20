package bowling;

public interface Frame {
    void roll(int pins) throws NoMoreRollsException, IllegalRollException;
    int score();
    void setNext(Frame frame);
    int getFirstRoll();
    int getSecondRoll();
    int getSecondRollForBonus();
    boolean noMoreRolls();
}
