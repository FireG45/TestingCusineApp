package ru.bucm.TestingApp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long test_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_link")
    private String image_link;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    public Test(String name, String description, String imageLink, User author) {
        this.name = name;
        this.description = description;
        image_link = imageLink;
        this.author = author;
    }

    public Test() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.test_id = id;
    }

    public Long getId() {
        return test_id;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author_id) {
        this.author = author_id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
