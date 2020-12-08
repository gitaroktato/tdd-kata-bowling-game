package bowling;

public interface Frame {
    void roll(int pins) throws NoMoreRollsException, IllegalRollException;
    int score();
    boolean isStrike();
    boolean isSpare();
    boolean hasMoreRolls();
    int getFirstRoll();
    int getSecondRoll();
    boolean hasNext();
    int getNextRoll();
}
