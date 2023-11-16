import Value.*;
import Action.*;
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

        JButton applysettings = new JButton("Принять настройки");
        applysettings.setBounds((int) (width / 2) - 415, (int) (height / 2) - 200, 200, 50);
        settingsPanel.add(applysettings);
        JButton backToHomeFromSettings = new JButton("Вернуться в главное меню");
        backToHomeFromSettings.setBounds((int) (width / 2) - 120, (int) (height / 2) - 450, 200, 50);
        settingsPanel.add(backToHomeFromSettings);

        JButton backToHomeFromGame = new JButton("Вернуться в главное меню");
        backToHomeFromGame.setBounds((int) (width / 2) - 120, (int) (height / 2) - 450, 200, 50);
        JPanel game = new JPanel();
        game.setLayout(null);
        game.setBackground(new Color(182, 179, 179));
        game.add(backToHomeFromGame);

        homeContainer.add(settingsPanel, "SETTINGS");
        homeContainer.add(mainPanel, "HOME");
        homeContainer.add(game, "GAME");

        this.add(homeContainer);
        cl.show(homeContainer, "HOME");

        JPanel deckPanel = new JPanel();
        deckPanel.setBackground(new Color(182, 179, 179));
        deckPanel.setBounds(70, 550, 700, 400);
        game.add(deckPanel);

        JLabel gamingTable = new JLabel();
        gamingTable.setLayout(null);
        gamingTable.setBounds((int) (width / 2) - 550, (int) (height / 2) - 300, 500, 400);
        game.add(gamingTable);
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(182, 179, 179));
        namePanel.setBounds(300, 100, 200, 50);
        game.add(namePanel);

        JButton backToGameFromMain = new JButton("Вернуться в игру");
        backToGameFromMain.addActionListener(event -> cl.show(homeContainer, "GAME"));
        backToGameFromMain.setBounds((int) (width / 2) - 120, (int) (height / 2) - 450, 200, 50);
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
        play.addActionListener(new Actionstartgame(playingcards, settingvalue, cl, homeContainer, deckPanel, gamingTable, backToGameFromMain, namePanel));
    }
}