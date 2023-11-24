package action;

import database.MyConnect;
import value.Playingcards;
import value.Settingvalue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ActionConfirmName implements ActionListener {

    private Settingvalue settingvalue;
    private JPanel homeContainer;
    private CardLayout cl;
    private JPanel names;
    private Playingcards playingcards;
    private JPanel deckPanel;
    private JLabel gamingTable;
    private JButton backToGameFromMain;
    private JPanel namePanel;
    private JButton backToHomeFromGame;
    private JButton endGame;
    public ActionConfirmName (JPanel homeContainer, CardLayout cl, Settingvalue settingvalue, JPanel names, Playingcards playingcards, JPanel deckPanel, JLabel gamingTable, JButton backToGameFromMain, JPanel namePanel, JButton backToHomeFromGame, JButton endGame) {
        this.homeContainer = homeContainer;
        this.cl = cl;
        this.settingvalue = settingvalue;
        this.names = names;
        this.playingcards = playingcards;
        this.deckPanel = deckPanel;
        this.gamingTable = gamingTable;
        this.backToGameFromMain = backToGameFromMain;
        this.namePanel = namePanel;
        this.backToHomeFromGame = backToHomeFromGame;
        this.endGame = endGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        names.removeAll();

        JButton backToMainFromName = new JButton();
        backToMainFromName.setIcon(new ImageIcon("data/back.png"));
        backToMainFromName.setBounds(0, 0, 140, 50);
        names.add(backToMainFromName);
        backToMainFromName.addActionListener(event -> cl.show(homeContainer, "HOME"));
        JButton confirmName = new JButton();
        confirmName.setIcon(new ImageIcon("data/accept.png"));

        confirmName.setBounds(335, 600, 135, 50);
        names.add(confirmName);
        cl.show(homeContainer, "NAME");
        Component nick1 = new JLabel("Name 1:");
        Component nick2 = new JLabel("Name 2:");
        Component nick3 = new JLabel("Name 3:");
        Component nick4 = new JLabel("Name 4:");
        Component nick5 = new JLabel("Name 5:");
        Component nick6 = new JLabel("Name 6:");

        JTextField nickname1 = new JTextField(20);
        nickname1.setBounds(200, 150, 150, 40);
        JTextField nickname2 = new JTextField(20);
        nickname2.setBounds(200, 210, 150, 40);
        JTextField nickname3 = new JTextField(20);
        nickname3.setBounds(200, 270, 150, 40);
        JTextField nickname4 = new JTextField(20);
        nickname4.setBounds(200, 330, 150, 40);
        JTextField nickname5 = new JTextField(20);
        nickname5.setBounds(200, 390, 150, 40);
        JTextField nickname6 = new JTextField(20);
        nickname6.setBounds(200, 450, 150, 40);

        if (settingvalue.playerCount == 3) {

            nick1.setBounds(130, 150, 60, 40);
            names.add(nick1);
            nick2.setBounds(130, 210, 60, 40);
            names.add(nick2);
            nick3.setBounds(130, 270, 60, 40);
            names.add(nick3);

            names.add(nickname1);
            names.add(nickname2);
            names.add(nickname3);


        } else if (settingvalue.playerCount == 4) {

            nick1.setBounds(130, 150, 60, 40);
            names.add(nick1);
            nick2.setBounds(130, 210, 60, 40);
            names.add(nick2);
            nick3.setBounds(130, 270, 60, 40);
            names.add(nick3);
            nick4.setBounds(130, 330, 60, 40);
            names.add(nick4);

            names.add(nickname1);
            names.add(nickname2);
            names.add(nickname3);
            names.add(nickname4);

        } else if (settingvalue.playerCount == 6) {

            nick1.setBounds(130, 150, 60, 40);
            names.add(nick1);
            nick2.setBounds(130, 210, 60, 40);
            names.add(nick2);
            nick3.setBounds(130, 270, 60, 40);
            names.add(nick3);
            nick4.setBounds(130, 330, 60, 40);
            names.add(nick4);
            nick5.setBounds(130, 390, 60, 40);
            names.add(nick5);
            nick6.setBounds(130, 450, 60, 40);
            names.add(nick6);

            names.add(nickname1);
            names.add(nickname2);
            names.add(nickname3);
            names.add(nickname4);
            names.add(nickname5);
            names.add(nickname6);

        }
        confirmName.addActionListener(new Actionstartgame(playingcards, settingvalue, cl, homeContainer, deckPanel, gamingTable, backToGameFromMain, backToHomeFromGame,namePanel, nickname1, nickname2, nickname3, nickname4, nickname5, nickname6, endGame));
    }
}
