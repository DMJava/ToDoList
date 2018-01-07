package com.app.controller;

import com.app.entity.Task;
import com.app.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private List<Task> tasks;
    private Integer page = 1;
    private String currentView = "redirect:viewForm/all";

    @RequestMapping(value = "viewForm/window", method = RequestMethod.GET)
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        return "window";
    }

    @RequestMapping(value = "taskById")
    public String getTaskById(Model model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "window";
    }

    @RequestMapping(value = "viewForm/all")
    public String getAllTask(Model model) {
        if (!currentView.equals("redirect:viewForm/all")) {
            page = 1;
            tasks = taskService.getAllTask((page - 1) * 5, page * 5, null);
        }
        setAttribute(model, null);
        currentView = "redirect:viewForm/all";
        return "viewForm";
    }

    @RequestMapping(value = "viewForm/isDone")
    public String getIsDoneTask(Model model) {
        if (!currentView.equals("redirect:viewForm/isDone")) {
            page = 1;
            tasks = taskService.getAllTask((page - 1) * 5, page * 5, Boolean.TRUE);
        }
        setAttribute(model, Boolean.TRUE);
        currentView = "redirect:viewForm/isDone";
        return "viewForm";
    }

    @RequestMapping(value = "viewForm/isNotDone")
    public String getNotDoneTask(Model model) {
        if (!currentView.equals("redirect:viewForm/isNotDone")) {
            page = 1;
            tasks = taskService.getAllTask((page - 1) * 5, page * 5, Boolean.FALSE);
        }
        setAttribute(model, Boolean.FALSE);
        currentView = "redirect:viewForm/isNotDone";
        return "viewForm";
    }

    @RequestMapping(value = "deleteTask")
    public String deleteTask(Model model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        taskService.deleteTask(id);
        return currentView;
    }

    @RequestMapping(value = "taskIsDone")
    public String taskIsDone(Model model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.getTaskById(id);
        task.setDone(true);
        taskService.updateTask(task);
        return currentView;
    }

    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public String allTask(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (!result.hasErrors()) {
            task.setCreateDate(new Date());
            task.setDone(false);
            taskService.addTask(task);
        }
        return "redirect:viewForm/all";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task, BindingResult result) {
        if (!result.hasErrors()) {
            task.setDone(false);
            taskService.addTask(task);
        }
        return currentView;
    }

    @RequestMapping(value = "previousPage", method = RequestMethod.GET)
    public String previousPage() {
        Integer newPage = page - 1;
        if (newPage >= 1) {
            page = newPage;
        }
        return currentView;
    }

    @RequestMapping(value = "nextPage", method = RequestMethod.GET)
    public String nextPage() {
        Integer newPage = page + 1;
        Boolean isDone = null;
        if (currentView.equals("redirect:viewForm/isDone")) {
            isDone = true;
        } else if (currentView.equals("redirect:viewForm/isNotDone")) {
            isDone = false;
        }
        List<Task> tasks = taskService.getAllTask((newPage - 1) * 5, newPage * 5, isDone);
        if (tasks.size() > 0){
            page = newPage;
        }
            return currentView;
    }

    @RequestMapping(value = "getPage")
    public String setPage(Model model, HttpServletRequest request){
        page = Integer.parseInt(request.getParameter("page"));
        return currentView;
    }

    private void setAttribute(Model model, Boolean isDone) {
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);
        Long count = taskService.getCountTask(isDone);
        model.addAttribute("maxPage", count % 5 == 0 ? count / 5 : (count / 5) + 1);
    }

    private String getMsg(String key, HttpServletRequest request){
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }
}
