package Action;
import Value.Settingvalue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public ActionCard (String suit, String dignity, JLabel gamingTable, String[][][] cardOnTable, String[][][] playersCard, Settingvalue settingvalue, JPanel deckPanel, int countMove, int[] cardLeft, JPanel namePanel) {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int cardEnabled = 0;
        FuncWithCard func = new FuncWithCard(suit, dignity, cardOnTable, countMove % settingvalue.playerCount,cardLeft);
        removeCardFromPlayer(playersCard, suit, dignity, countMove % settingvalue.playerCount, cardLeft);
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
        boolean flag = findWinner(cardLeft, countMove % settingvalue.playerCount);
        JButton skipMove = new JButton("Пропустить ход");
        findWinner(cardLeft, countMove % settingvalue.playerCount);
        countMove += 1;
        deckPanel.removeAll();
        namePanel.removeAll();
        gamingTable.add(cards);
        for (int i = 0; i < cardLeft[countMove % settingvalue.playerCount]; ++i) {
            JButton card = new JButton();
            card.addActionListener(new ActionCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1], gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, namePanel));
            card.setEnabled(func.isPossiblePutCard(cardOnTable, playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1]));
            card.setIcon(new ImageIcon(func.setIconForCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])));
            card.setBounds(0, 0, 50, 50);
            if (func.isPossiblePutCard(cardOnTable,playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])) {
                cardEnabled += 1;
            }
            if (flag) {
                card.setEnabled(false);
                skipMove.setEnabled(false);
            }
            deckPanel.add(card);
        }
        if (cardEnabled == 0) {
            skipMove.addActionListener(new ActionSkipMove(gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, suit, dignity, namePanel));
            deckPanel.add(skipMove);
        }
        Component name = new JLabel("Игрок " + (countMove % settingvalue.playerCount + 1) + " делает ход.");
        namePanel.add(name);
        gamingTable.updateUI();
        deckPanel.updateUI();
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
            JFrame winner = new JFrame("Победил игрок " + (player + 1));
            winner.setVisible(true);
            winner.setResizable(false);
            winner.setBounds((int) (width / 2) - 225, (int) (height / 2) - 300, 450, 100);
            return true;
        }
        return false;
    }
}