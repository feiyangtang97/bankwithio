import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AccountUtility implements AccountConstants {
  File file = new File("accounts.txt");
  File result = new File("accounts_result.txt");
  
  HashMap<String, CheckingAccount> map = new HashMap<>();
  
  public void readFile() {
    try {
      BufferedReader in = new BufferedReader(new FileReader(this.file));
      String line = in.readLine();
      while (line != null) {
        String[] array = line.split("<>");
        String key = array[0];
        String name = array[2];
        Double balance = Double.valueOf(array[3]);
        String[] days = array[1].split("/");
        int[] info = new int[3];
        for (int i = 0; i < 3; i++)
          info[i] = Integer.parseInt(days[i]); 
        GregorianCalendar date = new GregorianCalendar(info[0], info[1], info[2]);
        CheckingAccount acc = new CheckingAccount(key, name, date, balance);
        acc.setLiteral(array[1]);
        this.map.put(array[0], acc);
        line = in.readLine();
      } 
    } catch (IOException e) {
      System.out.println(e);
    } 
  }
  
  public Account getCurrentAccount(String a) {
    CheckingAccount temp = new CheckingAccount();
    for (Map.Entry<String, CheckingAccount> test : this.map.entrySet()) {
      if (a.equals(test.getKey()))
        temp = (CheckingAccount)test.getValue(); 
    } 
    return temp;
  }
  
  public boolean checkmap(String s) {
    boolean check = false;
    for (Map.Entry<String, CheckingAccount> test : this.map.entrySet()) {
      if (s.equals(test.getKey()))
        check = true; 
    } 
    return check;
  }
  
  public void writeToFile() {
    try {
      ArrayList<String> holder = new ArrayList<>();
      BufferedReader in = new BufferedReader(new FileReader(this.result));
      CheckingAccount temp = new CheckingAccount();
      for (Map.Entry<String, CheckingAccount> test : this.map.entrySet()) {
        temp = (CheckingAccount)test.getValue();
        String name = temp.getName();
        String number = temp.getNumber();
        String balance = temp.getBalance().toString();
        String date = temp.getLiteral();
        String[] arr = { number, date, name, balance };
        String newLine = String.join("<>", (CharSequence[])arr);
        holder.add(newLine);
      } 
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.result)));
      for (String s : holder)
        out.println(s); 
      in.close();
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    } 
  }
}
