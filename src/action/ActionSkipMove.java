package action;

import value.Settingvalue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private ArrayList<String> nicknames;

    private JButton backToHomeFromGame;
    private JButton backToGameFromMain;
    private JPanel homeContainer;
    private CardLayout cl;
    private JButton endGame;

    public ActionSkipMove (JLabel gamingTable, String[][][] cardOnTable, String[][][] playersCard, Settingvalue settingvalue, JPanel deckPanel, int countMove, int[] cardLeft, String suit, String dignity, JPanel namePanel, ArrayList<String> nicknames, JButton backToGameFromMain, JButton backToHomeFromGame, CardLayout cl, JPanel homeContainer, JButton endGame) {
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
        this.nicknames = nicknames;
        this.backToGameFromMain = backToGameFromMain;
        this.backToHomeFromGame = backToHomeFromGame;
        this.cl = cl;
        this.homeContainer = homeContainer;
        this.endGame = endGame;
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
            card.addActionListener(new ActionCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1], gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, namePanel, nicknames, backToGameFromMain, backToHomeFromGame, cl, homeContainer, endGame));
            card.setEnabled(func.isPossiblePutCard(cardOnTable, playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1]));
            card.setIcon(new ImageIcon(func.setIconForCard(playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])));
            if (func.isPossiblePutCard(cardOnTable,playersCard[countMove % settingvalue.playerCount][i][0], playersCard[countMove % settingvalue.playerCount][i][1])) {
                cardEnabled += 1;
            }
            deckPanel.add(card);
        }
        if (cardEnabled == 0) {
            JButton skipMove = new JButton("Пропустить ход");
            skipMove.addActionListener(new ActionSkipMove(gamingTable, cardOnTable, playersCard, settingvalue, deckPanel, countMove, cardLeft, suit, dignity, namePanel, nicknames, backToGameFromMain, backToHomeFromGame, cl, homeContainer, endGame));
            deckPanel.add(skipMove);
        }
        Component name = new JLabel("Игрок " + nicknames.get(countMove % settingvalue.playerCount) + " делает ход.");
        namePanel.add(name);
        deckPanel.updateUI();
    }
}
