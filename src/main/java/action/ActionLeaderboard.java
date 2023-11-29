package action;

import database.MyConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ActionLeaderboard implements ActionListener {

    private JPanel homeContainer;
    private CardLayout cl;
    private JPanel leaders;
    public ActionLeaderboard(JPanel homeContainer, CardLayout cl, JPanel leaders) {
        this.homeContainer = homeContainer;
        this.cl = cl;
        this.leaders = leaders;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        leaders.removeAll();
        leaders.updateUI();
        cl.show(homeContainer, "LEADERBOARD");

        MyConnect myConnect = new MyConnect();
        String[][] players;
        players = myConnect.allSelect();

        Component title = new JLabel("Таблица лидеров");
        title.setBounds(130, 0, 300, 50);
        title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        leaders.add(title);

        String[] columnName = {
                "Место",
                "Имя",
                "Количество побед"
        };
        Set<Integer> sortedWins = new HashSet<>();
        for (int i = 0; i < players.length; ++i) {
            sortedWins.add(Integer.parseInt(players[i][1]));
        }

        String[][] sortedPlayer = new String[players.length][3];
        int numberPlayer = players.length - 1;
        for (Integer element : sortedWins) {
            for (int j = 0; j < players.length; ++j) {
                if (element == Integer.parseInt(players[j][1])) {
                    sortedPlayer[numberPlayer][1] = players[j][0];
                    sortedPlayer[numberPlayer][2] = players[j][1];
                    sortedPlayer[numberPlayer][0] = String.valueOf(numberPlayer + 1) + ".";
                    numberPlayer -= 1;
                }
            }
        }

        JTable table = new JTable(sortedPlayer, columnName);
        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(new Dimension(50, 30));
        scroll.setVisible(true);
        table.setFont(new Font("Times New Roman", Font.BOLD, 15));
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setBounds(65,100, 300, 300);
        table.setBackground(new Color(193, 193, 193));
        table.setRowHeight(30);

        leaders.add(table);
        leaders.add(scroll);
    }
}