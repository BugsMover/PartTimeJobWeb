package factory;

import dao.UserDao;
import proxy.UserDaoProxy;

public class Daofactory {
	public static UserDao getUserDaoInstance() {
		return new UserDaoProxy();
	}

}
