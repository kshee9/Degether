package com.hanghae.degether.project.controller;

import com.hanghae.degether.project.dto.ProjectDto;
import com.hanghae.degether.project.dto.ResponseDto;
import com.hanghae.degether.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    // 프로젝트 생성
    @PostMapping("/project")
    public ResponseDto<?> createProject(
            @RequestPart ProjectDto.Request projectRequestDto,
            @RequestPart(value = "thumbnail") MultipartFile multipartFile) {
        return ResponseDto.builder()
                .ok(true)
                .message("생성 성공")
                .projectId(projectService.createProject(projectRequestDto, multipartFile))
                .build();
    }

    @PutMapping("/project/{projectId}")
    public ResponseDto<?> modifyProject(
            @PathVariable Long projectId,
            @RequestPart ProjectDto.Request projectRequestDto,
            @RequestPart(value = "thumbnail") MultipartFile multipartFile) {
        return ResponseDto.builder()
                .ok(true)
                .message("수정 성공")
                .result(projectService.modifyProject(projectId, projectRequestDto, multipartFile))
                .build();
    }

    // 프로젝트 리스트
    @GetMapping("/projects")
    public ResponseDto<?> getProjects(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "step", required = false) String step) {
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .result(projectService.getProjects(search, language, genre, step))
                .build();
    }

    // 찜하기, 찜삭제
    @PostMapping("/projectZzim/{projectId}")
    public ResponseDto<?> projectZzim(@PathVariable Long projectId) {
        projectService.projectZzim(projectId);
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .build();
    }

    // 팀원 존재 여부
    @GetMapping("/existUser/{projectId}")
    public ResponseDto<?> existUser(@PathVariable Long projectId) {
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .result(projectService.existUser(projectId))
                .build();
    }

    // 프로젝트 삭제
    @DeleteMapping("/project/{projectId}")
    public ResponseDto<?> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseDto.builder()
                .ok(true)
                .message("삭제 성공")
                .build();
    }

    // 프로젝트 미리보기
    @GetMapping("/project/{projectId}")
    public ResponseDto<?> getProject(@PathVariable Long projectId) {
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .result(projectService.getProject(projectId))
                .build();
    }
    //프로젝트 지원
    @PostMapping("/projectApply/{projectId}")
    public ResponseDto<?> applyProject(@PathVariable Long projectId) {
        projectService.applyProject(projectId);
        return ResponseDto.builder()
                .ok(true)
                .message("지원 성공")
                .build();
    }

    // 프로젝트 메인페이지
    @GetMapping("/projectMain/{projectId}")
    public ResponseDto<?> getProjectMain(@PathVariable Long projectId) {
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .result(projectService.getProjectMain(projectId))
                .build();
    }

    // 지원 받기
    @PostMapping("/addUser/{projectId}/{userId}")
    public ResponseDto<?> addUser(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.addUser(projectId, userId);
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .build();
    }

    // 지원자 삭제, 멤버 강제추방
    @PostMapping("/addUser/{projectId}/{userId}")
    public ResponseDto<?> kickUser(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.kickUser(projectId, userId);
        return ResponseDto.builder()
                .ok(true)
                .message("요청 성공")
                .build();
    }

}