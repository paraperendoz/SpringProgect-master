package com.levelup.levelup.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = User.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "team_student", joinColumns = @JoinColumn(name = "team_id"))
    private Set<User> students;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User mentor;

    public Long getId() {
        return id;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    public String getMentorName() {
        return mentor != null ? (mentor.getName() + mentor.getSurname()) : "none";
    }
}
