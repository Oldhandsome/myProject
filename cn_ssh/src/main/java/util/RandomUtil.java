package util;


public class RandomUtil {

	public static String random() {
		int num = (int) ((Math.random())*100000); 
		String id = String.valueOf(num);
		return id;
	}
	
}
