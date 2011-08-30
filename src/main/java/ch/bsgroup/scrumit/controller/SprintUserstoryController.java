package ch.bsgroup.scrumit.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.bsgroup.scrumit.domain.BurnDown;
import ch.bsgroup.scrumit.domain.Project;
import ch.bsgroup.scrumit.domain.Sprint;
import ch.bsgroup.scrumit.domain.Task;
import ch.bsgroup.scrumit.domain.UserStory;
import ch.bsgroup.scrumit.pojo.SerializableSprint;
import ch.bsgroup.scrumit.pojo.SerializableUserStory;
import ch.bsgroup.scrumit.service.IBurnDownChartService;
import ch.bsgroup.scrumit.service.IProjectService;
import ch.bsgroup.scrumit.service.ISprintService;
import ch.bsgroup.scrumit.service.ITaskService;
import ch.bsgroup.scrumit.service.IUserStoryService;
import ch.bsgroup.scrumit.utils.ResourceNotFoundException;

@Controller
@RequestMapping(value="/sprint/")
public class SprintUserstoryController {
	private ISprintService sprintService;
	private IUserStoryService userStoryService;
	private IProjectService projectService;
	private IBurnDownChartService burnDownChartService;
	private ITaskService taskService;
	private Validator validator;

	@Autowired
	public SprintUserstoryController(Validator validator) {
		this.validator = validator;
	}

	public void setSprintService(ISprintService sprintService) {
		this.sprintService = sprintService;
	}

	public void setUserStoryService(IUserStoryService userStoryService) {
		this.userStoryService = userStoryService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void setBurnDownChartService(IBurnDownChartService burnDownChartService) {
		this.burnDownChartService = burnDownChartService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value="{projectid}/", method=RequestMethod.GET)
	public String getSprintUserstory(@PathVariable("projectid") int id, Model model) {
		Project p = this.projectService.findProjectById(id);
		if (p == null) {
			throw new ResourceNotFoundException(id);
		}
		model.addAttribute("projectid", id);
		model.addAttribute("projectname", p.getName());
		return "sprint/sprint-userstory";
	}
	
	@RequestMapping(value="all/{projectid}/", method=RequestMethod.GET)
	public @ResponseBody List<SerializableSprint> getAllSprintsOfProject(@PathVariable int projectid) {
		Set<Sprint> sprints = this.sprintService.getAllSprintsByProjectId(projectid);
		List<SerializableSprint> serializedSprints = new ArrayList<SerializableSprint>();
		for (Iterator<Sprint> iterator = sprints.iterator(); iterator.hasNext();) {
			Sprint s = iterator.next();
			SerializableSprint ss = new SerializableSprint(s.getId(), s.getSlogan(), s.getStartDate(), s.getEndDate());
			serializedSprints.add(ss);
		}
		return serializedSprints;
	}

	@RequestMapping(value="alluserstories/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody List<SerializableUserStory> getAllUserstoriesOfSprint(@PathVariable int sprintid) {
		Set<UserStory> userstories = this.userStoryService.getAllUserStorysBySprintId(sprintid);
		List<SerializableUserStory> serializedUserstories = new ArrayList<SerializableUserStory>();
		for (Iterator<UserStory> iterator = userstories.iterator(); iterator.hasNext();) {
			UserStory u = iterator.next();
			SerializableUserStory su = new SerializableUserStory(u.getId(), u.getName());
			serializedUserstories.add(su);
		}
		return serializedUserstories;
	}

	@RequestMapping(value="sprint/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody SerializableSprint getSprint(@PathVariable int sprintid) {
		Sprint s = this.sprintService.findSprintById(sprintid);
		if (s == null) {
			throw new ResourceNotFoundException(sprintid);
		}
		return new SerializableSprint(s.getId(), s.getSlogan(), s.getStartDate(), s.getEndDate());
	}

	@RequestMapping(value="update/", method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> updateSprint(@RequestBody Sprint s, HttpServletResponse response) {
		Set<ConstraintViolation<Sprint>> failures = validator.validate(s);
		Sprint sprint = this.sprintService.findSprintById(s.getId());
		sprint.setSlogan(s.getSlogan().trim());
		sprint.setStartDate(s.getStartDate());
		sprint.setEndDate(s.getEndDate());
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessagesSprint(failures);
		} else {
			this.sprintService.updateSprint(sprint);
			return Collections.singletonMap("sprint", 
				new SerializableSprint(
					sprint.getId(), 
					sprint.getSlogan(), 
					sprint.getStartDate(), 
					sprint.getEndDate()
			));
		}
	}

	@RequestMapping(value="userstory/{userstoryid}/", method=RequestMethod.GET)
	public @ResponseBody SerializableUserStory getUserstory(@PathVariable int userstoryid) {
		UserStory u = this.userStoryService.findUserStoryById(userstoryid);
		if (u == null) {
			throw new ResourceNotFoundException(userstoryid);
		}
		return new SerializableUserStory(u.getId(), u.getName(), u.getPriority(), u.getCreationDate(), 
				u.getEstimatedSize(), u.getAcceptanceTest());
	}
	
	@RequestMapping(value="userstory/update/", method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> updateUserstory(@RequestBody UserStory u, HttpServletResponse response) {
		UserStory us = this.userStoryService.findUserStoryById(u.getId());
		us.setName(u.getName().trim());
		us.setPriority(u.getPriority());
		us.setEstimatedSize(u.getEstimatedSize());
		us.setAcceptanceTest(u.getAcceptanceTest().trim());
		Set<ConstraintViolation<UserStory>> failures = validator.validate(us);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessagesUserstory(failures);
		} else {
			this.userStoryService.updateUserStory(us);
			return Collections.singletonMap("userstory", 
					new SerializableUserStory(
						us.getId(), 
						us.getName(), 
						us.getPriority(), 
						us.getCreationDate(), 
						us.getEstimatedSize(), 
						us.getAcceptanceTest()
					)
			);
		}
	}

	@RequestMapping(value="add/{projectid}/", method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> addSprint(@RequestBody Sprint s, @PathVariable int projectid, HttpServletResponse response) throws ParseException {
		Project p = this.projectService.findProjectById(projectid);
		if (p == null) {
			throw new ResourceNotFoundException(projectid);
		}
		s.setProject(p);
		Set<ConstraintViolation<Sprint>> failures = validator.validate(s);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessagesSprint(failures);
		} else {
			s.setSlogan(s.getSlogan().trim());
			Sprint sprint = this.sprintService.addSprint(s);
			List<BurnDown> bdList = new ArrayList<BurnDown>();

			Calendar c = Calendar.getInstance();
			c.setTime(sprint.getStartDate());
			Calendar startCalendar = new GregorianCalendar(
				c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
			c.setTime(s.getEndDate());	
			Calendar endCalendar = new GregorianCalendar(
				c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
			boolean writeBurnDown = false;
			BurnDown bd;
			if (startCalendar.compareTo(endCalendar) <= 0) {
				writeBurnDown = true;
			}
			while (startCalendar.compareTo(endCalendar) <= 0) {
				bd = new BurnDown(0, 0, sprint.getId(), startCalendar.getTime());
				bdList.add(bd);
				startCalendar.add(Calendar.DATE, 1);
			}
			if (writeBurnDown) {
				this.burnDownChartService.addBurnDownForSprint(bdList);
			}
			return Collections.singletonMap("sprint", 
					new SerializableSprint(
							sprint.getId(), 
							sprint.getSlogan(), 
							sprint.getStartDate(), 
							sprint.getEndDate()
					)
			);
		}
	}

	@RequestMapping(value="add/userstory/{sprintid}/", method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> addUserstory(@PathVariable int sprintid, @RequestBody UserStory u, HttpServletResponse response) {
		Sprint sprint = this.sprintService.findSprintById(sprintid);
		if (sprint == null) {
			throw new ResourceNotFoundException(sprintid);
		}
		u.setName(u.getName().trim());
		u.setAcceptanceTest(u.getAcceptanceTest().trim());
		u.setCreationDate(new Date());
		Set<ConstraintViolation<UserStory>> failures = validator.validate(u);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessagesUserstory(failures);
		} else {
			UserStory newUserStory = this.userStoryService.addUserStory(u);

			Set<UserStory> userstories = this.userStoryService.getAllUserStorysBySprintId(sprintid);
			userstories.add(newUserStory);
			sprint.setUserStories(userstories);
			this.sprintService.updateSprint(sprint);

			return Collections.singletonMap("userstory", 
					new SerializableUserStory(
							newUserStory.getId(), 
							newUserStory.getName(), 
							newUserStory.getPriority(), 
							newUserStory.getCreationDate(), 
							newUserStory.getEstimatedSize(), 
							newUserStory.getAcceptanceTest()
					)
			);
		}
	}

	@RequestMapping(value="remove/{sprintid}/", method=RequestMethod.GET)
	public @ResponseBody void removeSprintById(@PathVariable int sprintid) {
		this.sprintService.removeSprint(sprintid);
		// ToDo, not yet implemented
		this.burnDownChartService.removeBurnDown(sprintid);
	}

	@RequestMapping(value="userstory/remove/{userstoryid}/", method=RequestMethod.GET)
	public @ResponseBody void removeUserstoryById(@PathVariable int userstoryid) {
		Set<Task> tasks = this.taskService.getAllTasksByUserstoryId(userstoryid);
		int taskDurationOfUserstory = 0;
		for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
			Task t = iterator.next();
			taskDurationOfUserstory += t.getDuration();
		}
		if (taskDurationOfUserstory > 0) {
			// Update BurnDownChart
			
		}
		this.userStoryService.removeUserStory(userstoryid);
	}

	// internal helper
	private Map<String, String> validationMessagesSprint(Set<ConstraintViolation<Sprint>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Sprint> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}
	private Map<String, String> validationMessagesUserstory(Set<ConstraintViolation<UserStory>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<UserStory> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}
}