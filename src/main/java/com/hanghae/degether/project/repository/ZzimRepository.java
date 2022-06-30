package com.hanghae.degether.project.repository;

import com.hanghae.degether.project.model.Project;
import com.hanghae.degether.project.model.Zzim;
import com.hanghae.degether.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZzimRepository extends JpaRepository<Zzim, Long> {

    boolean existsByUserAndProject(User user, Project project);

    void deleteByUserAndProject(User user, Project project);

    List<Zzim> findAllByUser(User user);
}
