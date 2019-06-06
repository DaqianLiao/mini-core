package com.mini.util.dao;

import java.util.Random;
import java.util.UUID;

/**
 * 主键获取,规则：当前时间缀转36进制字符串 + 两位36进制IP码 + 一位36进制随机码
 * @author XChao
 */
public final class PKGenerator {
	private static final long BASE_TIME = 1451606400000L;
	private static final PKGenerator INSTANCE = new PKGenerator();
	private final static char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	private long workerId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;

	private PKGenerator() {
		workerId = (new Random().nextInt(16) + 1);
	}

	public static void setWorkerId(long workerId) {
		INSTANCE.workerId = workerId;
	}

	/**
	 * 根据主键获取生成主键生成时的时间缀
	 * @param key 主键
	 * @return 时间戳
	 */
	public static long millis(long key) {
		return INSTANCE.sequence(key);
	}

	/**
	 * 生成主键
	 * @return 主键
	 */
	public static long key() {
		return INSTANCE.generate();
	}

	/**
	 * 生成一个UUID 替换掉"-"
	 * @return UUID
	 */
	public static String uuid() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "").toUpperCase();
	}

	/**
	 * 生成length位长度的纯数字的随机字符串
	 * @param length 长度
	 * @return 随机数字
	 */
	public static String number(int length) {
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(random.nextInt(10));
		}
		return result.toString();
	}

	/**
	 * 生成length位长度的字母加数据的随机字符串
	 * @param length 长度
	 * @return 随机字符
	 */
	public static String random(int length) {
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0, len = DIGITS.length; i < length; i++) {
			result.append(DIGITS[random.nextInt(len)]);
		}
		return result.toString();
	}

	/**
	 * 生成主键
	 * @return 随机主键
	 */
	private synchronized long generate() {
		if (sequence > 0x3FFF) {
			sequence = 0;
		}
		long timestamp = System.currentTimeMillis();
		while (sequence == 0 && lastTimestamp > timestamp) {
			lastTimestamp = timestamp;
		}
		long val = ((0x7fffffffffffffffL & (timestamp - BASE_TIME) << 22));
		return val | ((sequence++ & 0x3FFF) << 8) | ((workerId & 0xff));
	}

	/**
	 * 根据主键获取生成主键时的时间缀
	 * @param generate 主键
	 * @return 时间戳
	 */
	private long sequence(long generate) {
		return ((generate & 0xFFFFFFFFFFC00000L) >> 22) + BASE_TIME;
	}
}
