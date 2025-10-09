// add package name here if needed
//
// import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

class App {

  public static void main(String[] args) {

    JFrame frame = new JFrame();
    GridLayout layout = new GridLayout(3, 2);
    JPanel panel = new JPanel(layout);

    panel.setSize(300, 300);
    panel.setBackground(java.awt.Color.BLUE);

    JButton button = new JButton("Click Me");
    panel.add(button);

    JButton button2 = new JButton("No, Click Me!");
    panel.add(button2);

    JButton button3 = new JButton("Don't Click Me!");
    panel.add(button3);

    JButton button4 = new JButton("Please Click Me!");
    panel.add(button4);

    JButton button5 = new JButton("Click Me Now!");
    panel.add(button5);


    frame.add(panel);
    frame.setSize(400, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    
  }
}

