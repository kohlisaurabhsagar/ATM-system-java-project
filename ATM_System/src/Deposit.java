package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    JButton deposit, back;
    JTextField amount;
    String pinnumber;

    Deposit(String pinnumber){
        this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to desposit");
        text. setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 20);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 515, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setTitle("ATM");
        setLayout(null);
        setUndecorated(true);
        setVisible(true);getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == deposit){
            String credit = amount.getText();
            Date date = new Date();
            if(credit.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you wanna deposit");
            }else{
                try {
                Conn conn = new Conn();
                String query = "insert into bank values('"+pinnumber+"','"+date+"', 'Deposit', '"+credit+"')";
                conn.stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+" "+credit+" "+ " Deposited Succesfully!!!");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);
                }
            }

        }else if (ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }
    public static void main(String args[]){
        new Deposit("");
    }


    
}
