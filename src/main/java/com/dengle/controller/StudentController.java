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
public class StudentController {

}
