import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
public class BTNodeMenu extends JFrame implements ActionListener,ItemListener, KeyListener{
    private final JLabel lblChoose;
    private final JLabel lblValue;
    private final JTextField txtValue;
    private final JComboBox cboChoose;
    private final JTextArea txtAreaUp;
    private final JTextArea txtAreaDown;
    private final JButton btnProcess;
    private final JButton btnClose;
    private final BinaryTree tree; // Tree object declaration
    String hold="",traversal="";

    BTNodeMenu(){
        tree=new BinaryTree(); //Instantiation of a tree
        lblChoose=new JLabel("Choose");
        String[] menu ={"Add Node","Remove Node","Search","Cut the Tree","End"};
        cboChoose=new JComboBox(menu);
        txtAreaUp=new JTextArea();
        txtAreaDown=new JTextArea();
        btnProcess=new JButton("Process");
        btnClose=new JButton("Close");
        lblValue=new JLabel("Value");
        txtValue=new JTextField();
        setTitle("Binary Tree ADT Application by P. Oncada");
        setSize(400, 300);
        getContentPane().setBackground(new Color(100,50,90,80));
        setLayout(null);
        txtAreaUp.setEditable(false);
        txtAreaDown.setEditable(false);
        display();
        add(txtAreaUp).setBounds(20,20,330,60);
        add(new JScrollPane(txtAreaDown)).setBounds(20,80,330,100);
        add(lblChoose).setBounds(20,200,60,20);
        add(cboChoose).setBounds(80,200,150,20);
        add(btnProcess).setBounds(240,200,110,60);
        add(lblValue).setBounds(20,230,100,20);
        add(txtValue).setBounds(80,230,150,20);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnProcess.addActionListener(this);
        cboChoose.addItemListener(this);
        txtValue.addKeyListener(this);
        btnProcess.setEnabled(false);
    }

    public static void main(String[] args) {
        new BTNodeMenu();
    }

    public void actionPerformed(ActionEvent e) {
        int i=cboChoose.getSelectedIndex();
        if(i==0){
            int value=Integer.parseInt(txtValue.getText());
            tree.addNode(value); // when the first menu is selected, it will call method insert from Binary Tree ADT
        }else if(i==1){
            int value=Integer.parseInt(txtValue.getText());
            if (tree.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tree is currently empty for node removal", "Removal", JOptionPane.ERROR_MESSAGE);
            }
            else if (tree.search(value)) {
                if (value == tree.getRoot().getData() && tree.count() == 1) {
                    tree.cut();
                }else {
                    tree.delete(value);
                }
            }else {
                JOptionPane.showMessageDialog(null, value + " is not found within the Binary Tree", "Removal", JOptionPane.INFORMATION_MESSAGE);
            }

        }else if(i==2){
            int value=Integer.parseInt(txtValue.getText());
            if (tree.search(value)) {
                JOptionPane.showMessageDialog(null, value + " is found within the Binary Tree", "Search", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, value + " is not found within the Binary Tree", "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(i==3){
            tree.cut();
        }else if(i==4){ System.exit(0); }
        display();
        btnProcess.setEnabled(false);
    }
    public void itemStateChanged(ItemEvent e) {
        int i=cboChoose.getSelectedIndex();
        if(i==0){
            disable();
        }else if(i==1){
            enable();
        }else if(i==2){
            disable();
        }else if(i==3){
            disableAll();
            btnProcess.setEnabled(true);
        }else if(i==4){
//disableAll();
            txtValue.setText("0");
            txtValue.setEditable(false);
            btnProcess.setEnabled(true);
        }
    }
    public void enable(){ txtValue.setEditable(true); }
    public void disable(){ txtValue.setEditable(true); }
    public void disableAll(){ txtValue.setEditable(false); }
    public void keyPressed(KeyEvent arg0) { }
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(txtValue)){
            btnProcess.setEnabled(!txtValue.getText().isEmpty());
        }
    }
    public void keyTyped(KeyEvent e) {
        if(e.getSource().equals(txtValue)){
            if(!(Character.isDigit(e.getKeyChar()))){
                e.consume();
            }
        }
    }
    void display() {
//calling methods from a Binary Tree ADT
        traversal="Level Order\t: "+tree.traverseLevelOrder()
                +"\nInorder\t: "+tree.traverseInOrder()
                +"\nPreorder\t: "+tree.traversePreOrder()
                +"\nPostorder\t: "+tree.traversePostOrder()
                +"\nInternal Nodes\t: "+tree.printParents()
                +"\nLeaves\t: "+tree.printLeaves();
        txtAreaDown.setText(traversal);

        hold="Empty\t: "+tree.isEmpty()+"\tCurrent Nodes\t: "+tree.count()
                +"\nDepth\t: "+tree.depth()+"\tHeight\t: "+tree.height()
                +"\nLevel\t: "+tree.level()+"\tType\t: "+tree.treeType();
        txtAreaUp.setText(hold);
    }
}
