package guru.qa.main.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentModel {
    private String name, role;
    private List<String> teachersName;
    private CourseInfoModel courseInfo;
}
