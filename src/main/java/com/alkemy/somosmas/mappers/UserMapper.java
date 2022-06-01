package com.alkemy.somosmas.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.LoginUserDTO;
import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.User;

@Component
public class UserMapper {
    public UserDTO originalToDTO(User user) {
        UserDTO dtoUser = new UserDTO();
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setEmail(user.getEmail());
        return dtoUser;
    }

    public User dto2Model(UserDTO userDTO){
        User newUser = new User();
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        return newUser;
    }

    public List<UserDTO> modelUserToUserDTO(List<User> usersList){
        List<UserDTO> dtoList = usersList
                .stream()
                .map(i->this.originalToDTO(i))
                .collect(Collectors.toList());
        return dtoList;
    }

	public void userEntityRefreshValues(User userEntity, UserDTO dto) {
		if (dto.getEmail() != null && dto.getEmail().isBlank()) {
			userEntity.setEmail(dto.getEmail());
		}
		if (dto.getFirstName() != null && !dto.getFirstName().isBlank()) {
			userEntity.setFirstName(dto.getFirstName());			
		}
		if (dto.getLastName() != null && !dto.getLastName().isBlank()) {
			userEntity.setLastName(dto.getLastName());
		}
		if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
			userEntity.setPassword(dto.getPassword());
		}
	}

	public LoginUserDTO userToDTO(User user) {
    	LoginUserDTO loginUserDTO = new LoginUserDTO();
    	loginUserDTO.setUsername(user.getEmail());
    	loginUserDTO.setPassword(user.getPassword());
    	return loginUserDTO;
    }
}
