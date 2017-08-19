package com.bus.validate.match;

import java.lang.annotation.Annotation;

import com.bus.validate.exception.SmartValidateException;

/**
 * 
 * @author xms
 *
 * @param <T>
 */
public abstract class AbstractMatchValidate<T extends Annotation> {

	public abstract void validate(T t, String fieldName, Object value) throws SmartValidateException;
	
	/**
	 * 获取字段名称
	 * @param name
	 * @param fieldName
	 * @return
	 */
	protected String getName(String name, String fieldName) {
		
		if(name == null || name.trim().length() == 0) {
			
			return fieldName;
		}
		
		return name;
	}
	
	/**
	 * 获取提示信息
	 * @param definedMessage
	 * @param defaultMessage
	 * @param defaultMessageArgus
	 * @return
	 */
	protected String getMessage(
			String definedMessage,
			String defaultMessage,
			Object... defaultMessageArgus) {
		
		if(definedMessage == null || definedMessage.trim().length() == 0) {
			
			return String.format(defaultMessage, defaultMessageArgus);
			
		}
		
		return definedMessage;
	}
}
