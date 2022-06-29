package com.hanghae.degether.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.degether.project.model.Genre;
import com.hanghae.degether.project.model.Language;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class ProjectDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request{
        @NotEmpty(message = "프로젝트명을 입력해 주세요.")
        @Size(min = 2, max = 20, message = "2글자 이상, 20글자 이하로 입력해 주세요.")
        private String projectName;

        @NotEmpty(message = "프로젝트 설명을 입력해 주세요.")
        @Size(min = 2, max = 50, message = "2글자 이상, 50글자 이하로 입력해 주세요.")
        private String projectDescription;

        @NotNull(message = "올바른 값을 입력해 주세요.")
        @PositiveOrZero(message = "0 이상의 숫자를 입력해 주세요.")
        private int feCount;

        @NotNull(message = "올바른 값을 입력해 주세요.")
        @PositiveOrZero(message = "0 이상의 숫자를 입력해 주세요.")
        private int beCount;

        @NotNull(message = "올바른 값을 입력해 주세요.")
        @PositiveOrZero(message = "0 이상의 숫자를 입력해 주세요.")
        private int deCount;

        private String github;

        private String figma;

        private LocalDate deadLine;

        private String step;
        private List<Language> language;
        private List<Genre> genre;
    }
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response{
        private String thumbnail;
        private String projectName;
        private String projectDescription;
        private int feCount;
        private int beCount;
        private int deCount;
        private String github;
        private String figma;
        private LocalDate deadLine;
        private String step;
        private List<Language> language;
        private List<Genre> genre;
        private List<CommentDto.Response> comment;
        private List<UserDto> user;
        private List<UserDto> applyUser;
        private List<DocDto> notice;
        private List<DocDto> todo;
    }


}