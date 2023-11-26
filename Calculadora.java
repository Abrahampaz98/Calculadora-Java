package Calculadora;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Calculadora {
    public static void main (String[]agrs){ 
new Marco();
}   
}
class Marco extends JFrame{
public Marco(){
add(new lamina());
Toolkit v=Toolkit.getDefaultToolkit();
Dimension v2= v.getScreenSize();
Image i=v.getImage("calculator.png");
int h=v2.height,w=v2.width;
setBounds(w/4,h/4,w/4,h/2);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("CALCULADORA");
setIconImage(i);
setVisible(true);
setResizable(false);
}
}
class lamina extends JPanel{
private final JButton Screen;
private final Panel panel; 
private boolean inicio=true;
private String LastC;
private BigDecimal r;
public lamina(){
inserccion in = new inserccion();
operacion out= new operacion();
clsAction cls= new clsAction();
Delete delt= new Delete();
LastC="=";
panel= new Panel();
setLayout(new BorderLayout());
Screen= new JButton("0");
add(Screen,BorderLayout.NORTH);
Screen.setEnabled(false);
Screen.setFont(Screen.getFont().deriveFont(50f));
panel.setLayout(new GridLayout(5,4));
addB("C",cls);
addB("√",out);
addB("^",out);
addBotonIc(new ImageIcon("delete.png"),delt);
addB("7",in);
addB("8",in);
addB("9",in);
addB("*",out);
addB("4",in);
addB("5",in);
addB("6",in);
addB("+",out);
addB("3",in);
addB("2",in);
addB("1",in);
addB("-",out);
addB(".",in);
addB("0",in);
addB("/",out);
addB("=",out);
add(panel,BorderLayout.CENTER);
}
    private void addB(String n, ActionListener l){
    JButton b=new JButton(n);
    b.setBackground(Color.WHITE);
    b.setFont(b.getFont().deriveFont(15f));
    b.addActionListener(l);
    panel.add(b);
    }
    private void addBotonIc(Icon s, ActionListener l){
    JButton b=new JButton(s);
    b.setBackground(Color.WHITE);
    b.addActionListener(l);
    panel.add(b);
    }
    private class inserccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(inicio)Screen.setText(""); inicio=false;
            String in= e.getActionCommand();
            Screen.setText(Screen.getText()+in);
        }
    }   
        private class Delete implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent e) {
            String in=e.getActionCommand();
            if(inicio==false){
            if(Screen.getText().length()>0)
            in=Screen.getText().substring(0,Screen.getText().length()-1);
            Screen.setText(in);
            }
            }
        }
    private class operacion implements ActionListener{  
    @Override 
    public void actionPerformed(ActionEvent e){
    String c= e.getActionCommand();
    if(inicio){
    if(c.equals("-")){Screen.setText(c); inicio=false;}
    else LastC=c;
    }else{
    calculos(new BigDecimal(Screen.getText()));
    LastC=c; 
    inicio=true;}}
    }
     void calculos(BigDecimal x){
         switch (LastC) {
             case "+" -> r=r.add(x);
             case "-" -> r=r.subtract(x);
             case "*" -> r=r.multiply(x);
             case "/" -> r=r.divide(x, 2, RoundingMode.HALF_EVEN);
             case "^" -> r=r.pow(x.intValueExact());
             case "√" -> {
                 r= x.sqrt(new MathContext(1));
             }
             case "=" -> r=x;
         }
         if(r.compareTo(BigDecimal.ZERO)==0)r=BigDecimal.ZERO;
         Screen.setText(r.toString());
}
private class clsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
    if(inicio==false){Screen.setText("0"); inicio=true; 
    }if(inicio==true){Screen.setText("0"); 
    }
    }
    }
}