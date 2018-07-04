package dao;

import vo.User;

public interface UserDao {
	//现在完成的是登陆验证，登陆操作只返回两种结果
	public boolean findLogin(User user)throws Exception ;
	public boolean doCreate(User user)throws Exception;
	public boolean findword(User user) throws Exception;
	public boolean changeword(User user)throws Exception;
}
