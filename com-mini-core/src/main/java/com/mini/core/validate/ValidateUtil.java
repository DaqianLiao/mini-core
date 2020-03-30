package com.mini.core.validate;

import com.mini.core.util.StringUtil;
import com.mini.core.validation.Validator;

import java.util.EventListener;

public final class ValidateUtil implements EventListener {
	/**
	 * 设置错误码和错误消息
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void sendError(int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).send();
	}
	
	/**
	 * 设置错误码和错误消息
	 * @param error 错误码
	 * @param args  消息参数
	 */
	@Deprecated
	public static void sendError(int error, Object... args) {
		Validator.status(error).args(args).send();
	}
	
	/**
	 * 验证表达式/传入参数是否为true
	 * @param validate 表达式/传入参
	 * @param error    表达式为 false 的提示错误码
	 * @param message  静态式为 false 的提示错误消息
	 * @param args     消息参数
	 */
	@Deprecated
	public static void is(boolean validate, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(validate);
	}
	
	/**
	 * 验证表达式/传入参数是否为true
	 * @param validate 表达式/传入参
	 * @param error    表达式为 false 的提示错误码
	 * @param args     消息参数
	 */
	@Deprecated
	public static void is(boolean validate, int error, Object... args) {
		Validator.status(error).args(args).is(validate);
	}
	
	/**
	 * 验证字符串与正则表达式是否匹配
	 * @param string  字符串
	 * @param regex   正则表达式
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isRegex(String string, String regex, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isPattern(string, regex));
	}
	
	/**
	 * 验证字符串与正则表达式是否匹配
	 * @param string 字符串
	 * @param regex  正则表达式
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isRegex(String string, String regex, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isPattern(string, regex));
	}
	
	/**
	 * 验证传入字符串是否有值
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isNotBlank(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isNotBlank(string));
	}
	
	/**
	 * 验证传入字符串是否有值
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isNotBlank(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isNotBlank(string));
	}
	
	
	/**
	 * 验证传入对象是否为空
	 * @param object  传入对象
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isNotNull(Object object, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(object != null);
	}
	
	/**
	 * 验证传入对象是否为空
	 * @param object 传入对象
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isNotNull(Object object, int error, Object... args) {
		Validator.status(error).args(args).is(object != null);
	}
	
	/**
	 * 验证传入对象是否为空
	 * @param object  传入对象
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isNull(Object object, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(object == null);
	}
	
	/**
	 * 验证传入对象是否为空
	 * @param object 传入对象
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isNull(Object object, int error, Object... args) {
		Validator.status(error).args(args).is(object == null);
	}
	
	/**
	 * 验证传入字符串是否为邮箱格式
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isEmail(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isEmail(string));
	}
	
	/**
	 * 验证传入字符串是否为邮箱格式
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isEmail(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isEmail(string));
	}
	
	/**
	 * 验证传入字符串是否为电话号码
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isPhone(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isPhone(string));
	}
	
	/**
	 * 验证传入字符串是否为电话号码
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isPhone(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isPhone(string));
	}
	
	/**
	 * 验证传入字符串是否为手机号
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isMobile(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isMobile(string));
	}
	
	/**
	 * 验证传入字符串是否为手机号
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isMobile(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isMobile(string));
	}
	
	/**
	 * 验证传入字符串是否为手机号或者电话号码
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isMobilePhone(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isMobilePhone(string));
	}
	
	/**
	 * 验证传入字符串是否为手机号或者电话号码
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isMobilePhone(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isMobilePhone(string));
	}
	
	/**
	 * 验证传入字符串是否为纯英文字母
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isLetter(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isLetter(string));
	}
	
	/**
	 * 验证传入字符串是否为纯英文字母
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isLetter(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isLetter(string));
	}
	
	/**
	 * 验证传入字符串是否为纯数字
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isNumber(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isNumber(string));
	}
	
	/**
	 * 验证传入字符串是否为纯数字
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isNumber(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isNumber(string));
	}
	
	/**
	 * 验证传入字符串是否为纯中文
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isChinese(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isChinese(string));
	}
	
	/**
	 * 验证传入字符串是否为纯中文
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isChinese(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isChinese(string));
	}
	
	/**
	 * 验证传入字符串是否为身份证号码
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isIdCard(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isIdCard(string));
	}
	
	/**
	 * 验证传入字符串是否为身份证号码
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isIdCard(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isIdCard(string));
	}
	
	/**
	 * 验证传入字符串是否为字母、数字和下划线组成,数字不能开头
	 * @param string  传入字符串
	 * @param error   错误码
	 * @param message 错误消息
	 * @param args    消息参数
	 */
	@Deprecated
	public static void isRequire(String string, int error, String message, Object... args) {
		Validator.status(error).message(message).args(args).is(StringUtil.isRequire(string));
	}
	
	/**
	 * 验证传入字符串是否为字母、数字和下划线组成,数字不能开头
	 * @param string 传入字符串
	 * @param error  错误码
	 * @param args   消息参数
	 */
	@Deprecated
	public static void isRequire(String string, int error, Object... args) {
		Validator.status(error).args(args).is(StringUtil.isRequire(string));
	}
}
