package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService ,UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return null;
	}

	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public UserDTO getUser(Long id) {
		if(this.userRepository.existsById(id)){
			User userNew = this.userRepository.getById(id);
			UserDTO userDTO = userMapper.originalToDTO(userNew);
			return userDTO;
		}
		else{
			return null;
		}
	}
	 /*public hola(Long id){//PRUEBA
		if(exists(id)){
			User userNew = this.userRepository.getById(id);
			UserDTO userDTO = userMapper.originalToDTO(userNew);
			return userDTO;
		}
		else{
			return null;
		}
	 }*/

	@Override
	public List<UserDTO> getUsersList() {
		List<User> model = userRepository.findAll();
		List<UserDTO> dtoList = userMapper.modelUserToUserDTO(model);
		return dtoList;
	}

	@Override
	public UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception {
		Boolean mailExists = this.userRepository.existsByEmail(userDTO.getEmail());
		if(!mailExists){
			User newUser = userMapper.dto2Model(userDTO);
			this.userRepository.save(newUser);
			return userDTO;
		}else{
			throw new Exception("MAIL EXISTENTE, ELIJA OTRO POR FAVOR.");
		}
	}

	/*@Override //PRUEBA
	public Long getUserIdByMail(String email) {
		if(this.userRepository.existsByEmail(email)){
			User user = this.userRepository.findByEmail(email);
			Long result = user.getIdUser();
			return result;
		}else{
			return null;
		}
	}*/

	@Override
	public Boolean deleteUser(Long id) {
		if(this.userRepository.existsById(id)){
			User user = this.userRepository.getById(id);
			user.setDeleted(Boolean.TRUE);
			this.userRepository.save(user);
			return true;
		}else{
			return false;
		}
	}




}
