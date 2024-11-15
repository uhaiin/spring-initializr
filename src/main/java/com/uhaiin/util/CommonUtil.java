package com.uhaiin.util;

import java.util.UUID;

public class CommonUtil {
	/**
	 * 生成 uuid
	 *
	 * @return uuid
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}