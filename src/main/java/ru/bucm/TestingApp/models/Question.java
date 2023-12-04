package ru.bucm.TestingApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "image_link")
    private String image_link;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="test_id")
    private Test test;

    public Question(String description, String image_link, Test test) {
        this.description = description;
        this.image_link = image_link;
        this.test = test;
    }

    public Question() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
