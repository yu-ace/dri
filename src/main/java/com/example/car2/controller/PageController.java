package com.example.car2.controller;

import com.example.car2.model.ExamRecords;
import com.example.car2.model.Student;
import com.example.car2.model.User;
import com.example.car2.service.IExamRecordsService;
import com.example.car2.service.IStudentService;
import com.example.car2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    IStudentService studentService;
    @Autowired
    IExamRecordsService examRecordsService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index1(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/board",method = RequestMethod.GET)
    public String board(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        model.addAttribute("user",user);
        return "board";
    }

    @RequestMapping(path = "/newStudent",method = RequestMethod.GET)
    public String newStudent(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "newStudent";
    }

    @RequestMapping(path = "/studentList",method = RequestMethod.GET)
    public String studentList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Student> studentPage = studentService.studentList(of);
        model.addAttribute("studentList",studentPage);
        return "studentList";
    }

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public String add(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "addGrade";
    }

    @RequestMapping(path = "/examRecords",method = RequestMethod.GET)
    public String examRecords(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<ExamRecords> examRecordList = examRecordsService.getExamRecordsList(of);
        model.addAttribute("examRecordsList",examRecordList);
        return "examRecordsList";
    }

    @RequestMapping(path = "/gradeList",method = RequestMethod.GET)
    public String gradeList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Student> studentList = studentService.studentList(of);
        model.addAttribute("gradeList",studentList);
        return "gradeList";
    }

    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public String userList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<User> userList = userService.getUserListByStatus(0,of);
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(path = "/reg",method = RequestMethod.GET)
    public String reg(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "register";
    }

    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public String delete(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "delete";
    }

    @RequestMapping(path = "/change",method = RequestMethod.GET)
    public String change(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "change";
    }
}
