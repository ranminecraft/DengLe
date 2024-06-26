package com.dengle.controller;

import cn.hutool.json.JSONObject;
import com.dengle.bean.UserData;
import com.dengle.sql.entity.Users;
import com.dengle.sql.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Pattern;

import static com.dengle.bean.JsonKey.CODE;
import static com.dengle.bean.JsonKey.MSG;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@RestController
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public String register(@RequestBody UserData data) {
        JSONObject json = new JSONObject();
        if (data.getUsername() == null ||
                !Pattern.compile("^[a-zA-Z一-龥]{2,8}$")
                        .matcher(data.getUsername()).matches()) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "昵称由2~8位中文或英文组成");
            return json.toString();
        }

        if (data.getEmail() == null ||
                !Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                        .matcher(data.getEmail()).matches()) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "错误邮箱格式");
            return json.toString();
        }

        if (userRepository.findByUsername(data.getUsername()) != null) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "用户名已注册过");
            return json.toString();
        }

        if (userRepository.findByEmail(data.getEmail()) != null) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "邮箱已注册过");
            return json.toString();
        }

        Users user = new Users();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setTime(new Date());
        user.setAdmin(false);
        user.setActive(true);
        userRepository.save(user);

        json.set(CODE, SC_OK);
        json.set(MSG, "成功");
        return json.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public String login(@RequestBody UserData data) {
        JSONObject json = new JSONObject();

        if (data.getUsername() == null ||
                !Pattern.compile("^[a-zA-Z一-龥]{2,8}$")
                        .matcher(data.getUsername()).matches()) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "昵称由2~8位中文或英文组成");
            return json.toString();
        }

        Users user = userRepository.findByUsername(data.getUsername());
        if (user == null) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "该用户未注册");
            return json.toString();
        }

        if (!user.getPassword().equals(data.getPassword())) {
            json.set(CODE, SC_BAD_REQUEST);
            json.set(MSG, "密码错误");
            return json.toString();
        }
        json.set(CODE, SC_OK);
        json.set(MSG, "成功");
        return json.toString();
    }
}
