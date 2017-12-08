/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mattheus
 */
public class TarefaDAOJDBC implements TarefaDAO {

    private Connection conexao;
    private PreparedStatement operacaoInserir;
    private PreparedStatement operacaoListar;
    private PreparedStatement operacaoListarConcluidas;
    private PreparedStatement operacaoListarPorFazer;
    private PreparedStatement operacaoListarIniciaveisQ1;
    private PreparedStatement operacaoListarIniciaveisQ2;

    private PreparedStatement operacaoSelecionar;

    private PreparedStatement operacaoInserirPessoa;

    private PreparedStatement operacaoListarPessoaQ1;
    private PreparedStatement operacaoListarPessoaQ2;

    private PreparedStatement operacaoListarTarefaRequisito;
    private PreparedStatement operacaoInserirTarefaRequisito;

    private PreparedStatement operacaoAtualizarDescricao;
    private PreparedStatement operacaoAtualizarDuracaoEsperada;
    private PreparedStatement operacaoAtualizarPercentual;
    private PreparedStatement operacaoAtualizarDataFim;
    private PreparedStatement operacaoAtualizarStatus;

    public TarefaDAOJDBC() throws Exception {
        conexao = ConexaoJavaDB.getConnection();
        operacaoInserir = conexao.prepareStatement("INSERT INTO tarefas(descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto) VALUES(?,?,?,CURRENT_TIMESTAMP,NULL,1,?)");
        operacaoListar = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE id_projeto = ?");
        operacaoListarConcluidas = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE status = 2 AND id_projeto = ?");
        operacaoListarPorFazer = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE status = 1 AND id_projeto = ?");

        operacaoListarIniciaveisQ1 = conexao.prepareStatement("SELECT id_tarefarequisito FROM tarefas_requisitos WHERE id_tarefaatual = ?");
        operacaoListarIniciaveisQ2 = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE status = 2 AND id = ? AND id_projeto = ?");

        operacaoSelecionar = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE id = ?");
        operacaoInserirPessoa = conexao.prepareStatement("INSERT INTO pessoas_tarefas(id_pessoa,id_tarefa) VALUES(?,?)");

        operacaoListarPessoaQ1 = conexao.prepareStatement("SELECT id_pessoa FROM pessoas_tarefas WHERE id_tarefa = ?");
        operacaoListarPessoaQ2 = conexao.prepareStatement("SELECT nome FROM pessoas WHERE id = ?");

        operacaoListarTarefaRequisito = conexao.prepareStatement("SELECT id,descricao,duracao_esperada,percentual,data_inicio,data_fim,status,id_projeto FROM tarefas WHERE id_projeto = ? AND NOT id = ?");
        operacaoInserirTarefaRequisito = conexao.prepareStatement("INSERT INTO tarefas_requisitos(id_tarefaatual,id_tarefarequisito) VALUES(?,?)");

        operacaoAtualizarDescricao = conexao.prepareStatement("UPDATE tarefas SET descricao = ? WHERE id = ?");
        operacaoAtualizarDuracaoEsperada = conexao.prepareStatement("UPDATE tarefas SET duracao_esperada = ? WHERE id = ?");
        operacaoAtualizarPercentual = conexao.prepareStatement("UPDATE tarefas SET percentual = ? WHERE id = ?");
        operacaoAtualizarDataFim = conexao.prepareStatement("UPDATE tarefas SET data_fim = CURRENT_TIMESTAMP WHERE id = ?");
        operacaoAtualizarStatus = conexao.prepareStatement("UPDATE tarefas SET status = ? WHERE id = ?");

    }

    @Override
    public void criarTarefa(Tarefa tarefa, Integer id_projeto) throws Exception {
        operacaoInserir.clearParameters();
        operacaoInserir.setString(1, tarefa.getDescricao());
        operacaoInserir.setInt(2, tarefa.getDuracao_esperada());
        operacaoInserir.setDouble(3, tarefa.getPercentual());
        operacaoInserir.setInt(4, id_projeto);
        operacaoInserir.executeUpdate();

    }

    @Override
    public List<Tarefa> listarTarefasProjeto(Integer id_projeto) throws Exception {
        List<Tarefa> tarefa = new ArrayList<>();
        operacaoListar.clearParameters();
        operacaoListar.setInt(1, id_projeto);
        ResultSet resultado = operacaoListar.executeQuery();
        while (resultado.next()) {
            Tarefa t = new Tarefa();
            t.setId(resultado.getInt("id"));
            t.setDescricao(resultado.getString("descricao"));
            t.setDuracao_esperada(resultado.getInt("duracao_esperada"));
            t.setPercentual(Double.parseDouble(resultado.getString("percentual")));
            t.setData_inicio(resultado.getDate("data_inicio"));
            t.setData_final(resultado.getDate("data_fim"));
            t.setStatus(resultado.getInt("status"));
            t.setID_Projeto(id_projeto);
            tarefa.add(t);
        }
        return tarefa;
    }

    @Override
    public List<Tarefa> listarTarefasConcluidas(Integer id_projeto) throws Exception {
        List<Tarefa> tarefa = new ArrayList<>();
        operacaoListarConcluidas.clearParameters();
        operacaoListarConcluidas.setInt(1, id_projeto);
        ResultSet resultado = operacaoListarConcluidas.executeQuery();
        while (resultado.next()) {
            Tarefa t = new Tarefa();
            t.setId(resultado.getInt("id"));
            t.setDescricao(resultado.getString("descricao"));
            t.setDuracao_esperada(resultado.getInt("duracao_esperada"));
            t.setPercentual(Double.parseDouble(resultado.getString("percentual")));
            t.setData_inicio(resultado.getDate("data_inicio"));
            t.setData_final(resultado.getDate("data_fim"));
            t.setStatus(resultado.getInt("status"));
            t.setID_Projeto(id_projeto);
            tarefa.add(t);
        }
        return tarefa;
    }

    @Override
    public List<Tarefa> listarTarefasPorFazer(Integer id_projeto) throws Exception {
        List<Tarefa> tarefa = new ArrayList<>();
        operacaoListarPorFazer.clearParameters();
        operacaoListarPorFazer.setInt(1, id_projeto);
        ResultSet resultado = operacaoListarPorFazer.executeQuery();
        while (resultado.next()) {
            Tarefa t = new Tarefa();
            t.setId(resultado.getInt("id"));
            t.setDescricao(resultado.getString("descricao"));
            t.setDuracao_esperada(resultado.getInt("duracao_esperada"));
            t.setPercentual(Double.parseDouble(resultado.getString("percentual")));
            t.setData_inicio(resultado.getDate("data_inicio"));
            t.setData_final(resultado.getDate("data_fim"));
            t.setStatus(resultado.getInt("status"));
            t.setID_Projeto(id_projeto);
            tarefa.add(t);
        }
        return tarefa;
    }

    @Override
    public List<Tarefa> listarTarefasIniciaveis(Integer id_projeto, Integer id_tarefa_atual) throws Exception {
        List<Tarefa> tarefa = new ArrayList<>();
        operacaoListarIniciaveisQ1.clearParameters();
        operacaoListarIniciaveisQ1.setInt(1, id_tarefa_atual);
        ResultSet resultado = operacaoListarIniciaveisQ1.executeQuery();
        List<Integer> ids = null;
        List<ResultSet> r = null;
        while (resultado.next()) {
            ids.add(resultado.getInt("id_tarefarequisito"));
        }
        for (int k = 0; k < ids.size(); k++) {
            operacaoListarIniciaveisQ2.clearParameters();
            operacaoListarIniciaveisQ2.setInt(1, ids.get(k));
            operacaoListarIniciaveisQ2.setInt(2, id_projeto);

            ResultSet resultado2 = operacaoListarIniciaveisQ2.executeQuery();
            r.add(resultado2);
        }

        for (int k = 0; k < ids.size(); k++) {

            Tarefa t = new Tarefa();
            t.setId(r.get(k).getInt("id"));
            t.setDescricao(r.get(k).getString("descricao"));
            t.setDuracao_esperada(r.get(k).getInt("duracao_esperada"));
            t.setPercentual(Double.parseDouble(r.get(k).getString("percentual")));
            t.setData_inicio(r.get(k).getDate("data_inicio"));
            t.setData_final(r.get(k).getDate("data_fim"));
            t.setStatus(r.get(k).getInt("status"));
            t.setID_Projeto(id_projeto);
            tarefa.add(t);

        }

        return tarefa;
    }

    @Override
    public Tarefa selecionarTarefa(Integer id) throws Exception {
        Tarefa tarefa = null;
        operacaoSelecionar.clearParameters();
        operacaoSelecionar.setInt(1, id);
        ResultSet resultado = operacaoSelecionar.executeQuery();

        if (resultado.next()) {
            tarefa = new Tarefa();
            tarefa.setId(resultado.getInt("id"));
            tarefa.setDescricao(resultado.getString("descricao"));
            tarefa.setDuracao_esperada(resultado.getInt("duracao_esperada"));
            tarefa.setPercentual(Double.parseDouble(resultado.getString("percentual")));
            tarefa.setData_inicio(resultado.getDate("data_inicio"));
            tarefa.setData_final(resultado.getDate("data_fim"));
            tarefa.setStatus(resultado.getInt("status"));
            tarefa.setID_Projeto(resultado.getInt("id_projeto"));

        }
        return tarefa;
    }

    @Override
    public void cadastrarPessoa(Integer tarefa, Integer pessoa) throws Exception {
        operacaoInserirPessoa.clearParameters();
        operacaoInserirPessoa.setInt(1, pessoa);
        operacaoInserirPessoa.setInt(2, tarefa);
        operacaoInserirPessoa.executeUpdate();
    }

    @Override
    public List<Pessoa> listarPessoaTarefa(Integer id_tarefa) throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();
        operacaoListarPessoaQ1.clearParameters();
        operacaoListarPessoaQ1.setInt(1, id_tarefa);
        ResultSet resultado = operacaoListarPessoaQ1.executeQuery();

        List<Integer> ids = new ArrayList<>();

        List<ResultSet> r = new ArrayList<>();

        while (resultado.next()) {
            ids.add(resultado.getInt("id_pessoa"));
        }
        if (!ids.isEmpty()) {
            for (int k = 0; k < ids.size(); k++) {
                operacaoListarPessoaQ2.clearParameters();
                operacaoListarPessoaQ2.setInt(1, ids.get(k));
                ResultSet resultado2 = operacaoListarIniciaveisQ2.executeQuery();
                r.add(resultado2);
            }
            for (int k = 0; k < ids.size(); k++) {
                Pessoa p = new Pessoa();
                p.setId(r.get(k).getInt("id"));
                p.setNome(r.get(k).getString("nome"));
                pessoas.add(p);
            }
        }

        return pessoas;
    }

    @Override
    public List<Tarefa> listarTarefasRequisito(Integer id_projeto, Integer id_tarefa) throws Exception {
        List<Tarefa> tarefa = new ArrayList<>();
        operacaoListarTarefaRequisito.clearParameters();
        operacaoListarTarefaRequisito.setInt(1, id_projeto);
        operacaoListarTarefaRequisito.setInt(2, id_tarefa);
        ResultSet resultado = operacaoListarTarefaRequisito.executeQuery();
        while (resultado.next()) {
            Tarefa t = new Tarefa();
            t.setId(resultado.getInt("id"));
            t.setDescricao(resultado.getString("descricao"));
            t.setDuracao_esperada(resultado.getInt("duracao_esperada"));
            t.setPercentual(Double.parseDouble(resultado.getString("percentual")));
            t.setData_inicio(resultado.getDate("data_inicio"));
            t.setData_final(resultado.getDate("data_fim"));
            t.setStatus(resultado.getInt("status"));
            t.setID_Projeto(id_projeto);
            tarefa.add(t);
        }
        return tarefa;
    }

    @Override
    public void cadastrarTarefaRequisito(Integer id_tarefa_atual, Integer id_tarefa_requisito) throws Exception {
        operacaoInserirTarefaRequisito.clearParameters();
        operacaoInserirTarefaRequisito.setInt(1, id_tarefa_atual);
        operacaoInserirTarefaRequisito.setInt(2, id_tarefa_requisito);
        operacaoInserirTarefaRequisito.executeUpdate();

    }

    @Override
    public void atualizarTarefaDescricao(Integer id_tarefa, String descricao) throws Exception {
        operacaoAtualizarDescricao.clearParameters();
        operacaoAtualizarDescricao.setString(1, descricao);
        operacaoAtualizarDescricao.setInt(2, id_tarefa);
        operacaoAtualizarDescricao.executeUpdate();
    }

    @Override
    public void atualizarTarefaDuracaoEsperada(Integer id_tarefa, Integer duracao_esperada) throws Exception {
        operacaoAtualizarDuracaoEsperada.clearParameters();
        operacaoAtualizarDuracaoEsperada.setInt(1, duracao_esperada);
        operacaoAtualizarDuracaoEsperada.setInt(2, id_tarefa);
        operacaoAtualizarDuracaoEsperada.executeUpdate();
    }

    @Override
    public void atualizarTarefaPercentual(Integer id_tarefa, Double percentual) throws Exception {
        operacaoAtualizarPercentual.clearParameters();
        operacaoAtualizarPercentual.setDouble(1, percentual);
        operacaoAtualizarPercentual.setInt(2, id_tarefa);
        operacaoAtualizarPercentual.clearParameters();
    }

    @Override
    public void atualizarTarefaDataFim(Integer id_tarefa) throws Exception {
        operacaoAtualizarDataFim.clearParameters();
        operacaoAtualizarDataFim.setInt(1, id_tarefa);
        operacaoAtualizarDataFim.clearParameters();
    }

    @Override
    public void atualizarTarefaStatus(Integer id_tarefa, Integer status) throws Exception {
        operacaoAtualizarStatus.clearParameters();
        operacaoAtualizarStatus.setInt(1, status);
        operacaoAtualizarStatus.setInt(2, id_tarefa);
        operacaoAtualizarStatus.clearParameters();
    }

}
