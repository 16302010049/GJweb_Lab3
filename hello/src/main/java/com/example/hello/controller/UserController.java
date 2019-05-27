package com.example.hello.controller;

import com.example.hello.myBatis.SqlSessionLoader;
import com.example.hello.myBatis.po.User;
import com.example.hello.request.UserLoginRequest;
import com.example.hello.request.UserRegisterRequest;
import com.example.hello.response.ErrorResponse;
import com.example.hello.response.LoginResponse;
import com.example.hello.response.RegisterResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Object register(@RequestBody UserRegisterRequest request) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        User user = sqlSession.selectOne("hello.UserMapper.findUserByUsername", request.getUsername());
        if (user != null) {
            sqlSession.close();
            return new ErrorResponse("The username is already used");
        } else {
            sqlSession.insert("hello.UserMapper.addUser", new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getPhone()));
            sqlSession.commit();
            sqlSession.close();
            return new RegisterResponse("abc"); // use your generated token here.
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody Object login(@RequestBody UserLoginRequest userLoginRequest) throws IOException{
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        User user = sqlSession.selectOne("hello.UserMapper.login",userLoginRequest);
        if(user!=null){
            return  new LoginResponse("Login successfully!");
        }
        else {
            return new ErrorResponse("Username or password wrong");
        }
    }

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() throws IOException{
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<User> users = sqlSession.selectList("hello.UserMapper.listAllUsers");
        return users;
    }
}
