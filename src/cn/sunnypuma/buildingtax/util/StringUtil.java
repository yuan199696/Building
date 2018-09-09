package cn.sunnypuma.buildingtax.util;

import java.util.Random;

public class StringUtil {
	public static final String SOURCES =
            "abcdefghijklmnopqrstuvwxyz1234567890";
	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length) {
		Random random = new Random();
		char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
	}
}
