package com.andresguachisaca.service;

import com.andresguachisaca.model.User;

public interface IUserService extends ICrud<User> {

	User getByUserName(String username);

}
