package bowling;

public interface Frame {
    void roll(int pins) throws NoMoreRollsException, IllegalRollException;
    int score();
    boolean hasMoreRolls();
    int getFirstRoll();
    int getSecondRoll();
}
