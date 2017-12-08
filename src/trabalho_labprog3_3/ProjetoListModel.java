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
public class ProjetoListModel implements ListModel<Projeto> {

    private final List<Projeto> projetos;
    private final List<ListDataListener> dataListener;

    public ProjetoListModel(List<Projeto> projetos) {
        this.projetos = projetos;
        this.dataListener = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return projetos.size();
    }

    @Override
    public Projeto getElementAt(int index) {
        return projetos.get(index);
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
