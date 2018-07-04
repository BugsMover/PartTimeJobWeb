package sha256;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha_256 {


	public static String getSHA256Strjava(String str) {
		MessageDigest messageDigest = null;
		String encodeStr = "";
		try {
			try {
				messageDigest =MessageDigest.getInstance("SHA-256");
				messageDigest.update(str.getBytes("UTF-8"));
				encodeStr =byte2Hex(messageDigest.digest());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	private static String byte2Hex(byte[] bytes) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0;i<bytes.length;i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if(temp.length()==1) {
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

}
