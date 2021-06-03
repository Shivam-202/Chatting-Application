package chatting.application;
import static chatting.application.Server.vertical;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener{
    JButton btn1;
    static JTextField txt1;
    static JPanel tr;
   Boolean typing;
   static Socket skt;
   static DataInputStream din;
   static DataOutputStream dout;
          
   static JFrame f1 = new JFrame();
    Client(){
         
        f1.setBounds(900,120,400,600);
        f1.setLayout(null);
        f1.getContentPane().setBackground(Color.WHITE);
        f1.setResizable(false);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setUndecorated(true);
      
        JPanel p1 = new JPanel();
        p1.setBounds(0,0,400,70);
        p1.setBackground(new Color(7,114,84));
        p1.setLayout(null);
        f1.add(p1);
        
        f1.setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/chatImg/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lab1 = new JLabel(i3);
        lab1.setBounds(5,17,30,30);
        lab1.addMouseListener(new MouseAdapter(){
              @Override
              public void mouseClicked(MouseEvent me){
               System.exit(0);
         }
      });
         Cursor c = new Cursor(Cursor.HAND_CURSOR);
         
         lab1.setCursor(c);
        p1.add(lab1);
        
       
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/chatImg/img4.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel lab2 = new JLabel(i6);
        lab2.setBounds(40,5,60,60);
         lab2.setLayout(null);
          p1.add(lab2); 
          
         JLabel lab3 = new JLabel("Akshay");
         lab3.setBounds(105,13,200,30);
         lab3.setFont(new Font("TAHOMBO",Font.BOLD,20));
         lab3.setForeground(Color.WHITE);
         p1.add(lab3);
         
         JLabel lab7 = new JLabel("Active Now");
         lab7.setBounds(105,32,200,35);
         lab7.setFont(new Font("TAHOMBO",Font.PLAIN,12));
         lab7.setForeground(Color.WHITE);
         p1.add(lab7);
  
         Timer t = new Timer(1 ,new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e){   
                 if(!typing){
                 lab7.setText("Active Now");
                 }
             }
         });
         
         t.setInitialDelay(1500);
         
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/chatImg/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel lab4 = new JLabel(i9);
        lab4.setBounds(240,20,30,30);
        lab4.setLayout(null);
        p1.add(lab4); 
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/chatImg/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35,30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel lab5 = new JLabel(i12);
        lab5.setBounds(290,20,35,30);
        lab5.setLayout(null);
        p1.add(lab5);
         
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/chatImg/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel lab6 = new JLabel(i15);
        lab6.setBounds(340,20,30,30);
        lab6.setLayout(null);
        p1.add(lab6);
        
        txt1 = new JTextField();
        txt1.setBounds(0,565,300,35);
        txt1.setFont(new Font("TAHOMBO",Font.PLAIN,20)); 
        f1.add(txt1);
       
         txt1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ep){
                lab7.setText("Typing....");
                
                t.stop();
                
                typing = true;
                   if(typing == true){
                     btn1.setEnabled(true);
                   }           

                }
         
         
            public void keyReleased(KeyEvent er){
                typing = false;
                
                if(!t.isRunning()){
                    t.start();
                }
            }
        });
         
        btn1 = new JButton("Send");   
        btn1.setBackground(new Color(7,114,84));
        btn1.setBounds(300,565,100,35);
        btn1.setFont(new Font("TAHOMBO",Font.PLAIN,20)); 
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(this);
        btn1.setCursor(c);
        btn1.setEnabled(false);
        f1.add(btn1);
        
        tr = new JPanel();
        tr.setFont(new Font("TAHOMBO",Font.PLAIN,17));
        tr.setForeground(Color.BLACK);
        JScrollPane js = new JScrollPane(tr);
        js.setBounds(0,70,400,495);     
        f1.add(js);
        
        f1.setVisible(true);
    }
  
      public static JPanel formatLabel(String app){
        JPanel p3 = new JPanel();
      //  txt1.setText("");
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        JLabel l = new JLabel("<html><p style = \" width : 150px\">"+app+"</p></html>");
        l.setFont(new Font("TAHOMBO",Font.PLAIN,17)); 
        l.setBackground(new Color(37,211,102));
        l.setOpaque(true);
        l.setBorder(new EmptyBorder(10,10,10,50));
         
        Calendar cai = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel la = new JLabel();
        la.setText(sdf.format(cai.getTime()));
      
        p3.add(l);
        p3.add(la);
        
        return(p3);
    }
   
   @Override
    public void actionPerformed(ActionEvent e){
        
        
          try{
              btn1.setEnabled(false);
            String app = txt1.getText();
            JPanel p2 = formatLabel(app);
            txt1.setText("");
            tr.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            
            right.add(p2, BorderLayout.LINE_END);
             vertical.add(right);
             vertical.add(Box.createVerticalStrut(10));
            tr.add(vertical, BorderLayout.PAGE_START);
             
             dout.writeUTF(app);
           
          }
          catch(IOException ex)
          {
              System.out.println(ex);
          }
         
                     
    }

    public static void main(String[] args){
        
        new Client().f1.setVisible(true);
      
        try{
           skt = new Socket("127.0.0.1",1234);
            din = new DataInputStream(skt.getInputStream());
            dout = new DataOutputStream(skt.getOutputStream());
              String msg = "";
           while(true){
                 tr.setLayout(new BorderLayout());
            msg = din.readUTF();
           JPanel p2 = formatLabel(msg);
            JPanel left = new JPanel(new BorderLayout());
            
            left.add(p2, BorderLayout.LINE_START);
             vertical.add(left);
            
            tr.add(vertical, BorderLayout.PAGE_START);
            f1.validate();
          }
        }
        catch(Exception ev){
            System.out.println(ev);
        }
    }  
}

