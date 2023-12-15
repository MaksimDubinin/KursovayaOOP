package action;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Actionstartgame_Test {
    public int firstMove(String[][][] playersCard) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 1; ++j) {
                if (playersCard[i][j][0].equals("B") && playersCard[i][j][1].equals("9")) {
                    return i;
                }
            }
        }
        return 0;
    }
    @Test
    @DisplayName("Test method firstMove")
    public void firstMove() {
        String[][][] playersCard = new String[3][12][2];
        playersCard[1][0][0] = "B";
        playersCard[1][0][1] = "9";

        playersCard[0][0][0] = "C";
        playersCard[0][0][1] = "14";

        playersCard[2][0][0] = "K";
        playersCard[2][0][1] = "7";
        int firstMove = firstMove(playersCard);
        Assertions.assertEquals(1, firstMove);
    }
}
