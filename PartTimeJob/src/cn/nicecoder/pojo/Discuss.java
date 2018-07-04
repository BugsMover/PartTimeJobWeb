package cn.nicecoder.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Discuss类
 *-------------------------------
 * @author longtian
 * @date 2018年4月24日下午11:17:17
 * @description nicecoder.cn
 *-------------------------------
 */
public class Discuss {
	public int id;
	public String type;
	public String discussId;
	public String content;
	public String userId;
	public String pudate;
	public String agree;
	
	public List<Discuss> list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPudate() {
		return pudate;
	}
	public void setPudate(String pudate) {
		this.pudate = pudate;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getDiscussId() {
		return discussId;
	}
	public void setDiscussId(String discussId) {
		this.discussId = discussId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Discuss> getList() {
		return list;
	}
	public void setList(List<Discuss> list) {
		this.list = list;
	}
	public Discuss(int id, String type, String discussId, String content,String userId, String pudate, String agree) {
		this.id = id;
		this.type = type;
		this.discussId = discussId;
		this.content = content;
		this.userId = userId;
		this.pudate = pudate;
		this.agree = agree;
	}
	
}
