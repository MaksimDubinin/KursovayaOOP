package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class MyConnect_Test {

    @Test
    @DisplayName("Test getCountWin with player in table")
    public void getCountWinTest1() {
        MyConnect myConnect = new MyConnect();
        int countWin = myConnect.getCountWin("Miks");
        Assertions.assertEquals(4, countWin);
    }

    @Test
    @DisplayName("Test getCountWin with player who not in table")
    public void getCountWinTest2() {
        MyConnect myConnect = new MyConnect();
        int countWin = myConnect.getCountWin("Elena");
        Assertions.assertEquals(0, countWin);
    }

    @Test
    @DisplayName("Test setCountWin with player who not in table")
    public void setCountWinTest1() {
        MyConnect myConnect = new MyConnect();
        boolean flag = myConnect.setCountWin("Elena");
        Assertions.assertEquals(false, flag);
    }

    @Test
    @DisplayName("Test setCountWin with player who in table")
    public void setCountWinTest2() {
        MyConnect myConnect = new MyConnect();
        int prevWin = myConnect.getCountWin("Maksim");
        myConnect.setCountWin("Maksim");
        int postWin = myConnect.getCountWin("Maksim");
        Assertions.assertEquals(true, postWin - prevWin == 1);
    }

    @Test
    @DisplayName("Test getCountRow")
    public void getCountRowTest() {
        MyConnect myConnect = new MyConnect();
        int countRow = myConnect.getCountRow();
        Assertions.assertEquals(21, countRow);
    }

    @Test
    @DisplayName("Test insertInDb with player in table")
    public void insertInDbTest1() {
        MyConnect myConnect = new MyConnect();
        int prevSize = myConnect.getCountRow();
        myConnect.insertInDb("Miks");
        int postSize = myConnect.getCountRow();
        Assertions.assertEquals(true, prevSize == postSize);
    }

    @Test
    @DisplayName("Test insertInDb with player who not in table")
    public void insertInDbTest2() {
        MyConnect myConnect = new MyConnect();
        int prevSize = myConnect.getCountRow();
        myConnect.insertInDb("Grigorii");
        int postSize = myConnect.getCountRow();
        Assertions.assertEquals(true, postSize - prevSize == 1);
    }

    @Test
    @DisplayName("Test allSelect")
    public void allSelectTest() {
        MyConnect myConnect = new MyConnect();
        String[][] playersMass = myConnect.allSelect();
        Set<String> names = new HashSet<>();
        for (int i = 0; i < playersMass.length; ++i) {
            names.add(playersMass[i][0]);
        }
        Assertions.assertEquals(playersMass.length, names.size());
    }
}
