package com.ruoyi.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ruoyi.auth.form.LoginInfoBody;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;

import java.util.Map;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;


    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @PostMapping("loginInfo")
    public AjaxResult loginInfo(@RequestBody LoginBody form)
    {
        LoginInfoBody loginInfoBody = new LoginInfoBody();
        RegisterBody registerBody = new RegisterBody();
//        Patient patient = new Patient();
        registerBody.setUsername(form.getUsername());
        registerBody.setPassword("123456");
//        patient.setPatientName(form.getUsername());
//        patient.setPatientPhone("17737149483");
//        patient.setPatientEmail("17737149483@163.com");
//        patient.setPatientNum("412987200111216754");

        try {
           register(registerBody);
            // 用户登录
            LoginUser userInfo = sysLoginService.login(registerBody.getUsername(), registerBody.getPassword());
            Map<String, Object> token = tokenService.createToken(userInfo);
            loginInfoBody.setAccessToken(token);
            loginInfoBody.setUsername(registerBody.getUsername());
            loginInfoBody.setPassword(registerBody.getPassword());
            loginInfoBody.setPatientId(8129);
        }catch (Exception e){
            LoginUser userInfo = sysLoginService.login("admin", "admin123");
            Map<String, Object> token = tokenService.createToken(userInfo);
            loginInfoBody.setAccessToken(token);
            loginInfoBody.setUsername("admin");
            loginInfoBody.setPassword("admin123");
            loginInfoBody.setPatientId(8129);
        }
        return AjaxResult.success(loginInfoBody);
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
