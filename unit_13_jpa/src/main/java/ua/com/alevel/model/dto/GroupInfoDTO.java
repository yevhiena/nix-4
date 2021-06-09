package ua.com.alevel.model.dto;


public class GroupInfoDTO {
    private long id;
    private String code;
    private String courseName;

    public GroupInfoDTO(){
    }

    public GroupInfoDTO(long id, String code, String courseName) {
        this.id = id;
        this.code = code;
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "GroupInfoDTO{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
