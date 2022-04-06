package connectfour;


import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.*;


public class game extends JFrame implements ActionListener{
    //Global Variables
    public int turn = randomTurn();
    public int player1 = 1;//player turn represented by 1;
    public int player2 =2;//player2 (AI) turn represented by 2;
    public char piece ='x';
    public boolean gameEnd = false;
    public boolean stop = false; 
    List<Component> mainComponents =new ArrayList<Component>();// list of components that added to frame 
    
    public void print(char[][] board, int depth, boolean stop){
        char c;
        for(int s =0; s<board.length;s++){
            for (int i = 1; i < depth*3; i++) System.out.print("\t");
            for(int k = 0; k< board[0].length;k++){
                c = (board[s][k] == '\0') ? '_' : board[s][k];
                System.out.print(c + " ");
            }
            if(stop && s==board.length/2) System.out.print(" "+ eval(board) );
            System.out.println();
        }
        System.out.println("");
    }
    
    public int randomTurn(){//return integer which is 1 or 2 randomly to choose the player who starts
        Random random = new Random();
        return random.nextInt(2) + 1;  //random.nextInt(max - min + 1) + min : generate integerr betweenn 1 and 2
    }
    
    public boolean isTerminal(char[][] node){
        if(ConnectFour.fullyBoard){//if playing on fully board mode then check that the whole board is full
            for(char[] row : node ){
                for(char element : row){
                    if(element=='\0') return false;
                }
            }
            return true;     
        }else{ //if playing on first connect four mode then check that either player has one connect four
            if(countConnectFour(node,'R')==1 || countConnectFour(node,'B')==1)
                return true;
            else
                return false;
        }
    }
    
    public int countConnectFour(char[][] board, char piece){
        int count =0;
        ////horizontal
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]==piece &&board[i][j+2]==piece &&board[i][j+3]==piece ) count++;
        
        ////vertical
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]==piece &&board[i+2][j]==piece &&board[i+3][j]==piece ) count++;
        
        ////poitive diagonal
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]==piece &&board[i-2][j+2]==piece &&board[i-3][j+3]==piece ) count++;
        
        ////negative diagonal
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]==piece &&board[i+2][j+2]==piece &&board[i+3][j+3]==piece ) count++;

        return count;
    } 
    
    public int countConnectThree(char[][] board, char piece){
        int count = 0;
        //horizontal
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i][j+1]==piece &&board[i][j+2]==piece&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]=='\0' &&board[i][j+2]==piece&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]==piece &&board[i][j+2]=='\0'&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]==piece &&board[i][j+2]==piece&&board[i][j+3]=='\0' ) count++;
        
        //vertical
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]=='\0' && board[i+1][j]==piece &&board[i+2][j]==piece &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]=='\0' &&board[i+2][j]==piece &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]==piece &&board[i+2][j]=='\0' &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]==piece &&board[i+2][j]==piece &&board[i+3][j]=='\0' ) count++;
        
        //poitive diagonal
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i-1][j+1]==piece &&board[i-2][j+2]==piece &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]=='\0' &&board[i-2][j+2]==piece &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]==piece &&board[i-2][j+2]=='\0' &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]==piece &&board[i-2][j+2]==piece &&board[i-3][j+3]=='\0' ) count++;
        
        //negative diagonal
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i+1][j+1]==piece &&board[i+2][j+2]==piece &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]=='\0' &&board[i+2][j+2]==piece &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]==piece &&board[i+2][j+2]=='\0' &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]==piece &&board[i+2][j+2]==piece &&board[i+3][j+3]=='\0' ) count++;

        
        
        return count;
    }
    
    public int countConnectTwo(char[][] board, char piece){
        int count =0;
        //horizontal
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i][j+1]=='\0' &&board[i][j+2]==piece&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i][j+1]==piece &&board[i][j+2]=='\0'&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i][j+1]==piece &&board[i][j+2]==piece&&board[i][j+3]=='\0' ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]=='\0' &&board[i][j+2]==piece&&board[i][j+3]=='\0' ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]=='\0' &&board[i][j+2]=='\0'&&board[i][j+3]==piece ) count++;
        
        for(int i=0;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i][j+1]==piece &&board[i][j+2]=='\0'&&board[i][j+3]=='\0' ) count++;
        
        //vertical
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]=='\0' && board[i+1][j]=='\0' &&board[i+2][j]==piece &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]=='\0' && board[i+1][j]==piece &&board[i+2][j]=='\0' &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]=='\0' && board[i+1][j]==piece &&board[i+2][j]==piece &&board[i+3][j]=='\0' ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]=='\0' &&board[i+2][j]==piece &&board[i+3][j]=='\0' ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]=='\0' &&board[i+2][j]=='\0' &&board[i+3][j]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width;j++)
                    if(board[i][j]==piece && board[i+1][j]==piece &&board[i+2][j]=='\0' &&board[i+3][j]=='\0' ) count++;
        
        //poitive diagonal
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i-1][j+1]=='\0' &&board[i-2][j+2]==piece &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i-1][j+1]==piece &&board[i-2][j+2]=='\0' &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i-1][j+1]==piece &&board[i-2][j+2]==piece &&board[i-3][j+3]=='\0' ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]=='\0' &&board[i-2][j+2]==piece &&board[i-3][j+3]=='\0' ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]=='\0' &&board[i-2][j+2]=='\0' &&board[i-3][j+3]==piece ) count++;
        for(int i=3;i<ConnectFour.length;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i-1][j+1]==piece &&board[i-2][j+2]=='\0' &&board[i-3][j+3]=='\0' ) count++;
        
        //negative diagonal
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i+1][j+1]=='\0' &&board[i+2][j+2]==piece &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i+1][j+1]==piece &&board[i+2][j+2]=='\0' &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]=='\0' && board[i+1][j+1]==piece &&board[i+2][j+2]==piece &&board[i+3][j+3]=='\0' ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]=='\0' &&board[i+2][j+2]==piece &&board[i+3][j+3]=='\0' ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]=='\0' &&board[i+2][j+2]=='\0' &&board[i+3][j+3]==piece ) count++;
        for(int i=0;i<ConnectFour.length-3;i++)
            for(int j=0;j<ConnectFour.width-3;j++)
                    if(board[i][j]==piece && board[i+1][j+1]==piece &&board[i+2][j+2]=='\0' &&board[i+3][j+3]=='\0' ) count++;
    
        return count;
    }
    
    public int eval(char[][] board){ //evaluate the heuristic score of each state node
        int utility =0;
        
        if(ConnectFour.fullyBoard){//if playing on fully board mode then the heurestic score for the state or the node increases by 10 for each connect four of AI and decreases by 10 for the player
        ////<<AI Fours>>\\\\
        utility += countConnectFour(board, 'B') * 10;
        
        ////<<Player Fours>>\\\\
        utility -= countConnectFour(board, 'R') * 10;
        
        }else{//but in case of first connect four mode the score becomes much bigger because the AI have to win if there is any chance and block the opponent if he is about to win
            
        ////<<AI Fours>>\\\\
        utility += countConnectFour(board, 'B') * 100000;
        
        ////<<Player Fours>>\\\\
        utility -= countConnectFour(board, 'R') * 100000;
        }
        
        //**************************************************************************************************************
        
        ////<<AI Threes>>\\\\
        utility += countConnectThree(board, 'B') * 5;
        
        ////<<Player Threes>>\\\\
        utility -= countConnectThree(board, 'R') * 5;
        
        //**************************************************************************************************
        ////<<AI Twos>>\\\\
        utility += countConnectTwo(board, 'B') * 2;
        
        ////<<Player Twos>>\\\\
        utility -= countConnectTwo(board, 'R') * 2;
        
        return utility;
    }
    
    public void copyArr(char[][] node1, char[][] node2){ //copy values of array node1 into array nod2
        for (int i = 0; i < node1.length; i++)
            for (int j = 0; j < node1[0].length; j++)
                node2[i][j] = node1[i][j];
    }
    
    public pair minimax(char[][] node, int depth, boolean maximizingPlayer ,boolean pruning, int alpha, int beta){
        boolean changed = false;
        print(node,depth,depth == ConnectFour.k);
        if( depth == ConnectFour.k || isTerminal(node) ) return new pair(null, eval(node));
        
        char[][] child = new char[node.length][node[0].length];

        pair pair = null;
        if (maximizingPlayer){//Maximize
            char[][] maxChild = new char[node.length][node[0].length];;
            int maxUtility = -999999999;

            for(int i=0; i<ConnectFour.width;i++){
                copyArr(node,child);
                for(int j = ConnectFour.length-1 ; j>=0; j--){
                    if(child[j][i]=='\0'){ //generating childs
                        child[j][i] = 'B';
                        changed = true;
                        break;
                    }
                }
                if(changed) {
                    pair = minimax(child,depth+1,false,ConnectFour.pruning, alpha, beta);
                
                if(pair.getUtility()>maxUtility){
                    copyArr(child,maxChild);
                    maxUtility = pair.getUtility();
                }
                if(maxUtility >= beta && ConnectFour.pruning ) break;
                if(maxUtility> alpha && ConnectFour.pruning) alpha = maxUtility;
                changed = false;
                }
            }
            return new pair(maxChild,maxUtility);
            
            
        }else{//Minimize
            char[][] minChild = new char[node.length][node[0].length];;
            int minUtility = 999999999;
            
            for(int i=0; i<ConnectFour.width;i++){
                copyArr(node,child);
                for(int j = ConnectFour.length-1 ; j>=0; j--){
                    if(child[j][i]=='\0'){ //generating childs
                        child[j][i] = 'R';
                        changed = true;
                        break;
                    }
                }
                if(changed) {
                    pair = minimax(child,depth+1,true, ConnectFour.pruning, alpha, beta);
                if(pair.getUtility()<minUtility){
                    copyArr(child,minChild);
                    minUtility = pair.getUtility();
                }
                if(minUtility <= alpha && ConnectFour.pruning ) break;
                if(minUtility < beta && ConnectFour.pruning) beta = minUtility;
                changed = false;
                }
            }
            return new pair(minChild,minUtility);        
        }
    }
    
    public void AIPlayes(char board[][]){
        pair pair = minimax(board,0,true, ConnectFour.pruning,-999999999,999999999);
        int pos =0;
        for(int s =0; s<ConnectFour.board.length;s++){
            for(int k = 0; k< ConnectFour.board[0].length;k++){
                if(ConnectFour.board[s][k]!=pair.getChild()[s][k]){
                    pos = k;
                    break;
                }
            }
        }
        JPanel p = (JPanel)mainComponents.get(pos);
        JButton button = (JButton)p.getComponents()[0];
        button.doClick();
     }
  
    public void prepareFrame(JFrame frame){
        //make panels then add buttons to the pannels then add pannels to the frame
        for(int i = 0;i<ConnectFour.width;i++ ){
            int columnRank = i;
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(ConnectFour.length,1));////prepare each panel with one column and number of rows equals the length of board so we treat each panel as column
            
            for(int j=0; j<ConnectFour.length; j++){
                Button button = new Button();
                button.setText("");
                button.setVisible(true);
                button.setRadius(500);
                if(turn == player1) {
                    button.setColorOver(Color.red);
                } else{
                    button.setColorOver(Color.blue);
                }
                panel.add(button);
            }
            
            frame.add(panel);
            revalidate();//refrsh the frame after adding panel
            mainComponents.add(panel);
            //Prepare buttons clicks fuctions
            List<Component> buttons = Arrays.asList(panel.getComponents());       
            for(Component component : buttons){
                Button button = (Button) component;
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //when click on button drop a piece : color the last button in the column if there and update the board
                        for(int k = buttons.size()-1 ; k>=0; k--){
                            if(turn==player1){
                                if(buttons.get(k).getBackground()==Color.white ){ //search where to put the piece
                                    Button b = (Button) buttons.get(k);
                                    b.setColor(Color.red);b.setBackground(Color.red);b.setColorOver(Color.red);b.setColorClick(Color.red);
                                    turn = player2;//change turn
                                    ConnectFour.board[k][columnRank] = 'R';//update board
                                    piece='R';
                                    break;
                                }  
                            }else{
                                if(buttons.get(k).getBackground()==Color.white ){
                                    Button b = (Button) buttons.get(k);
                                    b.setColor(Color.blue);b.setBackground(Color.blue);b.setColorOver(Color.blue);b.setColorClick(Color.blue);
                                    turn=player1;
                                    ConnectFour.board[k][columnRank] = 'B';
                                    piece ='B';
                                    break; 
                                }
                            }
                        }
                
                if(isTerminal(ConnectFour.board)){ //when the game ends
                            gameEnd = true;
                            JFrame result = new JFrame();
                            result.setUndecorated(true);
                            result.setSize(300,100);
                            result.setVisible(true);
                            result.setResizable(false);
                            result.setLocation(500,100);
                            JLabel score = new JLabel();
                            score.setVisible(true);
                            score.setForeground(Color.black);
                            score.setBounds(50, 50, 100, 30);
                            score.setFont(new Font("Verdana", Font.PLAIN, 20));
                            result.add(score, BorderLayout.CENTER);
                            
                            int player1Connects = countConnectFour(ConnectFour.board,'R');
                            int player2Connects = countConnectFour(ConnectFour.board,'B');
                            
                            System.out.println("Done");
                            System.out.println("Player1: " + player1Connects +"  Player2: "+ player2Connects);
                            
                            if ( player1Connects > player2Connects ){
                                score.setText("<html>"+ ConnectFour.player1name + " Wins<br/> "+ ConnectFour.player1name+":" + player1Connects+" " + ConnectFour.player2name+":"+ player2Connects +"</html>");
                            }else if(player1Connects < player2Connects){
                                score.setText("<html>"+ ConnectFour.player2name + " Wins<br/> "+ ConnectFour.player1name+":" + player1Connects+" " + ConnectFour.player2name+":"+ player2Connects +"</html>");  
                            }else{
                                score.setText("<html>Draw<br/> "+ ConnectFour.player1name+": " + player1Connects +" "+ ConnectFour.player2name+": "+ player2Connects +"</html>");
                            }
                            
                        }
                        if(turn==player2 && !gameEnd){
                           if(ConnectFour.vsAI) AIPlayes(ConnectFour.board);
                           for(Component c :mainComponents){
                               Component[] s = ((JPanel)c).getComponents();
                               for(Component l : s ){
                                   if( ((Button)l).getBackground()== Color.white)((Button)l).setColorOver(Color.blue);
                               }
                           }
                        }
                        if(turn==player1){
                            for(Component c :mainComponents){
                               Component[] s = ((JPanel)c).getComponents();
                               for(Component l : s ){
                                   if( ((Button)l).getBackground()== Color.white )((Button)l).setColorOver(Color.red);
                               }
                           }
                        }
                    }
                    
                });
                
    }
                revalidate();
                
    }
    }
    
    
    
    
    
    public game(){
        setTitle("Connect Four");
        setSize(ConnectFour.width*100,ConnectFour.length*100);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stop the program if the window(Jframe) is closed 
        setLocation(300,50);
        setLayout(new GridLayout(1,ConnectFour.width));//prepare a frame with one row and number of columns equal the width and we will split these columns later
        prepareFrame(this);
        if(ConnectFour.vsAI && turn== player2){AIPlayes(ConnectFour.board); turn=player1;}//for first turn if it is for player2 to click and dont wait to click buttons
  

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    }
 
}
  
    

