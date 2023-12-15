package action;
import database.MyConnect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class ActionCard_Test {

    public boolean findWinner(int[] cardLeft, int player) {
        if (cardLeft[player] == 0) {
            return true;
        }
        return false;
    }

    @Test
    @DisplayName("Test method findWinner 1")
    public void findWinner() {
        int[] cardLeft = {1, 4, 3, 0};
        int player = 0;
        boolean isWin = findWinner(cardLeft, player);
        Assertions.assertEquals(false, isWin);
    }

    @Test
    @DisplayName("Test method findWinner 2")
    public void findWinner1() {
        int[] cardLeft = {1, 4, 3, 0};
        int player = 3;
        boolean isWin = findWinner(cardLeft, player);
        Assertions.assertEquals(true, isWin);
    }
}
