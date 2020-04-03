public class Client {

  private String id;
  private String contactName;
  private String company;
  private String email;
  private String phone;
  private String address;
  private int level;
  private boolean deleted;

  public Client(
      String id,
      String contactName,
      String company,
      String email,
      String phone,
      String address,
      int level,
      boolean deleted) {
    this.id = id;
    this.contactName = contactName;
    this.company = company;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.level = level;
    this.deleted = deleted;
  }

  public String getId() {
    return id;
  }

  public String getContactName() {
    return contactName;
  }

  public String getCompany() {
    return company;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public int getLevel() {
    return level;
  }

  public boolean isDeleted() {
    return deleted;
  }
}
