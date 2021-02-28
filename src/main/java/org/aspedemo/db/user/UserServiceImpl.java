package org.aspedemo.db.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspedemo.exception.AspeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.CaseFormat;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbc;
	
	private UserRowMapper rowMapper = new UserRowMapper();
	
	@Override
	public User findUserById(Integer id) {
		String query = "SELECT * FROM user WHERE id = ?";
		try {
			return jdbcTemplate.queryForObject(query, rowMapper, new Object[]{id});
		}
		catch (EmptyResultDataAccessException e) {
			throw new AspeException(404, "Unknown user ID: " + id, "User not found");
		}
	}
	
	@Override
	public Long countUsers() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Long.class);
	}
	
	@Override
	public List<User> findUsersByFilter(Map<String, Object> filter) {
		StringBuilder     query  = new StringBuilder("SELECT * FROM user WHERE 1=1 ");
		ArrayList<Object> params = new ArrayList<>();
		
		for (Entry<String, Object> criteria : filter.entrySet()) {
			String name  = criteria.getKey();
			Object value = criteria.getValue();
			
			// camelCase ==> snake_case
			name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
			
			// exact match
			if ("id".equals(name) || "email".equals(name)) {
				query.append(" AND " + name + " = ? ");
				params.add(value);
			}
			
			// like %...%
			else if ("user_name".equals(name) || "real_name".equals(name)) {
				query.append(" AND " + name + " LIKE ? ");
				params.add("%" + value + "%");
			}
			
			// last login range
			else if ("last_login_min".equals(name)) {
				query.append(" AND last_login >= ? ");
				params.add(value);
			}
			else if ("last_login_max".equals(name)) {
				query.append(" AND last_login <= ? ");
				params.add(value);
			}
			
			// active
			else if ("active".equals(name)) {
				if ("active".equals(value)) {
					query.append(" AND is_active IS TRUE ");
				}
				else if ("inactive".equals(value)) {
					query.append(" AND is_active IS FALSE ");
				}
			}
		}
		
		return jdbcTemplate.query(query.toString(), rowMapper, params.toArray());
	}
	
	@Override
	@Transactional
	public User create(User user) {
		if (user.getId() != null) {
			throw new AspeException(422, "id not allowed for user creation");
		}
		
		SqlParameterSource params = new MapSqlParameterSource(user.toMap());
		String[] names            = getFieldNames(params);
		String[] placeholders     = getPlaceholders(params);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int rows = namedJdbc.update(
			"INSERT INTO user (" + String.join(", ", names) + ") VALUES (" + String.join(", ", placeholders) + ")",
			params, keyHolder
		);
		
		if (1 == rows) {
			user.setId((Integer) keyHolder.getKey().intValue());
		}
		else {
			throw new AspeException(422, "user creation failed");
		}
		
		return user;
	}
	
	@Override
	@Transactional
	public User update(User user) {
		if (null == user.getId()) {
			throw new AspeException(422, "id must be present for an update");
		}
		
		SqlParameterSource params = new MapSqlParameterSource(user.toMap());
		String[] names            = getFieldNames(params);
		String[] placeholders     = getPlaceholders(params);
		
		// build up the query
		StringBuffer query = new StringBuffer("UPDATE user SET ");
		for (int i = 0; i < names.length; i++) {
			if (i > 0) {
				query.append(", ");
			}
			query.append(names[i] + " = " + placeholders[i]);
		}
		query.append(" WHERE id = :id");
		
		// execute the query
		int rows = namedJdbc.update(query.toString(), params);
		if (rows != 1) {
			throw new AspeException(422, "user update failed");
		}
		
		return user;
	}
	
	/**
	 * Returns all field names of the given sql parameters,
	 * converted to column names and sorted.
	 * 
	 * @param params SQL parameters.
	 * @return sorted column names.
	 */
	private String[] getFieldNames(SqlParameterSource params) {
		String[] names = params.getParameterNames();
		
		// convert entity names to column names
		names = Arrays.stream(names).map(
			name -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name)
		).toArray(String[]::new);
		
		Arrays.sort(names);
		
		return names;
	}
	
	/**
	 * Returns a list of named and sorted placeholders, produced by the given field names.
	 * 
	 * @param params SQL parameters.
	 * @return named and sorted placeholders
	 */
	private String[] getPlaceholders(SqlParameterSource params) {
		String[] names = params.getParameterNames();
		
		// someThing --> :someThing
		String[] placeholders = Arrays.stream(names).map(
			name -> ":" + name
		).toArray(String[]::new);
		
		Arrays.sort(placeholders);
		
		return placeholders;
	}
}
