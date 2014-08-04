import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Calculator implements ActionListener {
    
    private int state, i, att[][];
    private boolean input;
    private double result, num_front, num_back;
    private JFrame frame;
    private JButton[] num = new JButton[10];
    private JTextField display = new JTextField("0");
    private ArrayList<JComponent> GUI_Component;
    String[] symbol = {"+", "-", "*", "/", "=", ".", "C"};

    public Calculator() {
        init();
        run();
    }

    private void init() {
        state = 0;
        input = false;
        result = num_front = num_back = 0;
        int fill[] = {
            GridBagConstraints.BOTH,
            GridBagConstraints.VERTICAL,
            GridBagConstraints.HORIZONTAL,
            GridBagConstraints.NONE
        };
        int anchor[] = {
            GridBagConstraints.CENTER,
            GridBagConstraints.EAST,
            GridBagConstraints.SOUTHEAST,
            GridBagConstraints.SOUTH,
            GridBagConstraints.SOUTHWEST,
            GridBagConstraints.WEST,
            GridBagConstraints.NORTHWEST,
            GridBagConstraints.NORTH,
            GridBagConstraints.NORTHEAST
        };
        int a[][] = {
            {0, 0, 3, 1, 1, 1, fill[0], anchor[2]},
            {0, 4, 1, 1, 1, 1, fill[0], anchor[0]},
            {0, 3, 1, 1, 1, 1, fill[0], anchor[0]},
            {1, 3, 1, 1, 1, 1, fill[0], anchor[0]},
            {2, 3, 1, 1, 1, 1, fill[0], anchor[0]},
            {0, 2, 1, 1, 1, 1, fill[0], anchor[0]},
            {1, 2, 1, 1, 1, 1, fill[0], anchor[0]},
            {2, 2, 1, 1, 1, 1, fill[0], anchor[0]},
            {0, 1, 1, 1, 1, 1, fill[0], anchor[0]},
            {1, 1, 1, 1, 1, 1, fill[0], anchor[0]},
            {2, 1, 1, 1, 1, 1, fill[0], anchor[0]},
            {3, 1, 1, 1, 1, 1, fill[0], anchor[0]},
            {3, 2, 1, 1, 1, 1, fill[0], anchor[0]},
            {3, 3, 1, 1, 0, 0, fill[0], anchor[0]},
            {3, 4, 1, 1, 0, 0, fill[0], anchor[0]},
            {2, 4, 1, 1, 0, 0, fill[0], anchor[0]},
            {1, 4, 1, 1, 0, 0, fill[0], anchor[0]},
            {3, 0, 1, 1, 0, 0, fill[0], anchor[0]}
        };
        att = a;

        GUI_Component = new ArrayList<JComponent>(17);
    }

    public void addComponent(int i) {
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = att[i][0];
        c.gridy = att[i][1];
        c.gridwidth = att[i][2];
        c.gridheight = att[i][3];
        c.weightx = att[i][4];
        c.weighty = att[i][5];
        c.fill = att[i][6];
        c.anchor = att[i][7];

        frame.add(GUI_Component.get(i), c);
    }

    public void run() {
        frame = new JFrame("Calcultator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());

        display.setEditable(false);
        GUI_Component.add(display);

        for (i = 0; i < 10; i++) {
            num[i] = new JButton("" + i);
            num[i].addActionListener(this);
            GUI_Component.add(num[i]);
        }

        for (String sym: symbol) {
            JButton sym_button = new JButton("" + sym);
            sym_button.addActionListener(this);
            GUI_Component.add(sym_button);
        }

        for (i = 0; i < GUI_Component.size(); i++) {
            addComponent(i);
        }

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("+")) {
            if (this.input) {
                op();
            }
            this.state = 1;
        } else if (s.equals("-")) {
            if (this.input) {
                op();
            }
            this.state = 2;
        } else if (s.equals("*")) {
            if (this.input) {
                op();
            }
            this.state = 3;
        } else if (s.equals("/")) {
            if (this.input) {
                op();
            }
            this.state = 4;
        } else if (s.equals("=")) {
            if (this.input) {
                op();
            }
            this.state = 0;
        } else if (s.equals("C")) {
            this.num_back = 0;
            this.display.setText("" + 0);
            this.input = true;
        } else {
            if (!input) {
                if (s.equals(".")) {
                    display.setText("0.");
                } else {
                    display.setText("" + s);
                }
                input = true;
            } else {
                display.setText(display.getText() + s);
            }
        }
    }

    private void op() {
        this.num_back= Double.parseDouble(this.display.getText());
        switch (this.state) {
            case 0:
                this.num_front = this.num_back;
                break;
            case 1:
                this.num_front += this.num_back;
                break;
            case 2:
                this.num_front -= this.num_back;
                break;
            case 3:
                this.num_front *= this.num_back;
                break;
            case 4:
                this.num_front /= this.num_back;
                break;
        }
        this.display.setText("" + this.num_front);
        this.input = false;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
