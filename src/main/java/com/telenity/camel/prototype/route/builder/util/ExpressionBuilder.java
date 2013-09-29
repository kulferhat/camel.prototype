package com.telenity.camel.prototype.route.builder.util;

import org.apache.camel.Expression;
import org.apache.camel.model.language.GroovyExpression;
import org.apache.camel.model.language.JXPathExpression;
import org.apache.camel.model.language.SimpleExpression;
import org.apache.camel.model.language.XPathExpression;


public class ExpressionBuilder {
	public enum ExpressionType { 
		SIMPLE,
		XPATH,
		JXPATH,
		GROOVY;
		
	}
	
	public static Expression getExpression(String expression, ExpressionType expressionType){
		if (expressionType == ExpressionType.SIMPLE){
			return new SimpleExpression(expression);
		}
		else if (expressionType == ExpressionType.XPATH){
			return new XPathExpression(expression);
		}
		else if (expressionType == ExpressionType.JXPATH){
			return new JXPathExpression(expression);
		}
		else if (expressionType == ExpressionType.GROOVY){
			return new GroovyExpression(expression);
		}
		else{
			throw new UnsupportedOperationException("Unsupported expressionType=" + expressionType);
		}
	}
}
