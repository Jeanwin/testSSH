package com.zonekey.test.shiro.realm;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.zonekey.test.entity.User;
import com.zonekey.test.service.impl.UserServiceImpl;
import com.zonekey.test.shiro.token.Token;
import com.zonekey.test.util.PasswordHelper;

@Component
public class ShiroDbRealm extends AuthorizingRealm {

	@Resource
	private UserServiceImpl userService;
	// 自定义密码加密服务
	@Resource
	private PasswordHelper ph;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获取角色信息
		authorizationInfo.setRoles(ShiroDbRealm.getStringSet(userService.get(username).getRoles(), "code"));
		// 获取权限信息
		authorizationInfo.setStringPermissions(ShiroDbRealm.getStringSet(userService.get(username).getPermissions(), "code"));

		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Token t = (Token) token;
		System.out.println(t.getPrincipal().toString());
		String username = t.getPrincipal().toString();

		User user = userService.get(username);

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Boolean.TRUE.equals(user.isLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), this.getName());
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 将对象集合中某个字段转换为一个set集合
	 * 
	 * @param list
	 * @return
	 */
	public static Set<String> getStringSet(Set<?> set, String fieldName) {
		Set<String> s = new HashSet<String>();
		for (Object o : set) {
			Field f;
			try {
				// getFiled只能获取public字段
				f = o.getClass().getDeclaredField(fieldName);
				f.setAccessible(true);
				s.add(f.get(o).toString());
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

}
