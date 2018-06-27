/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.service.impl;

import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.entity.UsersRoles;
import com.mycompany.carrental.repository.UsersRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bolek
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService  {

    //pobieranie użytkownika z bazy danych przez Hibernate
    @Autowired
    UsersRepository usersRepository;
    
    @Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException {
                
		Users user = usersRepository.findOneByUserNickname(username);
                
		List<GrantedAuthority> authorities =
                                      buildUserAuthority(user.getUsersRoleses());
		return buildUserForAuthentication(user, authorities);
                
	}
        // Konwersja wlasnej klasy Users na 
	// wersje spring security 
	private User buildUserForAuthentication(Users user,
		List<GrantedAuthority> authorities) {
            System.out.println("Moje hasło: " + user.getPassword());
		return new User(user.getNickname(), user.getPassword(),
			true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UsersRoles> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Tworzenie listy uprawnien użytkownika
		for (UsersRoles userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}
