package org.sun.admin.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.sun.model.Admin;

/**
* @author sun 
* @date Jan 16, 2018 4:44:54 PM
* 
*/
public class Utils {

	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static final int hashIterations = 2;
    
    public static void encryptPassword(Admin model) {
        // User对象包含最基本的字段Username和Password
    	model.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
        String newPassword = new SimpleHash(algorithmName, model.getPassword(),
                ByteSource.Util.bytes(model.getSalt()), hashIterations).toHex();
        model.setPassword(newPassword);
    }
    
    public static void main(String[] args) {
    	String salt = randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(algorithmName, "111111",
                ByteSource.Util.bytes(salt), hashIterations).toHex();
        System.out.println("salt: "+salt + " newPassword "+newPassword);
	}
}
