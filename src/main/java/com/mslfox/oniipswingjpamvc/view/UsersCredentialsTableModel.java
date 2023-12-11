package com.mslfox.oniipswingjpamvc.view;

import com.mslfox.oniipswingjpamvc.model.UserCredentialDTO;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class UsersCredentialsTableModel extends AbstractTableModel {
    @Getter
    @Setter
    private List<UserCredentialDTO> usersCredentialsList;
    private final TableModelColumns[] columnValues;

    public UsersCredentialsTableModel(TableModelColumns[] columns) {
        this.columnValues = columns;
        this.usersCredentialsList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return usersCredentialsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnValues.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var user = usersCredentialsList.get(rowIndex);
        if (columnIndex < columnValues.length && columnIndex >= 0) {
            return columnValues[columnIndex].getCommand().execute(user);
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex < columnValues.length && columnIndex >= 0) {
            return columnValues[columnIndex].toString();
        }
        return null;
    }

    public UserCredentialDTO getSelectedRow(int rowIndex) {
        return usersCredentialsList.get(rowIndex);
    }
}