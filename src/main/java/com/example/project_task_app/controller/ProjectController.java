package com.example.project_task_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project_task_app.entity.Project;
import com.example.project_task_app.enums.ProjectStatus;
import com.example.project_task_app.repository.ProjectRepository;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	private final ProjectRepository projectRepository;

	public ProjectController(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("projects", projectRepository.findAll());
		model.addAttribute("pageTitle", "案件一覧");
		return "project_list";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("statuses", ProjectStatus.values());
		model.addAttribute("pageTitle", "案件登録");
		return "project_form";
	}

	@PostMapping
	public String create(@ModelAttribute Project project) {
		projectRepository.save(project);
		return "redirect:/projects";
	}

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + id));
		model.addAttribute("project", project);
		model.addAttribute("statuses", ProjectStatus.values());
		model.addAttribute("pageTitle", "案件編集");
		return "project_form";
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @ModelAttribute Project projectForm) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + id));

		project.setName(projectForm.getName());
		project.setDescription(projectForm.getDescription());
		project.setStatus(projectForm.getStatus());
		project.setStartDate(projectForm.getStartDate());
		project.setEndDate(projectForm.getEndDate());

		projectRepository.save(project);
		return "redirect:/projects";
	}
}