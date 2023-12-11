package com.mslfox.oniipswingjpamvc.view;

import com.mslfox.oniipswingjpamvc.model.Roles;
import com.mslfox.oniipswingjpamvc.model.UserCredentialDTO;
import com.mslfox.oniipswingjpamvc.utility.IPv4Validator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mslfox.oniipswingjpamvc.utility.DateTimeFormatter.formatDateTime;
import static com.mslfox.oniipswingjpamvc.view.TableModelColumns.*;
import static com.mslfox.oniipswingjpamvc.view.ViewConstants.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class UsersCredentialsView extends JFrame {
    private UsersCredentialsTableModel userTableModel;
    private JTable userTable;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;

    public UsersCredentialsView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("User Manager");
        setBounds(FRAME_X_POS, FRAME_Y_POS, FRAME_WIGHT, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createButton = new JButton(CREATE_BUTTON_NAME);
        updateButton = new JButton(UPDATE_BUTTON_NAME);
        deleteButton = new JButton(DELETE_BUTTON_NAME);
        inputPanel.add(createButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.NORTH);
        userTableModel = new UsersCredentialsTableModel(TableModelColumns.values());
        userTable = new JTable(userTableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        userTable.setFillsViewportHeight(true);
        userTable.setPreferredScrollableViewportSize(new Dimension(userTable.getPreferredScrollableViewportSize().width, getHeight() - 100));
    }

    public List<UserCredentialDTO> getSelectedUsers() {
        int[] selectedRows = userTable.getSelectedRows();
        List<UserCredentialDTO> selectedUsers = new ArrayList<>();
        for (int rowIndex : selectedRows) {
            var user = userTableModel.getSelectedRow(rowIndex);
            selectedUsers.add(user);
        }
        return selectedUsers;
    }

    public void showMessage(String message, String title, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, title, typeMessage);
    }

    public void setUsersCredentialsList(List<UserCredentialDTO> usersCredentialsList) {
        userTableModel.setUsersCredentialsList(usersCredentialsList);
    }

    public void uploadTableData() {
        userTableModel.fireTableDataChanged();
    }

    public UserCredentialDTO showEditUserDialog(String title, UserCredentialDTO user) throws ParseException {
        JTextField idField = new JTextField(user.getId() < 0 ? NO_VALUE : String.valueOf(user.getId()));
        idField.setEditable(false);
        JTextField loginField = new JTextField(user.getLogin());
        JTextField passwordField = new JTextField(user.getPassword());
        JTextField emailField = new JTextField(user.getEmail());

        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField[] ipFields = new JTextField[4];
        String[] userIpArr = new String[4];
        String userIpString = user.getIp();
        if (userIpString != null && !userIpString.isEmpty() && IPv4Validator.isValidIPv4(userIpString)) {
            userIpArr = userIpString.split("\\.");
        }
        for (int i = 0; i < ipFields.length; i++) {
            MaskFormatter formatter = new MaskFormatter("###");
            formatter.setPlaceholderCharacter('0');
            ipFields[i] = new JFormattedTextField(formatter);
            ipFields[i].setHorizontalAlignment(JTextField.CENTER);
            ipFields[i].setBackground(Color.WHITE);
            ipFields[i].setOpaque(true);
            ipFields[i].setColumns(3);
            ipFields[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            if (userIpArr[i] != null && !userIpArr[i].isEmpty()) {
                try {
                    ipFields[i].setText(String.format("%03d", Integer.parseInt(userIpArr[i])));
                } catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
            ipPanel.add(ipFields[i]);
            if (i < ipFields.length - 1) {
                JLabel dotLabel = new JLabel(".");
                ipPanel.add(dotLabel);
            }
        }
        JTextField creationDateField = new JTextField(user.getCreationDate() == null ? NO_VALUE : formatDateTime(user.getCreationDate()));
        creationDateField.setEditable(false);
        JTextField editDateField = new JTextField(user.getCreationDate() == null ? NO_VALUE : formatDateTime(user.getEditDate()));
        editDateField.setEditable(false);
        JComboBox<Roles> roleComboBox = new JComboBox<>(Roles.values());
        roleComboBox.setSelectedItem(user.getRole());

        Object[] fields = {
                LOGIN.toString(), loginField,
                PASSWORD.toString(), passwordField,
                EMAIL.toString(), emailField,
                IP.toString(), ipPanel,
                ACCESS_LEVEL.toString(), roleComboBox,
                ID.toString(), idField,
                CREATION_DATE.toString(), creationDateField,
                EDIT_DATE.toString(), editDateField,
        };
        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (option == JOptionPane.OK_OPTION) {
            return UserCredentialDTO.builder()
                    .id(user.getId())
                    .login(loginField.getText())
                    .password(passwordField.getText())
                    .email(emailField.getText())
                    .ip(Arrays.stream(ipFields)
                            .map(JTextComponent::getText)
                            .map(Integer::parseInt)
                            .map(String::valueOf)
                            .collect(Collectors.joining(".")))
                    .creationDate(user.getCreationDate())
                    .editDate(user.getEditDate())
                    .role((Roles) roleComboBox.getSelectedItem())
                    .build();
        }
        return null;
    }
}
