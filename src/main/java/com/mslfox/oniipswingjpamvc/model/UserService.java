package com.mslfox.oniipswingjpamvc.model;

import java.util.List;

public interface UserService<UserDTO> {
    List<UserDTO> getAllUsers();
    void deleteUsers(List<UserDTO> users);
    UserDTO updateUser(UserDTO updatedUser);
    void saveUser(UserDTO user);
}
