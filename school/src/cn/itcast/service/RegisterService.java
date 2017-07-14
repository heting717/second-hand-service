package cn.itcast.service;

import cn.itcast.entity.User;
import cn.itcast.exception.UserExistException;

public interface RegisterService {
	void register(User user) throws UserExistException;
	
}
