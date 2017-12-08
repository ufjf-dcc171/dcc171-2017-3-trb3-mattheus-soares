/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Mattheus
 */
public class PessoaListModel implements ListModel<Pessoa> {

    private final List<Pessoa> pessoas;
    private final List<ListDataListener> dataListener;

    public PessoaListModel(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        this.dataListener = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return pessoas.size();
    }

    @Override
    public Pessoa getElementAt(int index) {
        return pessoas.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListener.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListener.remove(l);
    }

}
