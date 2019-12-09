package snkienholz.github;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows the user to input their full name and then create a user id of their first name, surname,
 * and an email address of their first initial and last name
 */
public class Employee {

  StringBuilder name;
  String username;
  String password;
  String email;

  public Employee(String name, String password) {
    this.name = new StringBuilder(name);

    if (checkName()) {
      setUsername(name);
      setEmail(name);
    } else {
      this.username = "default";
      this.email = "user@oracleacademy.Test";
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * Formats the employee's name to a username ex.(Name: John Doe Username: jdoe)
   * @param name - name entered by employee
   */
  private void setUsername(String name) {

    String[] fullName = name.split(" ");

    username = (fullName[0].charAt(0) + fullName[1]).toLowerCase();
  }

  /**
   * Formats the employee's name to an email address ex.(john.doe@oracleacademy.Test)
   * @param name - name entered by employee
   */
  private void setEmail(String name) {

    String[] fullName = name.split(" ");

    email = (fullName[0] + "." + fullName[1]).toLowerCase() + "@oracleacademy.Test";
  }

  /**
   * Checks if name entered contains a space.
   * @return - returns true if there is a space
   */
  private boolean checkName() {

    // returns a -1 if space " " does not exist
    return (name.indexOf(" ") >= 0);
  }

  /**
   * Checks for a valid password, containing a lowercase and an uppercase letter, and
   * a special character
   * @return - returns true if password passes above stated criteria
   */
  private boolean isValidPassword(String password) {

    // regex pattern including uppercase, lowercase, special, and at least 3 characters
    String pattern = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{3,}";

    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(password);

    // return true if a match was found
    return m.find();
  }

  /**
   * Reverses saved password for security regulations
   * @param pw - user-entered password
   * @return - reversed password
   */
  public String reverseString(String pw) {

    if (pw.isEmpty()) {
      return pw;
    }

    return reverseString(pw.substring(1)) + pw.charAt(0);
  }

  @Override
  public String toString() {
    return "Employee Details" + "\n"
        + "Name : " + name + "\n"
        + "Username : " + username + "\n"
        + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }
}
