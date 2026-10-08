package model;

public class SupportGroup {

    private Long groupId;
    private String groupName;
    private String description;
    private String category;
    private String teamLead;

    public SupportGroup() {
    }

    public SupportGroup(Long groupId, String groupName,
                        String description, String category,
                        String teamLead) {

        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.category = category;
        this.teamLead = teamLead;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    @Override
    public String toString() {
        return "SupportGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", category='" + category + '\'' +
                ", teamLead='" + teamLead + '\'' +
                '}';
    }
}