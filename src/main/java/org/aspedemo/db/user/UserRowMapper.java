package org.aspedemo.db.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserName(rs.getString("user_name"));
		user.setRealName(rs.getString("real_name"));
		user.setEmail(rs.getString("email"));
		user.setLastLogin(rs.getDate("last_login"));
		if (user.getPasswordHash() != null) {
			// don't expose this
			user.setPasswordHash("a password has been set");
		}
		user.setActive(rs.getBoolean("is_active"));
		return user;
	}
	
}
