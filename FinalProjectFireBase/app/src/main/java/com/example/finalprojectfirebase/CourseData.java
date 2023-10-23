package com.example.finalprojectfirebase;

public class CourseData {
    private String instructorId  ;
    private String courseId ;
    private String courseName ;
    private String quizeGrade ;
    private String attendanceGrade ;
    private String projectsGrade ;

    public CourseData() {
    }

    public CourseData(String instructorId, String courseId, String courseName, String quizeGrade, String attendanceGrade, String projectsGrade) {
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.quizeGrade = quizeGrade;
        this.attendanceGrade = attendanceGrade;
        this.projectsGrade = projectsGrade;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getquizeGrade() {
        return quizeGrade;
    }

    public void setQuizeGrade(String quizeGrade) {
        this.quizeGrade = quizeGrade;
    }

    public String getAttendanceGrade() {
        return attendanceGrade;
    }

    public void setAttendanceGrade(String attendanceGrade) {
        this.attendanceGrade = attendanceGrade;
    }

    public String getProjectsGrade() {
        return projectsGrade;
    }

    public void setProjectsGrade(String projectsGrade) {
        this.projectsGrade = projectsGrade;
    }
}
