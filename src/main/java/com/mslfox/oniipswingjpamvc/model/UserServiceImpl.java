package com.mslfox.oniipswingjpamvc.model;


import com.mslfox.oniipswingjpamvc.model.repositories.UserCredentialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserCredentialDTO> {
    private final UserCredentialRepository userRepository;
    private final UserCredentialMapper mapper;
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with id: ";


    @Override
    public List<UserCredentialDTO> getAllUsers() {
        return mapper.userCredentialListToUserCredentialDTOList(userRepository.findAll());
    }

    @Override
    @Transactional
    public void deleteUsers(List<UserCredentialDTO> userCredentialDTOs) {
        userCredentialDTOs.forEach(userCredentialDTO -> {
            if (userRepository.existsById(userCredentialDTO.getId())) {
                userRepository.deleteById(userCredentialDTO.getId());
            } else {
                throw new NoSuchElementException(USER_NOT_FOUND_MESSAGE + userCredentialDTO.getId());
            }
        });
    }


    @Override
    @Transactional
    public UserCredentialDTO updateUser(UserCredentialDTO changedUser) {
        changedUser.setEditDate(LocalDateTime.now());
        var userCredential = userRepository.findById(changedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE + changedUser.getId()));
        var updatedUser = userRepository.save(mapper.updateUserCredentialFromDto(changedUser, userCredential));
        return mapper.userCredentialToUserCredentialDTO(updatedUser);
    }

    @Override
    public void saveUser(UserCredentialDTO userCredentialDTO) {
        userRepository.save(mapper.userCredentialDtoToNewUserCredential(userCredentialDTO));
    }
}
