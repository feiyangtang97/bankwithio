import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class Account implements AccountConstants {
  private String number;
  
  private String name;
  
  private GregorianCalendar date;
  
  private Double balance;
  
  private String literalDate;
  
  Account() {}
  
  Account(String a, String b, GregorianCalendar c, Double d) {
    this.number = a;
    this.name = b;
    this.date = c;
    this.balance = d;
    this.date.roll(2, -1);
  }
  
  public void setNumber(String s) {
    this.number = s;
  }
  
  public void setName(String s) {
    this.name = s;
  }
  
  public void setDate(GregorianCalendar c) {
    this.date = c;
  }
  
  public void setBalance(Double d) {
    this.balance = d;
  }
  
  public void setLiteral(String s) {
    this.literalDate = s;
  }
  
  public String getNumber() {
    return this.number;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    sdf.setCalendar(this.date);
    String dateFormatted = sdf.format(this.date.getTime());
    return dateFormatted;
  }
  
  public Double getBalance() {
    return this.balance;
  }
  
  public String getLiteral() {
    return this.literalDate;
  }
  
  public boolean deposit(Double d) {
    boolean out = true;
    this.balance = Double.valueOf(this.balance.doubleValue() + d.doubleValue());
    return out;
  }
  
  public boolean withdraw(Double d) {
    boolean out = false;
    if (this.balance.doubleValue() >= d.doubleValue()) {
      this.balance = Double.valueOf(this.balance.doubleValue() - d.doubleValue());
      out = true;
    } 
    return out;
  }
  
  public abstract int transferTo(CheckingAccount paramCheckingAccount, Double paramDouble);
}
