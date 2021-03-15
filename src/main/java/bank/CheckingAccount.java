import java.util.GregorianCalendar;

public class CheckingAccount extends Account {
  CheckingAccount() {}
  
  CheckingAccount(String a, String b, GregorianCalendar c, Double d) {
    super(a, b, c, d);
  }
  
  public int transferTo(CheckingAccount c, Double d) {
    int message = 0;
    Double total = Double.valueOf(d.doubleValue() + TRANSFER_FEE.doubleValue());
    if (getBalance().doubleValue() > CHECKING_BALANCE_THRESHOLD.doubleValue()) {
      withdraw(d);
    } else if (withdraw(total)) {
      message = 1;
    } else if (getBalance().doubleValue() < d.doubleValue()) {
      message = -2;
    } else {
      message = -1;
    } 
    if (message == 0 || message == 1)
      c.deposit(d); 
    return message;
  }
}