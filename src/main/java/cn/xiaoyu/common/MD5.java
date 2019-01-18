package cn.xiaoyu.common;

import java.security.MessageDigest;

/**
 * 说明：MD5处理
 * 修改时间：2017年9月5日
 * @version
 */
public class MD5 {

    public static String md5(String userId, String time, String appKey) {
        return md5(userId + time + appKey);
    }
    
	public static String md5(Integer userId, String time, String appKey) {
		return md5(userId + time + appKey);
	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance(Constants.MD5);
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println("加密的： "+md5("1"+"997959066"));
	}
}
