package action;
import value.Settingvalue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actionsettings implements ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double width = screenSize.getWidth();
    final double height = screenSize.getHeight();

    private Settingvalue settingvalue;
    private JTextField countPlayer;
    private JSpinner deck;
    private JRadioButton dir1;
    private JRadioButton dir2;

    public Actionsettings(Settingvalue settingvalue, JTextField countPlayer, JSpinner deck, JRadioButton dir1, JRadioButton dir2) {
        this.settingvalue = settingvalue;
        this.countPlayer = countPlayer;
        this.deck = deck;
        this.dir1 = dir1;
        this.dir2 = dir2;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        int countCard = (int) deck.getValue();
        String countPlayers = countPlayer.getText();
        if ((dir1.isSelected() || dir2.isSelected()) && (countCard == 36 || countCard == 52) && (countPlayers.equals("3") || countPlayers.equals("4") || countPlayers.equals("6")) && !(countCard == 52 && (countPlayers.equals("3") || countPlayers.equals("6")))) {
            int pc = Integer.parseInt(countPlayers);
            settingvalue.setCardCount(countCard);
            settingvalue.setPlayerCount(pc);
            if (dir1.isSelected()) {
                settingvalue.setDirection("По часовой стрелке");
            } else if (dir2.isSelected()) {
                settingvalue.setDirection("Против часовой стрелки");
            }
        } else {
            JFrame winderror = new JFrame("Ошибка");
            winderror.setVisible(true);
            winderror.setBounds((int) (width / 2) - 225, (int) (height / 2) - 300, 450, 100);
            JPanel error = new JPanel();
            winderror.add(error);
            Component errortext = new JLabel("Ошибка! Пожалуйста заполните все настройки корректным образом.");
            error.add(errortext);
            winderror.setResizable(false);
        }
    }
}