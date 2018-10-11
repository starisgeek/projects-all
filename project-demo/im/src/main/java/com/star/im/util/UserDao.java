package com.star.im.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.star.im.entity.User;

public class UserDao {
	private static final List<User> users = new ArrayList<>();

	static {
		Random r = new Random();
		int base = 100000, v = 10000;
		User user = new User();
		user.setUserId(Integer.toHexString(base + r.nextInt(v)));
		user.setUsername("star");
		user.setPassword("123456");
		users.add(user);

		user = new User();
		user.setUserId(Integer.toHexString(base + r.nextInt(v)));
		user.setUsername("lifang");
		user.setPassword("123456");
		users.add(user);
	}

	public User getUser(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		for (User user : users) {
			System.out.println(user.getUserId());
		}
	}
}
