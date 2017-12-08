/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.util.List;

/**
 *
 * @author Mattheus
 */
public interface ProjetoDAO {
    public void criar(Projeto projeto) throws Exception;
    public List<Projeto> listar() throws Exception;
    public Projeto selecionar(Integer id) throws Exception;
}
