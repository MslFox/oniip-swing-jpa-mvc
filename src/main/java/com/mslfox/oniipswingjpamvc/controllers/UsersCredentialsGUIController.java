package com.mslfox.oniipswingjpamvc.controllers;

import com.mslfox.oniipswingjpamvc.model.UserCredentialDTO;
import com.mslfox.oniipswingjpamvc.model.UserServiceImpl;
import com.mslfox.oniipswingjpamvc.view.UsersCredentialsView;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UsersCredentialsGUIController {
    private final UserServiceImpl userService;
    private final UsersCredentialsView usersView;


    public void start()  {

        usersView.setUsersCredentialsList(userService.getAllUsers());
        usersView.setVisible(true);
        usersView.uploadTableData();

        usersView.getUpdateButton().addActionListener(e -> {
            List<UserCredentialDTO> selectedUsers = usersView.getSelectedUsers();
            if (selectedUsers.size() == 1) {
                var selectedUser = selectedUsers.get(0);
                UserCredentialDTO changedUser;
                try {
                    changedUser = usersView.showEditUserDialog("Edit user", selectedUser);
                    if (changedUser == null) return;
                    var updatedUser = userService.updateUser(changedUser);
                    selectedUser.setLogin(updatedUser.getLogin());
                    selectedUser.setPassword(updatedUser.getPassword());
                    selectedUser.setEmail(updatedUser.getEmail());
                    selectedUser.setIp(updatedUser.getIp());
                    selectedUser.setRole(updatedUser.getRole());
                } catch (Exception ex) {
                    usersView.showMessage(ex.getMessage(), "Updating error", JOptionPane.ERROR_MESSAGE);
                }
                usersView.uploadTableData();
            } else {
                usersView.showMessage("Select one row of user credentials", "Updating error", JOptionPane.ERROR_MESSAGE);
            }
        });
        usersView.getCreateButton().addActionListener(e -> {
            UserCredentialDTO newUser;
            try {
                newUser = usersView.showEditUserDialog("Create user", new UserCredentialDTO());
                if (newUser == null) return;
                userService.saveUser(newUser);
                usersView.setUsersCredentialsList(userService.getAllUsers());
                usersView.uploadTableData();
            } catch (ConstraintViolationException ex) {
                Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
                if (!violations.isEmpty()) {
                    ConstraintViolation<?> firstViolation = violations.iterator().next();
                    String templateMessage = firstViolation.getMessageTemplate();
                    usersView.showMessage(templateMessage, "Creating error", JOptionPane.ERROR_MESSAGE);
                } else {
                    usersView.showMessage(ex.getLocalizedMessage(), "Creating error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                usersView.showMessage(ex.getMessage(), "Creating error", JOptionPane.ERROR_MESSAGE);
            }
        });
        usersView.getDeleteButton().addActionListener(e -> {
            List<UserCredentialDTO> selectedUsers = usersView.getSelectedUsers();
            try {
                userService.deleteUsers(selectedUsers);
                usersView.setUsersCredentialsList(userService.getAllUsers());
                usersView.uploadTableData();
            } catch (Exception ex) {
                usersView.showMessage(ex.getMessage(), "Deleting error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
