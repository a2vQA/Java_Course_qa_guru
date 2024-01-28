package guru.qa.model;

import java.util.List;

public class Student {
    private String name;
    private String role;
    private List<String> teachersName;
    private CourseInfo courseInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(List<String> teachersName) {
        this.teachersName = teachersName;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }
}
