package com.bus.validate;

import java.lang.annotation.Annotation;

import com.bus.validate.exception.SmartValidateException;
import com.bus.validate.match.AbstractMatchValidate;

/**
 * 验证单个变量
 * @author xms
 *
 */
public class SmartValidateObject {

	public void validateObject(
			String name,
			Object value,
			Annotation...annotations) throws SmartValidateException {
		
		matchAndExecuteValidators(name, value, annotations);
	}
	
	private void matchAndExecuteValidators(
			String name,
			Object value,
			Annotation[] rules) throws SmartValidateException {
		
		for(Annotation anno : rules) {
			
			AbstractMatchValidate matchValidate = ValidateRulePool.get(anno.annotationType());
			
			if(matchValidate == null) { continue; }
			
			matchValidate.validate(anno, name, value);
			
		}
	}
}
