package connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class MainMenu extends JFrame implements ActionListener {
 
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name1;
    private JLabel name2;
    private JTextField tname1;
    private JTextField tname2;
    private JLabel widthlenght;
    private JTextField twidth;
    private JTextField tlength;
    private JLabel mode;
    private JRadioButton single;
    private JRadioButton multi;
    private ButtonGroup g;
    private JLabel k;
    private JComboBox kk;
    private JLabel pruning;
    private JCheckBox cpruning;
    private JLabel gameMode;
    private JRadioButton fullyBoard;
    private JRadioButton firstToConnect;
    private ButtonGroup gg;
    private JButton start;

 
    private String K[]
        = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10"};
    
    // constructor to initialize the components
    // with default values.
    public MainMenu()
    {
        setTitle("Connect Four");
        setBounds(300, 90, 550, 600);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Main Menu");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(180, 30);
        c.add(title);
 
        name1 = new JLabel("Player1 Name");
        name1.setFont(new Font("Arial", Font.PLAIN, 20));
        name1.setSize(150, 20);
        name1.setLocation(50, 100);
        c.add(name1);
 
        name2 = new JLabel("Player 2 Name");
        name2.setFont(new Font("Arial", Font.PLAIN, 20));
        name2.setSize(150, 20);
        name2.setLocation(50, 130);
        c.add(name2);
        
        tname1 = new JTextField();
        tname1.setFont(new Font("Arial", Font.PLAIN, 15));
        tname1.setSize(150, 20);
        tname1.setLocation(250, 100);
        c.add(tname1);
        
        tname2 = new JTextField();
        tname2.setFont(new Font("Arial", Font.PLAIN, 15));
        tname2.setSize(150, 20);
        tname2.setLocation(250, 130);
        c.add(tname2);
 
        widthlenght = new JLabel("Width x Length                         x ");
        widthlenght.setFont(new Font("Arial", Font.PLAIN, 20));
        widthlenght.setSize(300, 20);
        widthlenght.setLocation(40, 160);
        c.add(widthlenght);

        twidth = new JTextField();
        twidth.setFont(new Font("Arial", Font.PLAIN, 15));
        twidth.setSize(20, 20);
        twidth.setLocation(280, 160);
        c.add(twidth);
 
        tlength = new JTextField();
        tlength.setFont(new Font("Arial", Font.PLAIN, 15));
        tlength.setSize(20, 20);
        tlength.setLocation(350, 160);
        c.add(tlength);
        
        mode = new JLabel("Select Mode");
        mode.setFont(new Font("Arial", Font.PLAIN, 20));
        mode.setSize(150, 20);
        mode.setLocation(50, 190);
        c.add(mode);
 
        single = new JRadioButton("Single Player");
        single.setFont(new Font("Arial", Font.PLAIN, 15));
        single.setSelected(true);
        single.setSize(150, 20);
        single.setLocation(200, 190);
        c.add(single);
 
        multi = new JRadioButton("Multiplayer");
        multi.setFont(new Font("Arial", Font.PLAIN, 15));
        multi.setSelected(false);
        multi.setSize(150, 20);
        multi.setLocation(350, 190);
        c.add(multi);
        g = new ButtonGroup();
        g.add(single);
        g.add(multi);
        
        k = new JLabel("K -Level Of Difficulty-");
        k.setFont(new Font("Arial", Font.PLAIN, 20));
        k.setSize(200, 20);
        k.setLocation(30, 220);
        c.add(k);
        
        kk = new JComboBox(K);
        kk.setFont(new Font("Arial", Font.PLAIN, 15));
        kk.setSize(50, 20);
        kk.setLocation(280, 220);
        c.add(kk);
        
        pruning = new JLabel("Pruning");
        pruning.setFont(new Font("Arial", Font.PLAIN, 20));
        pruning.setSize(200, 20);
        pruning.setLocation(70, 250);
        c.add(pruning);
        
        cpruning = new JCheckBox("");
        cpruning.setFont(new Font("Arial", Font.PLAIN, 15));
        cpruning.setSize(20, 20);
        cpruning.setLocation(290, 250);
        c.add(cpruning);
        
        
        gameMode = new JLabel("Game Mode");
        gameMode.setFont(new Font("Arial", Font.PLAIN, 20));
        gameMode.setSize(150, 20);
        gameMode.setLocation(60, 280);
        c.add(gameMode);
 
        fullyBoard = new JRadioButton("Full Board");
        fullyBoard.setFont(new Font("Arial", Font.PLAIN, 15));
        fullyBoard.setSelected(true);
        fullyBoard.setSize(150, 20);
        fullyBoard.setLocation(200, 280);
        c.add(fullyBoard);
 
        firstToConnect = new JRadioButton("First Connect");
        firstToConnect.setFont(new Font("Arial", Font.PLAIN, 15));
        firstToConnect.setSelected(false);
        firstToConnect.setSize(150, 20);
        firstToConnect.setLocation(350, 280);
        c.add(firstToConnect);
        gg = new ButtonGroup();
        gg.add(fullyBoard);
        gg.add(firstToConnect);
        
 
        start = new JButton("Start");
        start.setFont(new Font("Arial", Font.BOLD, 40));
        start.setSize(200, 80);
        start.setLocation(160, 400);
        start.addActionListener(this);
        c.add(start);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == start) {
            if(!tname1.getText().equals(""))ConnectFour.player1name = tname1.getText();
            if(!tname2.getText().equals(""))ConnectFour.player2name = tname2.getText();
            if(!twidth.getText().equals(""))ConnectFour.width = Integer.parseInt(twidth.getText());
            if(!tlength.getText().equals(""))ConnectFour.length = Integer.parseInt(tlength.getText());
            
            if(single.isSelected()) {ConnectFour.vsAI = true; ConnectFour.player2name = "AI"; }
            else ConnectFour.vsAI = false;
            
            ConnectFour.k = Integer.parseInt( (String)kk.getSelectedItem() );
            
            if(cpruning.isSelected()) ConnectFour.pruning =true;
            else ConnectFour.pruning =false;
            
            if(fullyBoard.isSelected()) ConnectFour.fullyBoard =true;
            else ConnectFour.fullyBoard =false;
            
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            ConnectFour.board = new char[ConnectFour.length][ConnectFour.width];
            JFrame GUI = new game();
            }
        }
}