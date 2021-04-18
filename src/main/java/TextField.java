

// laver boksen man skriver sit navn i
import javax.swing.*;
public class TextField {
    JFrame f;
    String name;
    TextField(){
        f=new JFrame();
      name =  JOptionPane.showInputDialog(f,"Enter Name");
    }
    public static void main(String[] args) {
        new TextField();
    }


}