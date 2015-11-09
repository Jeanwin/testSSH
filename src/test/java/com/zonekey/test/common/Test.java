package com.zonekey.test.common;

import java.security.Key;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.junit.Assert;

public class Test {

	/**
	 * AES对称加密
	 */
	@org.junit.Test
	public void test() {
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128); // 设置key长度
		// 生成key
		Key key = aesCipherService.generateNewKey();
		String text = "hello";
		// 加密
		String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
		// 解密
		String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

		Assert.assertEquals(text, text2);
	}

}
