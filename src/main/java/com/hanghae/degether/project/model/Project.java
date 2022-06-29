package com.hanghae.degether.project.model;

import com.hanghae.degether.project.dto.ProjectDto;
import com.hanghae.degether.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column
    private String thumbnail;
    @Column
    private String projectName;
    @Column
    private String projectDescription;
    @Column
    private int feCount;
    @Column
    private int beCount;
    @Column
    private int deCount;
    @Column
    private String github;
    @Column
    private String figma;
    @Column
    private LocalDate deadLine;
    @Column
    private String step;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Language> languages;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Genre> genres;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Comment> comments;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<UserProject> userProjects;

    public ProjectDto.Response update(
            String projectName,
            String projectDescription,
            int feCount,
            int beCount,
            int deCount,
            String github,
            String figma,
            LocalDate deadLine,
            String step,
            List<Language> language,
            List<Genre> genre,
            String thumbnail) {

        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.feCount = feCount;
        this.beCount = beCount;
        this.deCount = deCount;
        this.github = github;
        this.figma = figma;
        this.deadLine = deadLine;
        this.step = step;
        this.languages = language;
        this.genres = genre;
        this.thumbnail = thumbnail;
        return ProjectDto.Response.builder()
                .projectName(projectName)
                .projectDescription(projectDescription)
                .feCount(feCount)
                .beCount(beCount)
                .deCount(deCount)
                .github(github)
                .figma(figma)
                .deadLine(deadLine)
                .step(step)
                .language(language)
                .genre(genre)
                .thumbnail(thumbnail)
                .build();
    }
}