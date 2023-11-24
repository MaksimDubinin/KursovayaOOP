import value.*;
import action.*;
import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double width = screenSize.getWidth();
    final double height = screenSize.getHeight();
    public Window() {

        super("Девятка");
        setBounds((int) (width / 2) - 400, (int) (height / 2) - 425, 800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        CardLayout cl = new CardLayout();
        JPanel homeContainer = new JPanel(cl);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(182, 179, 179));
        mainPanel.setPreferredSize(new Dimension(800, 800));

        JButton setting = new JButton();
        setting.setBounds(255, 400, 140, 50);
        setting.setIcon(new ImageIcon("data/set.png"));
        mainPanel.add(setting);
        JButton play =  new JButton();
        play.setBounds(405, 400, 140, 50);
        play.setIcon(new ImageIcon("data/start.png"));
        mainPanel.add(play);

        setIconImage(new ImageIcon("data/logo2.png").getImage());

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setPreferredSize(new Dimension(800, 800));

        Component decks = new JLabel("Выберите игральную коолоду:");
        decks.setBounds((int) (width / 2) - 700, (int) (height / 2) - 500, 200, 200);
        settingsPanel.add(decks);
        SpinnerModel deck =  new SpinnerNumberModel(36, 36, 52, 16);
        JSpinner chooseDeck = new JSpinner(deck);
        chooseDeck.setBounds((int) (width / 2) - 500, (int) (height / 2) - 415, 50, 30);
        settingsPanel.add(chooseDeck);
        settingsPanel.setBackground(new Color(182, 179, 179));

        Component player = new JLabel("Выберите количество игроков:");
        player.setBounds((int) (width / 2) - 700, (int) (height / 2) - 450, 200, 200);
        settingsPanel.add(player);
        JTextField text = new JTextField(3);
        text.setBounds((int) (width / 2) - 500, (int) (height / 2) - 365, 70, 30);
        settingsPanel.add(text);

        Component direction = new JLabel("Выберите направление игры: ");
        direction.setBounds((int) (width / 2) - 700, (int) (height / 2) - 400, 200, 200);
        settingsPanel.add(direction);
        JRadioButton dir1 = new JRadioButton("По часовой стрелке");
        JRadioButton dir2 = new JRadioButton("Против часовой стрелке");
        ButtonGroup gr = new ButtonGroup();
        gr.add(dir1);
        gr.add(dir2);
        dir1.setBounds((int) (width / 2) - 505, (int) (height / 2) - 300, 200, 30);
        dir2.setBounds((int) (width / 2) - 505, (int) (height / 2) - 330, 200, 30);
        settingsPanel.add(dir1);
        settingsPanel.add(dir2);

        JButton applysettings = new JButton();
        applysettings.setIcon(new ImageIcon("data/accept.png"));
        applysettings.setBounds(333, 250, 135, 50);
        settingsPanel.add(applysettings);
        JButton backToHomeFromSettings = new JButton();
        backToHomeFromSettings.setIcon(new ImageIcon("data/back.png"));
        backToHomeFromSettings.setBounds(665, 0, 137, 50);
        settingsPanel.add(backToHomeFromSettings);


        JButton endGame = new JButton();
        endGame.setIcon(new ImageIcon("data/end.png"));
        endGame.addActionListener(e -> cl.show(homeContainer, "HOME"));
        endGame.setVisible(false);
        endGame.setBounds(340, 20, 137, 50);
        JButton backToHomeFromGame = new JButton();
        backToHomeFromGame.setIcon(new ImageIcon("data/back.png"));
        backToHomeFromGame.setBounds(0, 0, 137, 50);
        JPanel game = new JPanel();
        game.setLayout(null);
        game.setBackground(new Color(182, 179, 179));
        game.add(backToHomeFromGame);
        game.add(endGame);

        JPanel names = new JPanel();
        names.setLayout(null);
        names.setBackground(new Color(182, 179, 179));

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(null);
        leaderboard.setBackground(new Color(182, 179, 179));

        JButton backToHomeFromLeader = new JButton();
        backToHomeFromLeader.setIcon(new ImageIcon("data/back.png"));
        backToHomeFromLeader.setBounds(665, 0, 137, 50);
        leaderboard.add(backToHomeFromLeader);
        backToHomeFromLeader.addActionListener(e -> cl.show(homeContainer, "HOME"));

        JPanel leaders = new JPanel();
        leaders.setBounds(200, 200, 400, 400);
        leaders.setLayout(null);
        leaders.setBackground(new Color(182, 179, 179));
        leaderboard.add(leaders);

        JPanel rules = new JPanel();
        rules.setLayout(null);
        JButton fromHomeToRule = new JButton();
        fromHomeToRule.setIcon(new ImageIcon("data/rule.png"));
        fromHomeToRule.addActionListener(e -> cl.show(homeContainer, "RULES"));
        fromHomeToRule.setBounds(405, 470, 137, 50);
        mainPanel.add(fromHomeToRule);
        rules.setBackground(new Color(182, 179, 179));

        JButton backToHomeFromRule = new JButton();
        backToHomeFromRule.setIcon(new ImageIcon("data/back.png"));
        backToHomeFromRule.setBounds(665, 0, 137, 50);
        backToHomeFromRule.addActionListener(e -> cl.show(homeContainer, "HOME"));
        rules.add(backToHomeFromRule);

        JButton fromHomeToLeader = new JButton();
        fromHomeToLeader.setIcon(new ImageIcon("data/table.png"));
        fromHomeToLeader.setBounds(255, 470, 137, 50);
        mainPanel.add(fromHomeToLeader);
        fromHomeToLeader.addActionListener(new ActionLeaderboard(homeContainer, cl, leaders));

        homeContainer.add(settingsPanel, "SETTINGS");
        homeContainer.add(mainPanel, "HOME");
        homeContainer.add(game, "GAME");
        homeContainer.add(names, "NAME");
        homeContainer.add(leaderboard, "LEADERBOARD");
        homeContainer.add(rules, "RULES");

        this.add(homeContainer);
        cl.show(homeContainer, "HOME");

        JPanel deckPanel = new JPanel();
        deckPanel.setBackground(new Color(182, 179, 179));
        deckPanel.setBounds(50, 500, 700, 400);
        game.add(deckPanel);

        JLabel gamingTable = new JLabel();
        gamingTable.setLayout(null);
        gamingTable.setBounds(170, 100, 500, 400);
        game.add(gamingTable);
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(182, 179, 179));
        namePanel.setBounds(250, 40, 300, 50);
        game.add(namePanel);

        JButton backToGameFromMain = new JButton();
        backToGameFromMain.setIcon(new ImageIcon("data/backToGame.png"));
        backToGameFromMain.addActionListener(event -> cl.show(homeContainer, "GAME"));
        backToGameFromMain.setBounds(663, 0, 137, 50);
        backToGameFromMain.setVisible(false);
        mainPanel.add(backToGameFromMain);

        JLabel logo = new JLabel();
        logo.setLayout(null);
        logo.setBounds(250, 50, 300, 250);
        logo.setIcon(new ImageIcon("data/logo2.png"));
        mainPanel.add(logo);

        backToHomeFromSettings.addActionListener(e -> cl.show(homeContainer, "HOME"));
        setting.addActionListener(e -> cl.show(homeContainer, "SETTINGS"));
        backToHomeFromGame.addActionListener(e -> cl.show(homeContainer, "HOME"));
        Settingvalue settingvalue = new Settingvalue();
        applysettings.addActionListener(new Actionsettings(settingvalue, text, chooseDeck, dir1, dir2));
        Playingcards playingcards = new Playingcards();
        play.addActionListener(new ActionConfirmName(homeContainer, cl, settingvalue, names, playingcards, deckPanel, gamingTable, backToGameFromMain, namePanel, backToHomeFromGame, endGame));
    }
}