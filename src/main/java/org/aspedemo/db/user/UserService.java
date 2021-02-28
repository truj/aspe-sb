package org.aspedemo.db.user;

import java.util.List;
import java.util.Map;

public interface UserService {
	public User findUserById(Integer id);
	public List<User> findUsersByFilter(Map<String, Object> filter);
	public Long countUsers();
	public User create(User user);
	public User update(User user);
}
