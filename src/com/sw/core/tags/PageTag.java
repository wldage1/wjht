/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.core.tags
 * FileName: PageTag.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-10
 * @last Modified 
 * @history
 */
package com.sw.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.sw.core.common.Constant;

/**页面公共引用标签
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午4:04:34
 *  LastModified:
 *  History:
 *  </pre>
 */
public class PageTag extends BodyTagSupport{
	
	/**
	 * 似有的css文件
	 */
	private String css;
	/**
	 * 似有的js文件
	 */
	private String js;
	
	
	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public int doStartTag() throws JspException {
		String common = (String) pageContext.getSession().getAttribute("common");
		String style = (String)pageContext.getSession().getAttribute("style");
		String base = (String)pageContext.getSession().getAttribute("base");
		  JspWriter writer = pageContext.getOut();
		  try {
			writer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.role/TR/html4/loose.dtd\">");
			writer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			writer.append("<head>").append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			
			//公共的css
			writer.append("<link  href=\"").append(base).append("/").append(common).append("/").append(style).append("/css/skin.css\" ")
			.append(" rel=\"stylesheet\" type=\"text/css\" />");
			
			writer.append("<link  href=\"").append(base).append("/").append(common).append("/").append(style)
			.append("/css/jquery.ui.custom.css\" ").append(" rel=\"stylesheet\" type=\"text/css\" />");
			
			//渲染似有的css
			if(css != null){
				String[] array = css.split(",");
				for(String mycss : array){
					writer.append("<link  href=\"").append(base).append("/").append(common).append("/").append(style)
					.append("/css/").append(mycss).append("\" rel=\"stylesheet\" type=\"text/css\" />");
				}
			}
			
			//公共的js
			writer.append("<script type=\"text/javascript\"  src=\"").append(base).append("/").append(common)
			.append("/js/jquery-1.6.min.js\" ").append("></script>");
			writer.append("<script type=\"text/javascript\"  src=\"").append(base).append("/").append(common)
			.append("/js/jquery.json-2.2.min.js\" ").append("></script>");
			
			if(js != null){
				String[] arrayjs = js.split(",");
				for(String myjs : arrayjs){
					writer.append("<script type=\"text/javascript\"  src=\"").append(base).append("/").append(common)
					.append("/js/").append(myjs).append("\" ></script>");
				}
			}
			
			writer.append("</head>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return EVAL_BODY_INCLUDE;
	}
   
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append("</html>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	
}
