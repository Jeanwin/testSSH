package com.zonekey.test.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

import com.zonekey.test.entity.User;

@Component
public class PasswordHelper {
	/**
	 * 随机数生成器，根据需求使用
	 */
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	// private String algorithmName = "md5";
	private int hashIterations = 2;

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	/**
	 * 使用MD5盐值加密<br>
	 * matcher会自动检测加密类型，迭代次数
	 * 
	 * @param user
	 */
	public void encryptPassword(User user) {
		// 产生随机数
		// user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new Md5Hash(user.getPassword(), user.getCredentialsSalt(), this.hashIterations).toHex();
		// String newPassword = new SimpleHash(algorithmName,
		// user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),
		// this.hashIterations).toHex();
		user.setPassword(newPassword);
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public String randomHexString() {
		return randomNumberGenerator.nextBytes().toHex();
	}

	public static void main(String[] args) {
		User u = new User();
		u.setPassword("123");
		u.setUsername("zhangsan");
		u.setSalt("123");
		System.out.println(u.getCredentialsSalt());
		PasswordHelper p = new PasswordHelper();
		p.encryptPassword(u);
		System.out.println("123:" + u.getPassword());
	}
}