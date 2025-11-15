package com.example.project_task_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_task_app.entity.Task;
import com.example.project_task_app.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByProjectId(Long projectId);

	List<Task> findByStatus(TaskStatus status);

	List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
}