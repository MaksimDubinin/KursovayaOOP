package Action;

import Value.Settingvalue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSkipMove implements ActionListener {

    private JLabel gamingTable;
    private String[][][] cardOnTable;
    private String[][][] playersCard;
    private Settingvalue settingvalue;
    private JPanel deckPanel;
    private int countMove;
    private int[] cardLeft;
    private String suit;
    private String dignity;
    private JPanel namePanel;

    public ActionSkipMove (JLabel gamingTable, String[][][] cardOnTable, String[][][] playersCard, Settingvalue settingvalue, JPanel deckPanel, int countMove, int[] cardLeft, String suit, String dignity, JPanel namePanel) {
        this.gamingTable = gamingTable;
        this.cardOnTable = cardOnTable;
        this.playersCard = playersCard;
        this.settingvalue = settingvalue;
        this.deckPanel = deckPanel;
        this.countMove = countMove;
        this.cardLeft = cardLeft;
        this.suit = suit;
        this.dignity = dignity;
        this.namePanel = namePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FuncWithCard func = new FuncWithCard(suit, dignity, cardOnTable, countMove % settingvalue.playerCount,cardLeft);
        countMove += 1;
        int cardEnabled = 0;
        deckPanel.removeAll();
        namePanel.removeAll();
        for (int i = 0; i < cardLeft[countMove % settingvalue.playerCount]; ++i) {
            JButton card = new JButton();
            card.addActionListener(new ActionCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1], gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, namePanel));
            card.setEnabled(func.isPossiblePutCard(cardOnTable, playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1]));
            card.setIcon(new ImageIcon(func.setIconForCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])));
            if (func.isPossiblePutCard(cardOnTable,playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])) {
                cardEnabled += 1;
            }
            deckPanel.add(card);
        }
        if (cardEnabled == 0) {
            JButton skipMove = new JButton("Пропустить ход");
            skipMove.addActionListener(new ActionSkipMove(gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, suit, dignity, namePanel));
            deckPanel.add(skipMove);
        }
        Component name = new JLabel("Игрок " + (countMove % settingvalue.playerCount + 1) + " делает ход.");
        namePanel.add(name);
        deckPanel.updateUI();
    }
}
