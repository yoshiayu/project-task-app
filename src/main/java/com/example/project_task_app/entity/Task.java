package com.example.project_task_app.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.project_task_app.enums.TaskPriority;
import com.example.project_task_app.enums.TaskStatus;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(nullable = false, length = 200)
	private String title;

	@Column(length = 1000)
	private String description;

	@Column(length = 100)
	private String assignee;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TaskStatus status = TaskStatus.TODO;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TaskPriority priority = TaskPriority.MEDIUM;

	private LocalDate dueDate;

	@Column(precision = 10, scale = 2)
	private BigDecimal estimatedHours;

	@Column(precision = 10, scale = 2)
	private BigDecimal actualHours;

	public Task() {
	}

	public Long getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public BigDecimal getActualHours() {
		return actualHours;
	}

	public void setActualHours(BigDecimal actualHours) {
		this.actualHours = actualHours;
	}
}
