package utils;

import java.util.UUID;

public class WebUtils {

	public static String makeID(){
		return UUID.randomUUID().toString();
	}
}
