package com.example.project_task_app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_task_app.entity.Project;
import com.example.project_task_app.entity.Task;
import com.example.project_task_app.enums.TaskPriority;
import com.example.project_task_app.enums.TaskStatus;
import com.example.project_task_app.repository.ProjectRepository;
import com.example.project_task_app.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;

	public TaskController(TaskRepository taskRepository,
			ProjectRepository projectRepository) {
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
	}

	@GetMapping
	public String list(@RequestParam(required = false) Long projectId,
			@RequestParam(required = false) TaskStatus status,
			Model model) {

		List<Project> projects = projectRepository.findAll();
		List<Task> tasks;

		if (projectId != null && status != null) {
			tasks = taskRepository.findByProjectIdAndStatus(projectId, status);
		} else if (projectId != null) {
			tasks = taskRepository.findByProjectId(projectId);
		} else if (status != null) {
			tasks = taskRepository.findByStatus(status);
		} else {
			tasks = taskRepository.findAll();
		}

		model.addAttribute("projects", projects);
		model.addAttribute("tasks", tasks);
		model.addAttribute("statuses", TaskStatus.values());
		model.addAttribute("selectedProjectId", projectId);
		model.addAttribute("selectedStatus", status);
		model.addAttribute("pageTitle", "タスク一覧");

		return "task_list";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		model.addAttribute("projects", projectRepository.findAll());
		model.addAttribute("statuses", TaskStatus.values());
		model.addAttribute("priorities", TaskPriority.values());
		model.addAttribute("pageTitle", "タスク登録");
		return "task_form";
	}

	@PostMapping
	public String create(@RequestParam Long projectId,
			@ModelAttribute Task task) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + projectId));
		task.setProject(project);
		taskRepository.save(task);
		return "redirect:/tasks";
	}

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));

		model.addAttribute("task", task);
		model.addAttribute("projects", projectRepository.findAll());
		model.addAttribute("statuses", TaskStatus.values());
		model.addAttribute("priorities", TaskPriority.values());
		model.addAttribute("pageTitle", "タスク編集");
		return "task_form";
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id,
			@RequestParam Long projectId,
			@ModelAttribute Task formTask) {

		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + projectId));

		task.setProject(project);
		task.setTitle(formTask.getTitle());
		task.setDescription(formTask.getDescription());
		task.setAssignee(formTask.getAssignee());
		task.setStatus(formTask.getStatus());
		task.setPriority(formTask.getPriority());
		task.setDueDate(formTask.getDueDate());
		task.setEstimatedHours(formTask.getEstimatedHours());
		task.setActualHours(formTask.getActualHours());

		taskRepository.save(task);
		return "redirect:/tasks";
	}
}