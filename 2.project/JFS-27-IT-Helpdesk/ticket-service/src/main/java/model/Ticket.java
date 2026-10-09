
package model;

public class Ticket extends BaseEntity {

    private Long ticketId;
    private String title;
    private String description;
    private String category;
    private String priority;
    private String status;
    private String createdBy;
    private String assignedTo;

    public Ticket() {
        super();
    }

    public Ticket(Long ticketId, String title, String description,
                  String category, String priority, String status,
                  String createdBy, String assignedTo) {

        super();

        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
        updateTimestamp();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateTimestamp();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        updateTimestamp();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
        updateTimestamp();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        updateTimestamp();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        updateTimestamp();
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        updateTimestamp();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
