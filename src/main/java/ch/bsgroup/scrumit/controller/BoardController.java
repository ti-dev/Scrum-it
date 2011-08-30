package ch.bsgroup.scrumit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.bsgroup.scrumit.domain.BurnDown;
import ch.bsgroup.scrumit.domain.BurnDownChart;
import ch.bsgroup.scrumit.domain.Project;
import ch.bsgroup.scrumit.domain.Sprint;
import ch.bsgroup.scrumit.domain.Task;
import ch.bsgroup.scrumit.domain.UserStory;
import ch.bsgroup.scrumit.pojo.SerializableBurnDownChart;
import ch.bsgroup.scrumit.pojo.SerializableTask;
import ch.bsgroup.scrumit.pojo.SerializableUserStory;
import ch.bsgroup.scrumit.service.IBurnDownChartService;
import ch.bsgroup.scrumit.service.IProjectService;
import ch.bsgroup.scrumit.service.ISprintService;
import ch.bsgroup.scrumit.service.ITaskService;
import ch.bsgroup.scrumit.service.IUserStoryService;
import ch.bsgroup.scrumit.utils.ResourceNotFoundException;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	private IProjectService projectService;
	private ISprintService sprintService;
	private IUserStoryService userStoryService;
	private ITaskService taskService;
	private IBurnDownChartService burnDownChartService;

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void setSprintService(ISprintService sprintService) {
		this.sprintService = sprintService;
	}

	public void setUserStoryService(IUserStoryService userStoryService) {
		this.userStoryService = userStoryService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public void setBurnDownChartService(IBurnDownChartService burnDownChartService) {
		this.burnDownChartService = burnDownChartService;
	}

	@RequestMapping(value="{projectid}/{sprintid}/", method=RequestMethod.GET)
	public String getBoard(@PathVariable("projectid") int pid, @PathVariable("sprintid") int sid, Model model) {
		model.addAttribute("projectid", pid);
		model.addAttribute("sprintid", sid);
		Project p = this.projectService.findProjectById(pid);
		if (p == null) {
			throw new ResourceNotFoundException(pid);
		}
		model.addAttribute("projectname", p.getName());
		Sprint s = this.sprintService.findSprintById(sid);
		if (s == null) {
			throw new ResourceNotFoundException(sid);
		}
		model.addAttribute("sprintslogan", s.getSlogan());
		return "board/board";
	}

	@RequestMapping(value="alluserstories/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody List<SerializableUserStory> getAllUserstoriesOfSprint(@PathVariable Integer sprintid) {
		Set<UserStory> userstories = this.userStoryService.getAllUserStorysBySprintId(sprintid);
		List<SerializableUserStory> serializedUserstories = new ArrayList<SerializableUserStory>();
		for (Iterator<UserStory> iterator = userstories.iterator(); iterator.hasNext();) {
			UserStory u = iterator.next();
			SerializableUserStory su = new SerializableUserStory(u.getId(), u.getName(), u.getxCoord(), u.getyCoord());
			serializedUserstories.add(su);
		}
		return serializedUserstories;
	}

	@RequestMapping(value="alltasks/{userstoryid}/", method=RequestMethod.GET)
	public @ResponseBody List<SerializableTask> getAllTasksOfUserstory(@PathVariable Integer userstoryid) {
		Set<Task> tasks = this.taskService.getAllTasksByUserstoryId(userstoryid);
		List<SerializableTask> serializedTasks = new ArrayList<SerializableTask>();
		for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
			Task t = iterator.next();
			SerializableTask st = new SerializableTask(t.getId(), t.getDescription(), t.getxCoord(), 
					t.getyCoord(), t.getStatus(), t.getDuration(), t.getCreationDate());
			serializedTasks.add(st);
		}
		return serializedTasks;
	}

	@RequestMapping(value="userstory/updatecoord/", method=RequestMethod.POST)
	public @ResponseBody void updateUserstoryCoord(@RequestBody UserStory u) {
		UserStory us = this.userStoryService.findUserStoryById(u.getId());
		us.setxCoord(u.getxCoord());
		us.setyCoord(u.getyCoord());
		this.userStoryService.updateUserStory(us);
	}

	@RequestMapping(value="userstory/updatename/", method=RequestMethod.POST)
	public @ResponseBody void updateUserstoryName(@RequestBody UserStory u) {
		UserStory us = this.userStoryService.findUserStoryById(u.getId());
		us.setName(u.getName());
		this.userStoryService.updateUserStory(us);
	}

	@RequestMapping(value="add/task/{userstoryid}/{sprintid}/", method=RequestMethod.POST)
	public @ResponseBody SerializableTask addTask(@PathVariable Integer userstoryid, @PathVariable int sprintid, @RequestBody Task t) {
		UserStory u = this.userStoryService.findUserStoryById(userstoryid);
		if (u == null) {
			throw new ResourceNotFoundException(userstoryid);
		}
		t.setUserStory(u);
		t.setCreationDate(new Date());
		Task task = this.taskService.addTask(t);
		this.burnDownChartService.updateBurnDown(task.getDuration(), 0, sprintid);
		return new SerializableTask(task.getId(), task.getDescription(), task.getxCoord(), task.getyCoord(), 
				task.getStatus(), task.getDuration(), task.getCreationDate());
	}

	@RequestMapping(value="task/updatecoord/{sprintid}/", method=RequestMethod.POST)
	public @ResponseBody void updateTaskCoord(@PathVariable int sprintid, @RequestBody Task t) {
		Task task = this.taskService.findTaskById(t.getId());
		if (task == null) {
			throw new ResourceNotFoundException(t.getId());
		}
		task.setxCoord(t.getxCoord());
		task.setyCoord(t.getyCoord());
		if (task.getStatus() != t.getStatus()) {
			// status change
			if (t.getStatus() == 2 && task.getStatus() < 2) {
				// 0,1 -> 2
				this.burnDownChartService.updateBurnDown(-task.getDuration(), task.getDuration(), sprintid);
			}
			if (task.getStatus() == 2 && t.getStatus() < 2) {
				// 2 -> 0,1
				this.burnDownChartService.updateBurnDown(task.getDuration(), -task.getDuration(), sprintid);
			}
			task.setStatus(t.getStatus());
		}
		this.taskService.updateTask(task);
	}

	@RequestMapping(value="task/updatedescription/", method=RequestMethod.POST)
	public @ResponseBody void updateTaskDescription(@RequestBody Task t) {
		Task task = this.taskService.findTaskById(t.getId());
		task.setDescription(t.getDescription());
		this.taskService.updateTask(task);
	}

	@RequestMapping(value="burndownchart/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody List<SerializableBurnDownChart> getBurnDownChart(@PathVariable int sprintid) {
		List<BurnDownChart> bdchart = this.burnDownChartService.getAllBurnDownCharts(sprintid);
		List<SerializableBurnDownChart> sbdchart = new ArrayList<SerializableBurnDownChart>();
		for (Iterator<BurnDownChart> iterator = bdchart.iterator(); iterator.hasNext();) {
			BurnDownChart bdc = iterator.next();
			SerializableBurnDownChart sbdc = new SerializableBurnDownChart(bdc.getDate(), bdc.getReal(), bdc.getOptimal());
			System.out.println(bdc.getDate()+" "+bdc.getReal());
			sbdchart.add(sbdc);
		}
		return sbdchart;
	}

	@RequestMapping(value="burndown/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody List<BurnDown> getBurnDown(@PathVariable int sprintid) {
		return this.burnDownChartService.getBurnDown(sprintid);
	}
}