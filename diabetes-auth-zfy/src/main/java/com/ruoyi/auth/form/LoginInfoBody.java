package com.ruoyi.auth.form;

import java.util.Map;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
public class LoginInfoBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    private Integer patientId;

    private Map<String, Object> accessToken;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public Map<String, Object> getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(Map<String, Object> accessToken) {
        this.accessToken = accessToken;
    }
}
