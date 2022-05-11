package com.alkemy.somosmas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import com.alkemy.somosmas.services.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	UserRepository userRepository;

	/*@Override
	public User save(User dto) {
		User user = userMapper.userDTO2Entity(dto);
		User userSave = userRepository.save(user);
		UserDTO result = userMapper.entityToUserDTO(userSave);
		return result;
	}*/

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return null;
	}

/*
 * 	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        java.util.Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), grantedAuthorities);
    }*/
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}


}
