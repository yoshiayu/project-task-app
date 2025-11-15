package com.example.project_task_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_task_app.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}