package start;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

public class Carculator extends JFrame implements ActionListener {
    /****************北面控件***************/
    private JPanel jp_north = new JPanel();
    private JTextField input_text = new JFormattedTextField();
    private JButton c_Btn = new JButton("C");

    /****************中间控件***************/
    private JPanel jp_center = new JPanel();

    public Carculator() throws HeadlessException {
        this.init();
        this.addNorthComponent();
        this.addCenterButton();
    }


    //    初始化方法
    public void init() {
        this.setTitle("计算器");
        this.setSize(500, 400);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    //    北面控件
    public void addNorthComponent() {
        this.input_text.setPreferredSize(new Dimension(400, 30));
        jp_north.add(input_text);
        this.c_Btn.setForeground(Color.RED);
        jp_north.add(c_Btn);
        c_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });

        this.add(jp_north, BorderLayout.NORTH);
    }


    //    中间按钮
    public void addCenterButton() {
        String btn_text = "123+456-789*.0=/²()√";
        String regex = "[\\+\\-*/.=²()√]";
        this.jp_center.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < 20; i++) {
            String temp = btn_text.substring(i, i + 1);
            JButton btn = new JButton();
            btn.setText(temp);
            if (temp.matches(regex)) {
                btn.setFont(new Font("粗体", Font.BOLD, 22));
                btn.setForeground(Color.RED);
            } else btn.setFont(new Font("粗体", Font.BOLD, 25));
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        this.add(jp_center, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        Carculator carculator = new Carculator();
        carculator.setVisible(true);
    }


    private String firstInput = null;
    private String operator = null;
    @Override
    public void actionPerformed(ActionEvent e) {
        String clickStr = e.getActionCommand();
        if (".0123456789".indexOf(clickStr) != -1) {
            this.input_text.setText(input_text.getText() + clickStr);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
        } else if (clickStr.matches("[\\+\\-*/²()√]{1}")) {
            operator = clickStr;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        } else if (clickStr.equals("=")) {
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (operator) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0) {
                        result = a / b;
                    }
                    break;
                case "√": {
                    b = 0.00;
                    b = Math.sqrt(a);
                    result = Double.valueOf(String.valueOf(b));
                }
                    break;
                case "²":{
                    b = 0.00;
                    b=Math.pow(a,2);
                    result= Double.valueOf(String.valueOf(b));
                }
                    break;
            }


                this.input_text.setText(result.toString());

            }
        }

    }