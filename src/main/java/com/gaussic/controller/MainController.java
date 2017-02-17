package com.gaussic.controller;

import com.gaussic.model.UserEntity;
import com.gaussic.repository.UserRepository;
import com.gaussic.repository.UserSortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by HP-HQ on 2017/2/6.
 */
@Controller
public class MainController {
    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSortRepository userSortRepository;

    @RequestMapping(value = "/users/pageList", params = "pageSize")
    public @ResponseBody List<UserEntity> getPageList(@RequestParam(value = "pageSize") Integer pageSize) {
        Page<UserEntity> all = userSortRepository.findAll(new PageRequest(2, pageSize));
        List<UserEntity> userEntities = all.getContent();
        return userEntities;
    }

    public void add() {
        for (int i = 0; i < 100; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setNickname("xxx" + i);
            userEntity.setPassword("pass" + i);
            userEntity.setFirstname("fist" + i);
            userEntity.setLastname("last" + i);
            userRepository.saveAndFlush(userEntity);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "admin/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
// 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);
        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }

    // 查看用户详情
// @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
// 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {
        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);
        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    @RequestMapping(value = "admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {
        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    @RequestMapping(value = "admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {
        // 更新用户信息
        userRepository.updateUser(user.getNickname(), user.getFirstname(),
                user.getLastname(), user.getPassword(), user.getId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {
        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }
}
