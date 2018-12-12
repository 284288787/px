/**create by liuhua at 2016年6月17日 下午3:59:26**/
package com.star.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageListTag extends TagSupport{
	private static final long serialVersionUID = 6405865795294582070L;
	
	private String url;
	private Integer pageNumber;
	private Integer totalPage;
	private String parentClass = "pages warp";
	private String prevBtnClass = "pages-pre";
	private String nextBtnClass = "pages-next";
	private String lineClass = "pages-num";
	private String curClass = "cur";
	private String pagesTo = "pages-to";
	private String pagesToIpt = "pages-to-input";
	private String pagesToBtn = "pages-to-btn";
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
//		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		StringBuffer outHtml = new StringBuffer();
		outHtml.append("<div class=\"" + parentClass + "\">");
		if (pageNumber > 1) {
			String prevPage = "-" + (pageNumber - 1);
			if (pageNumber == 2) {
				prevPage = "";
			}
			outHtml.append("<span class=\"" + prevBtnClass + "\"><a href=\"" + url + prevPage + "\">上一页</a></span>");
		}
		int begin = 1;
		int end = totalPage;
		if (end > 12) {
			if(pageNumber - 5 > 0){
				begin = pageNumber - 5;
				if (pageNumber + 4 < totalPage) {
					end = pageNumber + 4;
				}else {
					begin = totalPage - 10;
				}
			}else {
				end = 10;
			}
		}
		if (begin > 1) {
			outHtml.append("<span class=\"" + lineClass + "\"><a href=\"" + url + "\">1 ...</a></span>");
		}
		for (; begin <= end; begin ++) {
			if (begin == pageNumber) {
				outHtml.append("<span class=\"" + lineClass + " " + curClass + "\"><a>" + begin + "</a></span>");
			}else {
				String temp = "";
				if (begin > 1) {
					temp = "-" + begin;
				}
				outHtml.append("<span class=\"" + lineClass + "\"><a href=\"" + url + temp + "\">" + begin + "</a></span>");
			}
		}
		if (end < totalPage){
			outHtml.append("<span class=\"" + lineClass + "\"><a href=\"" + url + "-" + totalPage + "\">... " + totalPage + "</a></span>");
		}
		if (pageNumber < totalPage) {
			String nextPage = "-" + (pageNumber + 1);
			outHtml.append("<span class=\"" + nextBtnClass + "\"><a href=\"" + url + "-" + nextPage + "\">下一页</a></span>");
		}
		outHtml.append("<span class=\"" + pagesTo + "\">");
		int jumpNumber = pageNumber + 1;
		if (jumpNumber > totalPage) {
			jumpNumber = 1;
		}
		String temp = "-" + jumpNumber;
		if (jumpNumber == 1) {
			temp = "";
		}
		outHtml.append("<input type=\"text\" id=\"jumpPageIpt\" value=\"" + jumpNumber + "\" class=\"" + pagesToIpt + "\"/>");
		outHtml.append("<a href=\"" + url + temp + "\" class=\"" + pagesToBtn + "\">跳转</a>");
		outHtml.append("</span>");
		outHtml.append("</div>");
		try {
			out.println(outHtml.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public String getParentClass() {
		return parentClass;
	}

	public String getPrevBtnClass() {
		return prevBtnClass;
	}

	public String getNextBtnClass() {
		return nextBtnClass;
	}

	public String getLineClass() {
		return lineClass;
	}

	public String getCurClass() {
		return curClass;
	}

	public String getPagesTo() {
		return pagesTo;
	}

	public String getPagesToIpt() {
		return pagesToIpt;
	}

	public String getPagesToBtn() {
		return pagesToBtn;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}

	public void setPrevBtnClass(String prevBtnClass) {
		this.prevBtnClass = prevBtnClass;
	}

	public void setNextBtnClass(String nextBtnClass) {
		this.nextBtnClass = nextBtnClass;
	}

	public void setLineClass(String lineClass) {
		this.lineClass = lineClass;
	}

	public void setCurClass(String curClass) {
		this.curClass = curClass;
	}

	public void setPagesTo(String pagesTo) {
		this.pagesTo = pagesTo;
	}

	public void setPagesToIpt(String pagesToIpt) {
		this.pagesToIpt = pagesToIpt;
	}

	public void setPagesToBtn(String pagesToBtn) {
		this.pagesToBtn = pagesToBtn;
	}
}