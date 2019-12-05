package com.code.community.community.controller;

import com.code.community.community.dto.AccessTokenDto;
import com.code.community.community.dto.GithubUser;
import com.code.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by lixing on 2019/9/1 11:13
 * @author lx
 */
@Controller
public class AuthController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("4a946bdb83d9f252e74b");
        accessTokenDto.setClient_secret("33588a6947816a4b97d7190003b7df4bf35c0baa");
        accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
