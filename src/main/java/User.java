public class User {

  String id;
  String username;
  String password;
  String mail;
  String phone;
  int department;
  int role;

  public User(
      String id,
      String username,
      String password,
      String mail,
      String phone,
      int department,
      int role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.mail = mail;
    this.phone = phone;
    this.department = department;
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getMail() {
    return mail;
  }

  public String getPhone() {
    return phone;
  }

  public int getDepartment() {
    return department;
  }

  public int getRole() {
    return role;
  }
}
