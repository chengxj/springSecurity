package com.springSecurity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.springSecurity.dao.UserDao;
import com.springSecurity.domain.DbUser;

public class CustomUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger.getLogger("CustomUserDetailsService");
	
	private UserDao userDAO = new UserDao();

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserDetails user = null;
		try {
			DbUser dbUser = userDAO.getDatabase(username);
			user = new User(dbUser.getUsername(), dbUser.getPassword().toLowerCase(), true, true, true, true, getAuthorities(dbUser.getAccess()));
		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return user;
	}

	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (access.compareTo(1) == 0) {
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList;
	}
	
}