package org.ada.myfirstspringproject.dto;

public class AcademicDegreeDTO {

    private Integer id;
    private String title;
    private String institution;
    private String date;

    public AcademicDegreeDTO(String title, String institution, String date) {
        this.title = title;
        this.institution = institution;
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInstitution() {
        return institution;
    }

    public String getDate() {
        return date;
    }
}
