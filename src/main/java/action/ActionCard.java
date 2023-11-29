package action;
import database.MyConnect;
import value.Settingvalue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ActionCard implements ActionListener {

    private String suit;
    private String dignity;
    private JLabel gamingTable;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double width = screenSize.getWidth();
    final double height = screenSize.getHeight();
    private String[][][] cardOnTable;
    private String[][][] playersCard;
    private Settingvalue settingvalue;
    private JPanel deckPanel;
    private int countMove;
    private int[] cardLeft;
    private JPanel namePanel;
    private ArrayList<String> nicknames;

    private JButton backToHomeFromGame;
    private JButton backToGameFromMain;
    private JPanel homeContainer;
    private CardLayout cl;
    private JButton endGame;
    public ActionCard (String suit, String dignity, JLabel gamingTable, String[][][] cardOnTable, String[][][] playersCard, Settingvalue settingvalue, JPanel deckPanel, int countMove, int[] cardLeft, JPanel namePanel, ArrayList<String> nicknames, JButton backToGameFromMain, JButton backToHomeFromGame, CardLayout cl, JPanel homeContainer, JButton endGame) {
        this.suit = suit;
        this.dignity = dignity;
        this.gamingTable = gamingTable;
        this.cardOnTable = cardOnTable;
        this.playersCard = playersCard;
        this.settingvalue = settingvalue;
        this.deckPanel = deckPanel;
        this.countMove = countMove;
        this.cardLeft = cardLeft;
        this.namePanel = namePanel;
        this.nicknames = nicknames;
        this.backToGameFromMain = backToGameFromMain;
        this.backToHomeFromGame = backToHomeFromGame;
        this.cl = cl;
        this.homeContainer = homeContainer;
        this.endGame = endGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int cardEnabled = 0;
        int move;
        move = countMove % settingvalue.playerCount;
        removeCardFromPlayer(playersCard, suit, dignity, move, cardLeft);
        FuncWithCard func = new FuncWithCard(suit, dignity, cardOnTable, cardLeft);

        JLabel cards = new JLabel();
        cards.setIcon(new ImageIcon(func.setIconForCard(suit, dignity)));
        int dig = Integer.parseInt(dignity);
        if (suit.equals("K")) {
            cards.setBounds(225 - (9 - dig) * 30, 100, 300, 300);
            cardOnTable[0][dig - 2][0] = suit;
            cardOnTable[0][dig - 2][1] = dignity;
        } else if (suit.equals("C")) {
            cards.setBounds(225 - (9 - dig) * 30, 0, 300, 300);
            cardOnTable[1][dig - 2][0] = suit;
            cardOnTable[1][dig - 2][1] = dignity;
        } else if (suit.equals("P")) {
            cards.setBounds(225 - (9 - dig) * 30, -100, 300, 300);
            cardOnTable[2][dig - 2][0] = suit;
            cardOnTable[2][dig - 2][1] = dignity;
        } else if (suit.equals("B")) {
            cards.setBounds(225 - (9 - dig) * 30, 200, 300, 300);
            cardOnTable[3][dig - 2][0] = suit;
            cardOnTable[3][dig - 2][1] = dignity;
        }
        boolean flag = findWinner(cardLeft, move);
        JButton skipMove = new JButton();
        countMove += 1;
        int playersMove;
        playersMove = countMove % settingvalue.playerCount;
        deckPanel.removeAll();
        namePanel.removeAll();
        namePanel.updateUI();
        gamingTable.add(cards);
        for (int i = 0; i < cardLeft[playersMove]; ++i) {
            JButton card = new JButton();
            card.addActionListener(new ActionCard(playersCard[playersMove][i][0], playersCard[playersMove][i][1], gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, namePanel, nicknames, backToGameFromMain, backToHomeFromGame, cl, homeContainer, endGame));
            card.setEnabled(func.isPossiblePutCard(cardOnTable, playersCard[playersMove][i][0], playersCard[playersMove][i][1]));
            card.setIcon(new ImageIcon(func.setIconForCard(playersCard[playersMove][i][0], playersCard[playersMove][i][1])));
            card.setBounds(0, 0, 50, 50);
            if (func.isPossiblePutCard(cardOnTable,playersCard[playersMove][i][0], playersCard[playersMove][i][1])) {
                cardEnabled += 1;
            }
            if (flag) {
                namePanel.removeAll();
                deckPanel.updateUI();
                namePanel.updateUI();
                endGame.setVisible(true);
                backToGameFromMain.setVisible(false);
                backToHomeFromGame.setEnabled(false);
                card.setEnabled(false);
                skipMove.setEnabled(false);
            }
            deckPanel.add(card);
        }
        if (cardEnabled == 0) {
            skipMove.addActionListener(new ActionSkipMove(gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, suit, dignity, namePanel, nicknames, backToGameFromMain, backToHomeFromGame, cl , homeContainer, endGame));
            skipMove.setIcon(new ImageIcon("src/main/resources/data/skip.png"));
            deckPanel.add(skipMove);
        }
        if (!flag) {
            Component name = new JLabel("Игрок " + nicknames.get(playersMove) + " делает ход.");
            name.setFont(new Font("Times New Roman", Font.BOLD, 20));
            namePanel.add(name);
            gamingTable.updateUI();
            deckPanel.updateUI();
        }
        /*for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 13; ++j) {
                System.out.print("{" + cardOnTable[i][j][0] + " " + cardOnTable[i][j][1] + "} ");
            }
            System.out.println();
        }*/
    }

    public void removeCardFromPlayer(String[][][] playersCard, String suit, String dignity, int player, int[] cardLeft) {
        String[] temp = playersCard[player][cardLeft[player] - 1];
        for (int i = 0; i < cardLeft[player]; ++i) {
            if (playersCard[player][i][0].equals(suit) && playersCard[player][i][1].equals(dignity)) {
                playersCard[player][i] = temp;
                cardLeft[player] -= 1;
            }
        }
    }
    public boolean findWinner(int[] cardLeft, int player) {
        if (cardLeft[player] == 0) {
            MyConnect myConnect = new MyConnect();
            myConnect.setCountWin(nicknames.get(player));
            JFrame winner = new JFrame("Победил игрок " + nicknames.get(player));
            winner.setVisible(true);
            winner.setResizable(false);
            winner.setBounds((int) (width / 2) - 225, (int) (height / 2) - 300, 450, 100);
            return true;
        }
        return false;
    }
}