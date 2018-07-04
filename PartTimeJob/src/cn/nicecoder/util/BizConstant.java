package cn.nicecoder.util;

/**
 * 业务常量
 *-------------------------------
 * @author longtian
 * @date 2018年4月12日下午9:49:56
 * @description nicecoder.cn
 *-------------------------------
 */
public enum BizConstant {
	BIZ_NEWS_0("0","新增兼职"),
	BIZ_NEWS_1("1","查询所有兼职"),
	BIZ_NEWS_2("2","查询单条兼职"),
	BIZ_NEWS_3("3","删除单条兼职"),
	BIZ_NEWS_4("4","兼职详情"),
	BIZ_NEWS_5("5","评论兼职"),
	BIZ_NEWS_6("6","点赞"),
	BIZ_NEWS_7("7","首页显示兼职"),
	;
	
	private String pCode;
	private String pName;
	
	private BizConstant(String pCode,String pName) {
		this.pCode = pCode;
		this.pName = pName;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	
	
}
