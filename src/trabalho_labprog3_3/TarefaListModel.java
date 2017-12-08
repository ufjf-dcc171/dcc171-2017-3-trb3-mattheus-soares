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
public class TarefaListModel implements ListModel<Tarefa> {

    private final List<Tarefa> tarefas;
    private final List<ListDataListener> dataListener;

    public TarefaListModel(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
        this.dataListener = new ArrayList<>();
    }


    @Override
    public int getSize() {
        return tarefas.size();
    }

    @Override
    public Tarefa getElementAt(int index) {
        return tarefas.get(index);
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
