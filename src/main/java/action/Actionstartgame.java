package action;
import database.MyConnect;
import value.Playingcards;
import value.Settingvalue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actionstartgame implements ActionListener {

    private Playingcards playingcards;
    private Settingvalue settingvalue;
    private CardLayout cl;
    private JPanel homeContainer;
    private JPanel deckPanel;
    private JLabel gamingTable;
    private JButton backToGameFromMain;
    private JPanel namePanel;

    private JTextField nickname1;
    private JTextField nickname2;
    private JTextField nickname3;
    private JTextField nickname4;
    private JTextField nickname5;
    private JTextField nickname6;
    private JButton backToHomeFromGame;
    private JButton endGame;
    public Actionstartgame(Playingcards playingcards, Settingvalue settingvalue, CardLayout cl, JPanel homeContainer, JPanel deckPanel, JLabel gamingTable, JButton backToGameFromMain, JButton backToHomeFromGame,JPanel namePanel, JTextField nickname1, JTextField nickname2, JTextField nickname3, JTextField nickname4, JTextField nickname5, JTextField nickname6, JButton endGame) {
        this.playingcards = playingcards;
        this.settingvalue = settingvalue;
        this.cl = cl;
        this.homeContainer = homeContainer;
        this.deckPanel = deckPanel;
        this.gamingTable = gamingTable;
        this.backToGameFromMain = backToGameFromMain;
        this.namePanel = namePanel;
        this.nickname1 = nickname1;
        this.nickname2 = nickname2;
        this.nickname3 = nickname3;
        this.nickname4 = nickname4;
        this.nickname5 = nickname5;
        this.nickname6 = nickname6;
        this.backToHomeFromGame = backToHomeFromGame;
        this.endGame = endGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> nicknames = new ArrayList<>();
        nicknames.add(nickname1.getText());
        nicknames.add(nickname2.getText());
        nicknames.add(nickname3.getText());
        nicknames.add(nickname4.getText());
        nicknames.add(nickname5.getText());
        nicknames.add(nickname6.getText());

        boolean isAllNameCorrect = true;
        Pattern nicknameRegex = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
        for (int i = 0; i < settingvalue.playerCount; ++i) {
            Matcher m = nicknameRegex.matcher(nicknames.get(i));
            if (!m.matches()) {
                isAllNameCorrect = false;
            }
        }
        if (isAllNameCorrect) {
            MyConnect myConnect = new MyConnect();
            for (int i = 0; i < settingvalue.playerCount; ++i) {
                myConnect.insertInDb(nicknames.get(i));
            }
            backToHomeFromGame.setEnabled(true);
            endGame.setVisible(false);
            backToGameFromMain.setVisible(true);
            cl.show(homeContainer, "GAME");
            gamingTable.removeAll();
            deckPanel.removeAll();
            namePanel.removeAll();
            gamingTable.setIcon(new ImageIcon("data/table2.jpeg"));

            String[][] currentdeck = new String[0][];
            String[][][] playersCard = new String[0][][];
            int[] cardLeft = new int[settingvalue.playerCount];
            for (int i = 0; i < settingvalue.playerCount; ++i) {
                cardLeft[i] = settingvalue.cardCount / settingvalue.playerCount;
            }
            if (settingvalue.cardCount == 36) {
                currentdeck = playingcards.getDeck36();
                int personalCountCard = settingvalue.cardCount / settingvalue.playerCount;
                playersCard = new String[settingvalue.playerCount][personalCountCard][2];
            } else if (settingvalue.cardCount == 52) {
                currentdeck = playingcards.getDeck52();
                int personalCountCard = settingvalue.cardCount / settingvalue.playerCount;
                playersCard = new String[settingvalue.playerCount][personalCountCard][2];
            }

            int x = settingvalue.cardCount;
            int index = 0;
            while (x > 0) {
                for (int i = 0; i < settingvalue.playerCount; ++i) {
                    int randomCard = (int) (Math.random() * x);
                    playersCard[i][index] = currentdeck[randomCard];
                    removeCard(currentdeck, randomCard, currentdeck.length);
                    --x;
                }
                ++index;
            }
            /*System.out.println();
            for (int i = 0; i < settingvalue.playerCount; ++i) {
                System.out.print("Player " + (i + 1) + ": ");
                for (int j = 0; j < settingvalue.cardCount / settingvalue.playerCount; ++j) {
                    System.out.print("{" + playersCard[i][j][0] + ", " + playersCard[i][j][1] + "} ");
                }
                System.out.println();
            }*/
            int countMove = firstMove(playersCard);
            String[][][] cardOnTable = new String[4][13][2];
            for (int i = 0; i < playersCard[countMove].length; ++i) {
                FuncWithCard func = new FuncWithCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1], cardOnTable, countMove % settingvalue.playerCount, cardLeft);
                JButton card = new JButton();
                card.setIcon(new ImageIcon(func.setIconForCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])));
                if (!(playersCard[countMove][i][0].equals("B") && playersCard[countMove][i][1].equals("9"))) {
                    card.setEnabled(false);
                }
                card.addActionListener(new ActionCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1], gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, namePanel, nicknames, backToGameFromMain, backToHomeFromGame, cl, homeContainer, endGame));
                deckPanel.add(card);
            }
            Component text = new JLabel("Игрок " + nicknames.get(countMove) + " делает ход.");
            text.setFont(new Font("Times New Roman", Font.BOLD, 20));
            namePanel.add(text);
        } else {
            JFrame winderror = new JFrame("Ошибка");
            winderror.setVisible(true);
            winderror.setBounds(500, 500, 450, 100);
            JPanel error = new JPanel();
            winderror.add(error);
            Component errortext = new JLabel("Ошибка! Пожалуйста заполните все поля корректным образом.");
            error.add(errortext);
            winderror.setResizable(false);
        }
    }
    public void removeCard(String[][] deck, int line, int countCard) {
        String[] temp = deck[line];
        System.arraycopy(deck, line + 1, deck, line, countCard - line - 1);
        deck[deck.length - 1] = temp;
    }

    public int firstMove(String[][][] playersCard) {
        for (int i = 0; i < settingvalue.playerCount; ++i) {
            for (int j = 0; j < settingvalue.cardCount / settingvalue.playerCount; ++j) {
                if (playersCard[i][j][0].equals("B") && playersCard[i][j][1].equals("9")) {
                    return i;
                }
            }
        }
        return 0;
    }
}