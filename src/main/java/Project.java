import java.sql.Date;

public class Project {

  private String id;
  private String name;
  private String managerId;
  private String monitorId;
  private String clientId;
  private Date startDate;
  private Date endDate;
  private String frameworks;
  private String languages;
  private String milestones;
  private int status;

  public Project(
      String id,
      String name,
      String managerId,
      String monitorId,
      String clientId,
      Date startDate,
      Date endDate,
      String frameworks,
      String languages,
      String milestones,
      int status) {
    this.id = id;
    this.name = name;
    this.managerId = managerId;
    this.monitorId = monitorId;
    this.clientId = clientId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frameworks = frameworks;
    this.languages = languages;
    this.milestones = milestones;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getManagerId() {
    return managerId;
  }

  public String getMonitorId() {
    return monitorId;
  }

  public String getClientId() {
    return clientId;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getFrameworks() {
    return frameworks;
  }

  public String getLanguages() {
    return languages;
  }

  public String getMilestones() {
    return milestones;
  }

  public int getStatus() {
    return status;
  }
}
