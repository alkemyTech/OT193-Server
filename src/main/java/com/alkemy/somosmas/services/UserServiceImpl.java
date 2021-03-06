package com.alkemy.somosmas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.LoginUserDTO;
import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.enums.RoleEnum;
import com.alkemy.somosmas.exceptions.InvalidUserException;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	EmailService emailService;

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
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));//encripto contrase??a
			newUser.setRole(new Role(RoleEnum.ROLE_USER.getId()));
			this.userRepository.save(newUser);
			this.emailService.sendWelcomeEmailTemplateTo(userDTO.getEmail(), "??Estimado/a "
			+ userDTO.getFirstName() + " te damos la bienvenida a somos mas!" , " Gracias por ser parte."
			,"Mail de bienvenida: somos mas");

			return userDTO;
		}else{
			throw new Exception("MAIL EXISTENTE, ELIJA OTRO POR FAVOR.");
		}
	}


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

	@Override
	public UserDTO update(Long id, UserDTO dto) throws ModelNotFoundException {
		User userEntity = this.userRepository.findById(id).orElse(null);
		if (userEntity == null) {
			throw new ModelNotFoundException(id,"User");
		}
		this.userMapper.userEntityRefreshValues(userEntity, dto);
		User userEntityModified = this.userRepository.save(userEntity);
		UserDTO result = this.userMapper.originalToDTO(userEntityModified);
		
		return result;
	}

	private void injectUserInSecurityContext(String email, String password) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public LoginUserDTO authUser(String email, String password) throws InvalidUserException {
		if(!existsByEmail(email)) {
			throw new InvalidUserException("Email does not exist");
		}
		User user = findByEmail(email);
		if(!passwordEncoder.matches(password,user.getPassword())) {
			throw new InvalidUserException("Incorrect email or password");
		}
		LoginUserDTO loginUserDTO = userToDTO(user);
		injectUserInSecurityContext(email, password);
		return loginUserDTO;
	}


	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public LoginUserDTO userToDTO(User user) {
		return userMapper.userToDTO(user);
	}
	
}
