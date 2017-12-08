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
public interface PessoaDAO {
    public void criar(Pessoa pessoa) throws Exception;
    public List<Pessoa> listar() throws Exception;
    public Pessoa selecionar(Integer id) throws Exception;

}
