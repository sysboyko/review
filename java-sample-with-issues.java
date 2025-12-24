import java.util.*;
import java.io.*;

public class UserManager {

  public static List users = new ArrayList(); // raw type

  public static int avgAge = 0;

  public static void main(String[] args) {
    loadUsers("users.txt");
    process();
    System.out.println("AVG AGE = " + avgAge);
  }

  public static void loadUsers(String file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = null;
      while (true) {
        line = br.readLine();
        if (line == null) break;

        String[] parts = line.split(",");

        Map user = new HashMap(); // no generics
        user.put("name", parts[0]);
        user.put("age", Integer.parseInt(parts[1]));
        user.put("active", parts[2].equals("true"));

        users.add(user);
      }

    } catch (Exception e) {
      System.out.println("Error"); // swallowing error details
    }
  }

  public static void process() {
    int total = 0;
    int count = 0;

    for (int i = 0; i < users.size(); i++) {
      Map u = (Map) users.get(i);

      if (u.get("active") == true) { // reference comparison
        total += (int) u.get("age");
        count++;
      }
    }

    avgAge = total / count; // possible division by zero
  }
}