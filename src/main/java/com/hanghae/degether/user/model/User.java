package com.hanghae.degether.user.model;


import com.hanghae.degether.project.model.Language;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "UserInfo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @Column(nullable = false)
    private  String username;

    @Column(nullable = false)
    private  String nickname;

    @Column(nullable = false)
    private  String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private List<Language> language;

    @Column
    private  String profileUrl;

    @Column
    private  String role;

    @Column
    private  String github;

    @Column
    private String figma;

    @Column
    private String intro;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private boolean status = true ;


    @Builder
    public User(String username, String nickName, String profileUrl, String password){
        this.username = username;
        this.nickname = nickName;
        this.profileUrl = profileUrl;
        this.password = password;
    }
    @Builder
    public void update(String profileUrl, String role, String nickName, List<Language> language, String github, String figma, String intro, String phoneNumber, String email) {
        this.profileUrl =profileUrl;
        this.role = role;
        this.nickname = nickName;
        this.language.clear();
        this.language.addAll(language);
        this.github = github;
        this.figma = figma;
        this.intro = intro;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }


}
