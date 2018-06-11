package com.spring.shiro.realm;

import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
public class realm extends AuthorizingRealm {

    @Autowired
    private UserInterface userInterface;
    
    @Override
    public String getName() {
        return "realm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserEntiry userByUserEntiry = userInterface.getUserByUser(username);
        System.out.println("userByUserEntiry = " + userByUserEntiry);
        if (userByUserEntiry!=null){
            System.out.println("userByUserEntiry = " + userByUserEntiry);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,userByUserEntiry.getPassword(),getName());
            return info;
        }else {
            throw new AuthenticationException();
        }
    }
}
