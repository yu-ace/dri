package com.example.car2.controller;

import com.example.car2.model.Student;
import com.example.car2.model.User;
import com.example.car2.service.IExamRecordsService;
import com.example.car2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    IStudentService studentService;
    @Autowired
    IExamRecordsService examRecordsService;

    @RequestMapping(path = "/newStudent",method = RequestMethod.POST)
    public String newStudent(
            @RequestParam(name = "name")
            String name, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        studentService.newStudent(name);
        model.addAttribute("tip","添加成功");
        return "newStudent";
    }

    @RequestMapping(path = "/studentList",method = RequestMethod.POST)
    public String studentList(
            @RequestParam(name = "number")
            int n, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<Student> studentPage = studentService.studentList(of);
        model.addAttribute("studentList",studentPage);
        return "studentList";
    }

    @RequestMapping(path = "/studentListByStatus",method = RequestMethod.POST)
    public String studentListByStatus(
            @RequestParam(name = "status")
            int status,
            @RequestParam(name = "number")
            int number,
            Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(number,10);
        Page<Student> studentPage = studentService.studentListByStatus(status,of);
        model.addAttribute("studentList",studentPage);
        return "studentList";
    }

    @RequestMapping(path = "/addGrade",method = RequestMethod.POST)
    public String addGrade(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "n")
            int n,
            @RequestParam(name = "grade")
            int grade,
            Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        studentService.addGrade(id,n,grade);
        examRecordsService.newExamRecords(id,n,grade);
        model.addAttribute("tip","录入成功");
        return "addGrade";
    }

    @RequestMapping(path = "/gradeListByStudentId",method = RequestMethod.POST)
    public String gradeListByStudentId(
            @RequestParam(name = "id")
            int id,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        Student student = studentService.getStudent(id);
        model.addAttribute("gradeList",student);
        return "gradeList";
    }

    @RequestMapping(path = "/gradeListPage",method = RequestMethod.POST)
    public String gradeListPage(
            @RequestParam(name = "number")
            int n, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<Student> studentPage = studentService.studentList(of);
        model.addAttribute("gradeList",studentPage);
        return "gradeList";
    }
}
