package com.example.car2.controller;

import com.example.car2.model.ExamRecords;
import com.example.car2.model.User;
import com.example.car2.service.IExamRecordsService;
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
public class ExamRecordsController {

    @Autowired
    IExamRecordsService examRecordsService;

    @RequestMapping(path = "/examRecordsListByStudentId",method = RequestMethod.POST)
    public String examRecordsListByStudentId(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "number")
            int number, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(number,10);
        Page<ExamRecords> examRecordsList = examRecordsService.getExamRecordsListById(id,of);
        model.addAttribute("examRecordsList",examRecordsList);
        return "examRecordsList";
    }

    @RequestMapping(path = "/examRecordsListByPage",method = RequestMethod.POST)
    public String examRecordsListByPage(
            @RequestParam(name = "number")
            int number, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(number,10);
        Page<ExamRecords> examRecordsList = examRecordsService.getExamRecordsList(of);
        model.addAttribute("examRecordsList",examRecordsList);
        return "examRecordsList";
    }
}
