/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Mattheus
 */
public interface TarefaDAO {

    public void criarTarefa(Tarefa tarefa,Integer id_projeto) throws Exception;
    public List<Tarefa> listarTarefasProjeto(Integer id_projeto) throws Exception;
    public List<Tarefa> listarTarefasConcluidas(Integer id_projeto) throws Exception;
    public List<Tarefa> listarTarefasPorFazer(Integer id_projeto) throws Exception;
    public List<Tarefa> listarTarefasIniciaveis(Integer id_projeto, Integer id_tarefa_atual) throws Exception;

    public Tarefa selecionarTarefa(Integer id) throws Exception;

    public void cadastrarPessoa(Integer tarefa, Integer pessoa) throws Exception;
    public List<Pessoa> listarPessoaTarefa(Integer id_tarefa) throws Exception;
    public List<Tarefa> listarTarefasPossivelRequisito(Integer id_projeto,Integer id_tarefa) throws Exception;
    public void cadastrarTarefaRequisito(Integer id_tarefa_atual,Integer id_tarefa_requisito) throws Exception;
    public void removerTarefaRequisito(Integer id_tarefa_atual,Integer id_tarefa_requisito) throws Exception;

    
    public boolean verificarConclusao(Integer id_projeto, Integer id_tarefa)throws Exception;
    public void concluirTarefa(Integer id_projeto, Integer id_tarefa)throws Exception;
    public List<Tarefa> listarTarefasNaoRequisito(Integer id_projeto,Integer id_tarefa) throws Exception;
    

    
    public void atualizarTarefaDescricao(Integer id_tarefa, String descricao)throws Exception;
    public void atualizarTarefaDuracaoEsperada(Integer id_tarefa, Integer duracao_esperada)throws Exception;
    public void atualizarTarefaPercentual(Integer id_tarefa, Double percentual)throws Exception;
    public void atualizarTarefaDataFim(Integer id_tarefa)throws Exception;
    public void atualizarTarefaStatus(Integer id_tarefa, Integer status)throws Exception;
}
