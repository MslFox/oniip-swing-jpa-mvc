package com.mslfox.oniipswingjpamvc.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCredentialMapper {
    UserCredential userCredentialDtoToNewUserCredential(UserCredentialDTO userCredentialDTO) {
        if (userCredentialDTO == null) {
            return null;
        }
        var user = new UserCredential();
        user.setLogin(userCredentialDTO.getLogin());
        user.setPassword(userCredentialDTO.getPassword());
        user.setEmail(userCredentialDTO.getEmail());
        user.setIp(userCredentialDTO.getIp());
        user.setRole(userCredentialDTO.getRole());
        return user;
    }

    UserCredentialDTO userCredentialToUserCredentialDTO(UserCredential userCredential) {
        if (userCredential == null) {
            return null;
        }
        return new UserCredentialDTO(
                userCredential.getId(),
                userCredential.getLogin(),
                userCredential.getPassword(),
                userCredential.getEmail(),
                userCredential.getIp(),
                userCredential.getCreationDate(),
                userCredential.getEditDate(),
                userCredential.getRole());
    }

    List<UserCredentialDTO> userCredentialListToUserCredentialDTOList(List<UserCredential> list) {
        if (list == null) {
            return null;
        }
        List<UserCredentialDTO> list1 = new ArrayList<>(list.size());
        for (UserCredential userCredential : list) {
            list1.add(userCredentialToUserCredentialDTO(userCredential));
        }
        return list1;
    }

    UserCredential updateUserCredentialFromDto(UserCredentialDTO userCredentialDTO, UserCredential userCredential) {
        if (userCredentialDTO == null) {
            return null;
        }
        userCredential.setLogin(userCredentialDTO.getLogin());
        userCredential.setPassword(userCredentialDTO.getPassword());
        userCredential.setEmail(userCredentialDTO.getEmail());
        userCredential.setIp(userCredentialDTO.getIp());
        userCredential.setEditDate(userCredentialDTO.getEditDate());
        userCredential.setRole(userCredentialDTO.getRole());
        return userCredential;
    }
}
