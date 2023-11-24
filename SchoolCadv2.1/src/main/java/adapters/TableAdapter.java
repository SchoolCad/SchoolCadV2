package adapters;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableAdapter extends DefaultTableModel {

    public TableAdapter(String[] columnNames, Object[] data) {
        super(columnNames, 0);
        for (Object row : data)
            addRow(row);
    }

    // Método para adicionar uma nova linha à tabela
    public void addRow(Object rowData) {
        super.addRow((Object[]) rowData);
    }

    // Método para remover uma linha da tabela
    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
