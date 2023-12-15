package action;
import database.MyConnect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuncWithCard_Test {

    @Test
    @DisplayName("Test method setIconForCard for correct input")
    public void setIconForCard1() {
        int[] cardLeft = new int[0];
        String[][][] cardOnTable = new String[0][][];
        FuncWithCard funcWithCard = new FuncWithCard("K", "9", cardOnTable, cardLeft);
        String path = funcWithCard.setIconForCard("K", "9");
        Assertions.assertEquals("src/main/resources/data/4k.jpg", path);
    }

    @Test
    @DisplayName("Test method setIconForCard for incorrect input")
    public void setIconForCard2() {
        int[] cardLeft = new int[0];
        String[][][] cardOnTable = new String[4][13][2];
        FuncWithCard funcWithCard = new FuncWithCard("K", "9", cardOnTable, cardLeft);
        String path = funcWithCard.setIconForCard("K", "23");
        Assertions.assertEquals("", path);
    }

    @Test
    @DisplayName("Test method isPosiblePutCar 1")
    public void isPosiblePutCar1() {
        int[] cardLeft = new int[0];
        String[][][] cardOnTable = new String[4][13][2];
        cardOnTable[0][7][0] = "K";
        cardOnTable[0][7][1] = "9";
        FuncWithCard funcWithCard = new FuncWithCard("K", "9", cardOnTable, cardLeft);
        boolean posibility = funcWithCard.isPossiblePutCard(cardOnTable, "K", "10");
        Assertions.assertEquals(true, posibility);
    }

    @Test
    @DisplayName("Test method isPosiblePutCar 2")
    public void isPosiblePutCar2() {
        int[] cardLeft = new int[0];
        String[][][] cardOnTable = new String[4][13][2];
        cardOnTable[0][7][0] = "K";
        cardOnTable[0][7][1] = "9";
        FuncWithCard funcWithCard = new FuncWithCard("K", "9", cardOnTable, cardLeft);
        boolean posibility = funcWithCard.isPossiblePutCard(cardOnTable, "C", "14");
        Assertions.assertEquals(false, posibility);
    }
}