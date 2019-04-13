import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Rot13 {

	public static String rot13(String args) {
		StringBuffer sbuf = new StringBuffer();
		String s = args;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'm')
				c += 13;
			else if (c >= 'A' && c <= 'M')
				c += 13;
			else if (c >= 'n' && c <= 'z')
				c -= 13;
			else if (c >= 'N' && c <= 'Z')
				c -= 13;
			sbuf.append(c);
		}
		System.out.println(sbuf.toString());
		return sbuf.toString();
	}

	@Test
	public void test() {
		rot13(rot13("123abcdefg HIJKLMN opq rst uvw xyz"));
		int start =  (int) (Math.random() * 10 + 6);
		System.out.println(start);
		System.out.println(randomCase(getMD5("123456").substring(start, start + 16)));
		start =  (int) (Math.random() * 10 + 6);
		System.out.println(start);
		System.out.println(randomCase(getMD5("123456").substring(start, start + 16)));
		start =  (int) (Math.random() * 10 + 6);
		System.out.println(start);
		System.out.println(randomCase(getMD5("123456").substring(start, start + 16)));
		
		System.out.println(randomCase(Long.toHexString(new Date().getTime())).substring(2, 6));
		System.out.println(Long.valueOf("164a6b00000", 16));
		Date n = new Date();
		n.setTime(Long.valueOf("164a6b00000", 16));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(n));
	}
	
	@Test
	public void t() {
		System.out.println(System.currentTimeMillis());
		Date n = new Date();
		n.setTime(Long.valueOf("164a6000000", 16));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(n));
		
		n.setTime(Long.valueOf("164cfffffff", 16));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(n));
	}
	

//	@Test
	public void testrandomCase() {
		System.out.println(randomCase("123abcdefg hijklmn opq rst uvw xyz"));
		System.out.println(randomCase("123abcdefg hijklmn opq rst uvw xyz"));
	}
	
	 /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
    public static String randomCase(String str) {
    	if(str == null)
    		return null;
    	StringBuffer buf = new StringBuffer();
    	for(int i =0 ;i<str.length();i++) {
    		if((int)(Math.random() * 10) %2 == 0) {
    			buf.append(str.substring(i, i+1).toUpperCase());
    		} else {
    			buf.append(str.substring(i, i+1).toLowerCase());
    		}
    	}
    	return buf.toString();
    }
}
