import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void testGameCreation() {
        var game = new Game();
        game.roll(0);
        var score = game.score();
        assertEquals(0, game.score());
    }
}
