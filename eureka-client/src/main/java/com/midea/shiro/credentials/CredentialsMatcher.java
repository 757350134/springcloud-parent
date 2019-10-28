package com.midea.shiro.credentials;

import com.midea.utils.UsingAesHopeUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


public class CredentialsMatcher extends SimpleCredentialsMatcher {
    //重写父类的方法,用于验证密码有效性
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken intoken = (UsernamePasswordToken) token;
        //获取用户输入的密码
        String inPassword = new String(intoken.getPassword());
        //获得数据库的密码
        String dbPassword = (String) info.getCredentials();
        try {
            dbPassword = UsingAesHopeUtil.decrypt(dbPassword, intoken.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //验证密码是否一致
        return this.equals(dbPassword, inPassword);
    }
}
