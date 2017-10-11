package Ferramentas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private final Class classes[] = new Class[]{Integer.class, String.class, String.class, Double.class};
    private final String colunas[] = new String[]{"Id do Serviço", "Nome", "Data do Serviço", "Valor do Serviço"};
    private List<EntidadeTable> dados;
    private final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
    public boolean mudou = false;

    public TableModel(List<EntidadeTable> dados) {
        this.dados = dados;
    }

    public void setDados(List<EntidadeTable> dados) {
        this.dados = dados;
    }

    public List<EntidadeTable> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        EntidadeTable s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getId_servico();
            case 1:
                return s.getNome_servico();
            case 2:
                return s.getData_servico();
            case 3:
                return s.getValor();
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        mudou = true;
        EntidadeTable s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                
                    s.setId_servico((int) aValue);
                
                break;
            case 1:
                s.setNome_servico((String) aValue);
                break;
            case 2:
                s.setData_servico((String) aValue);
                break;
            case 3:
                s.setValor((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");

        }
        fireTableDataChanged();
    }

    public EntidadeTable getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(EntidadeTable empregado) {
        return dados.indexOf(empregado);
    }

    public void onAdd(EntidadeTable empregado) {

        dados.add(empregado);
        fireTableRowsInserted(indexOf(empregado), indexOf(empregado));
        mudou = true;

    }

    public void onRemove(int[] rowIndex) {

        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
        mudou = true;

    }
}
