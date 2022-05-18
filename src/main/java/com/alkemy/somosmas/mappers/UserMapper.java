package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDTO originalToDTO(User user) {
        UserDTO dtoUser = new UserDTO();
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setPhoto(user.getPhoto());
        return dtoUser;
    }

    public List<UserDTO> modelUserToUserDTO(List<User> usersList){
        List<UserDTO> dtoList = usersList
                .stream()
                .map(i->this.originalToDTO(i))
                .collect(Collectors.toList());
        return dtoList;
    }
}
