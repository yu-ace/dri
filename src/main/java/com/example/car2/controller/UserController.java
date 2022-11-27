package com.example.car2.controller;

import com.example.car2.model.User;
import com.example.car2.service.IUserService;
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
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = "/login" ,method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model, HttpSession session){
        User user = userService.getUserByName(name);
        if(user == null){
            model.addAttribute("error","该用户不存在");
            return "login";
        }
        if(user.getIsDelete() == 1){
            model.addAttribute("error","用户已注销");
            return "login";
        }
        if(user.getPassword().equals(password)){
            session.setAttribute("user",user);
            return "redirect:/board";
        }else{
            model.addAttribute("error","密码错误");
            return "login";
        }
    }

    @RequestMapping(path = "/userListPage",method = RequestMethod.POST)
    public String gradeListPage(
            @RequestParam(name = "number")
            int n, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<User> userPage = userService.getUserListByStatus(0,of);
        model.addAttribute("userList",userPage);
        return "userList";
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        User user1 = userService.getUserByName(name);
        if(user1 == null){
            userService.newUser(name,password);
            model.addAttribute("tip","添加成功");
            return "register";
        }else if(user1.getIsDelete() == 1){
            if(user1.getPassword().equals(password)){
                userService.activationUser(name);
                model.addAttribute("tip","该用户已重新激活");
                return "register";
            }else{
                model.addAttribute("tip","该用户已注销，密码错误，激活失败");
                return "register";
            }
        }else{
            model.addAttribute("tip","该用户已存在，添加失败");
            return "register";
        }

    }

    @RequestMapping(path = "/delete",method = RequestMethod.POST)
    public String delete(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "password")
            String password,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        User user1 = userService.getUserById(id);
        if(user1 == null){
            model.addAttribute("tip","用户不存在");
            return "delete";
        }
        if(user1.getIsDelete() == 1){
            model.addAttribute("tip","该用户早已注销");
            return "delete";
        }
        if(user1.getName().equals("admin")){
            model.addAttribute("tip","该用户为管理员，无法删除");
            return "delete";
        }
        if(user1.getPassword().equals(password)){
            userService.deleteUser(id);
            model.addAttribute("tip","删除成功");
            return "delete";
        }else{
            model.addAttribute("tip","密码错误");
            return "delete";
        }
    }

    @RequestMapping(path = "/change",method = RequestMethod.POST)
    public String change(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "newPassword")
            String newPassword,
            @RequestParam(name = "newPassword1")
            String newPassword1,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        User user1 = userService.getUserByName(name);
        if(user1 == null){
            model.addAttribute("tip","用户不存在");
            return "change";
        }
        if(user1.getIsDelete() == 1){
            model.addAttribute("tip","该用户早已注销");
            return "change";
        }
        if(user1.getName().equals("admin")){
            model.addAttribute("tip","该用户为管理员，无法删除");
            return "change";
        }
        if(user1.getPassword().equals(password)){
            if(newPassword.equals(password) || newPassword1.equals(password)){
                model.addAttribute("tip","新密码与老密码相同，更改失败");
                return "change";
            }else if(!newPassword.equals(newPassword1)){
                model.addAttribute("tip","两次新密码不同，更改失败");
                return "change";
            }else{
                user1.setPassword(newPassword);
                model.addAttribute("tip","更改成功");
                return "change";
            }
        }else{
            model.addAttribute("tip","密码错误");
            return "change";
        }
    }
}
