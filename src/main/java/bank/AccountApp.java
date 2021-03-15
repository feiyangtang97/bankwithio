import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AccountApp extends JFrame {
  AccountUtility au = new AccountUtility();
  
  private JComboBox<String> accountDrop;
  
  private JTextField balanceText;
  
  private JButton depositButton;
  
  private JButton exitButton;
  
  private JLabel jLabel1;
  
  private JLabel jLabel2;
  
  private JLabel jLabel3;
  
  private JLabel jLabel4;
  
  private JTextField nameText;
  
  private JTextField openText;
  
  private JButton transferButton;
  
  private JButton withdrawButton;
  
  public AccountApp() {
    initComponents();
    loadFile();
    ArrayList<String> filler = new ArrayList<>();
    for (Map.Entry<String, CheckingAccount> test : this.au.map.entrySet()) {
      CheckingAccount now = (CheckingAccount)test.getValue();
      filler.add(now.getNumber());
    } 
    Collections.sort(filler);
    for (String s : filler)
      this.accountDrop.addItem(s); 
    this.openText.setText("");
    this.nameText.setText("");
    this.balanceText.setText("");
    this.depositButton.setEnabled(false);
    this.withdrawButton.setEnabled(false);
    this.transferButton.setEnabled(false);
  }
  
  private void initComponents() {
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    this.accountDrop = new JComboBox<>();
    this.openText = new JTextField();
    this.nameText = new JTextField();
    this.balanceText = new JTextField();
    this.depositButton = new JButton();
    this.withdrawButton = new JButton();
    this.transferButton = new JButton();
    this.exitButton = new JButton();
    setDefaultCloseOperation(3);
    setTitle("Bank Account Application");
    this.jLabel1.setText("Account Number");
    this.jLabel2.setText("Open Date:");
    this.jLabel3.setText("Customer Name:");
    this.jLabel4.setText("Balance:");
    this.accountDrop.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            AccountApp.this.accountDropActionPerformed(evt);
          }
        });
    this.openText.setEditable(false);
    this.nameText.setEditable(false);
    this.balanceText.setEditable(false);
    this.depositButton.setText("Deposit");
    this.depositButton.setEnabled(false);
    this.depositButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            AccountApp.this.depositButtonActionPerformed(evt);
          }
        });
    this.withdrawButton.setText("Withdraw");
    this.withdrawButton.setEnabled(false);
    this.withdrawButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            AccountApp.this.withdrawButtonActionPerformed(evt);
          }
        });
    this.transferButton.setText("Transfer To");
    this.transferButton.setDoubleBuffered(true);
    this.transferButton.setEnabled(false);
    this.transferButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            AccountApp.this.transferButtonActionPerformed(evt);
          }
        });
    this.exitButton.setText("Exit");
    this.exitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            AccountApp.this.exitButtonActionPerformed(evt);
          }
        });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(57, 57, 57)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.jLabel1)
            .addComponent(this.jLabel2)
            .addComponent(this.jLabel3)
            .addComponent(this.jLabel4))
          .addGap(46, 46, 46)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(this.accountDrop, -2, -1, -2)
            .addComponent(this.openText, -1, 195, 32767)
            .addComponent(this.nameText)
            .addComponent(this.balanceText))
          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 56, 32767)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(this.transferButton, -1, -1, 32767)
            .addComponent(this.depositButton, -1, -1, 32767)
            .addComponent(this.withdrawButton, -1, -1, 32767)
            .addComponent(this.exitButton, -1, -1, 32767))
          .addGap(69, 69, 69)));
    layout.setVerticalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(67, 67, 67)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.jLabel1)
            .addComponent(this.accountDrop, -2, -1, -2)
            .addComponent(this.depositButton))
          .addGap(34, 34, 34)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.jLabel2)
            .addComponent(this.openText, -2, -1, -2)
            .addComponent(this.withdrawButton))
          .addGap(43, 43, 43)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.jLabel3)
            .addComponent(this.nameText, -2, -1, -2)
            .addComponent(this.transferButton))
          .addGap(38, 38, 38)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.jLabel4)
            .addComponent(this.balanceText, -2, -1, -2)
            .addComponent(this.exitButton))
          .addContainerGap(46, 32767)));
    pack();
  }
  
  private void exitButtonActionPerformed(ActionEvent evt) {
    System.exit(0);
  }
  
  private void accountDropActionPerformed(ActionEvent evt) {
    dataFill();
    this.depositButton.setEnabled(true);
    this.withdrawButton.setEnabled(true);
    this.transferButton.setEnabled(true);
  }
  
  private void depositButtonActionPerformed(ActionEvent evt) {
    Double money = Double.valueOf(0.0D);
    String Deposit = "";
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    String key = (String)this.accountDrop.getSelectedItem();
    CheckingAccount now = (CheckingAccount)this.au.getCurrentAccount(key);
    int k = 0, j = 0;
    while (k < 1) {
      try {
        Deposit = JOptionPane.showInputDialog("How much to deposit?");
        if (Deposit != null) {
          money = new Double(Deposit);
          if (money.doubleValue() > 0.0D) {
            k++;
            continue;
          } 
          JOptionPane.showMessageDialog(null, "You must enter a positive number.", "Alert", 0);
          continue;
        } 
        k++;
        j++;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid deposit format: " + Deposit, "Alert", 0);
      } 
    } 
    if (j == 0 && now.deposit(money))
      JOptionPane.showMessageDialog(null, currency
          .format(money) + " was deposited to account: " + key, "Deposit Successful", 1); 
    this.au.writeToFile();
    loadFile();
    dataFill();
  }
  
  private void withdrawButtonActionPerformed(ActionEvent evt) {
    Double money = Double.valueOf(0.0D);
    String Withdraw = "";
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    String key = (String)this.accountDrop.getSelectedItem();
    CheckingAccount now = (CheckingAccount)this.au.getCurrentAccount(key);
    int k = 0, j = 0;
    while (k < 1) {
      try {
        Withdraw = JOptionPane.showInputDialog("How much to withdraw?");
        if (Withdraw != null) {
          money = new Double(Withdraw);
          if (money.doubleValue() > 0.0D) {
            k++;
            continue;
          } 
          JOptionPane.showMessageDialog(null, "You must enter a positive number.", "Alert", 0);
          continue;
        } 
        j++;
        k++;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid withdrawl format: " + Withdraw, "Alert", 0);
      } 
    } 
    if (j == 0 && !now.withdraw(money)) {
      JOptionPane.showMessageDialog(null, "Invalid withdrawl ammount: " + currency
          .format(money), "Alert", 0);
    } else if (j == 0) {
      JOptionPane.showMessageDialog(null, currency
          .format(money) + " was withdrawn from account: " + key, "Withdraw Successful", 1);
    } 
    this.au.writeToFile();
    loadFile();
    dataFill();
  }
  
  private void transferButtonActionPerformed(ActionEvent evt) {
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    Double money = Double.valueOf(0.0D);
    String keyTo = "";
    String Transfer = "";
    String key = (String)this.accountDrop.getSelectedItem();
    CheckingAccount now = (CheckingAccount)this.au.getCurrentAccount(key);
    CheckingAccount later = new CheckingAccount();
    int message = 100;
    boolean correct = false;
    int j = 0;
    while (j == 0 && !correct) {
      keyTo = JOptionPane.showInputDialog("To which account?");
      if (keyTo != null) {
        if (this.au.checkmap(keyTo) && !keyTo.equals(key)) {
          correct = true;
          continue;
        } 
        JOptionPane.showMessageDialog(null, "Invalid beneficiery account: " + keyTo, "Alert", 0);
        continue;
      } 
      j++;
    } 
    if (j == 0)
      later = (CheckingAccount)this.au.getCurrentAccount(keyTo); 
    int k = 0;
    while (j == 0 && k < 1) {
      try {
        Transfer = JOptionPane.showInputDialog("How much to transfer?");
        if (Transfer != null) {
          money = new Double(Transfer);
          if (money.doubleValue() > 0.0D) {
            k++;
            continue;
          } 
          JOptionPane.showMessageDialog(null, "You must enter a positive number.", "Alert", 0);
          continue;
        } 
        k++;
        j++;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid transfer format: " + Transfer, "Alert", 0);
      } 
    } 
    if (j == 0)
      message = now.transferTo(later, money); 
    if (message == 0) {
      JOptionPane.showMessageDialog(null, currency
          .format(money) + " was transfered to account: " + keyTo + "\nNo transfer fee was applied.", "Transfer Successful", 1);
    } else if (message == 1) {
      JOptionPane.showMessageDialog(null, currency
          .format(money) + " was transfered to account: " + keyTo + "\n$2 transfer fee was applied.", "Transfer Successful", 1);
    } else if (message == -1) {
      JOptionPane.showMessageDialog(null, "Transfer unsuccessful because balance less than transfer ammount and transfer fee", "Alert", 0);
    } else if (message == -2) {
      JOptionPane.showMessageDialog(null, "Transfer unsuccessful because balance less than transfer ammount", "Alert", 0);
    } 
    this.au.writeToFile();
    loadFile();
    dataFill();
  }
  
  public static void main(String[] args) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        } 
      } 
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(AccountApp.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(AccountApp.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(AccountApp.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(AccountApp.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            AccountApp app = new AccountApp();
            app.setVisible(true);
            app.setLocationRelativeTo(null);
          }
        });
  }
  
  public boolean checkValid(String s) {
    boolean valid = false;
    return valid;
  }
  
  public void loadFile() {
    this.au.readFile();
  }
  
  public void dataFill() {
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    String a = (String)this.accountDrop.getSelectedItem();
    CheckingAccount now = (CheckingAccount)this.au.getCurrentAccount(a);
    this.openText.setText(now.getDate());
    this.nameText.setText(now.getName());
    Double d = now.getBalance();
    this.balanceText.setText(currency.format(d));
  }
}
