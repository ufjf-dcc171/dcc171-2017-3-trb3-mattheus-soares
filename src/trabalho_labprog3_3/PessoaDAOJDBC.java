/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.sql.Connection;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mattheus
 */
public class PessoaDAOJDBC implements PessoaDAO {

    private Connection conexao;
    private PreparedStatement operacaoInserir;
    private String sqlListar;
    private Statement operacaoListar;
    private PreparedStatement operacaoSelecionar;

    public PessoaDAOJDBC() throws Exception {
        conexao = ConexaoJavaDB.getConnection();
        operacaoInserir = conexao.prepareStatement("INSERT INTO pessoas(nome) VALUES(?)");
        operacaoListar = conexao.createStatement();
        sqlListar = "SELECT id,nome FROM pessoas";
        operacaoSelecionar = conexao.prepareStatement("SELECT id,nome FROM pessoas WHERE id = ?");

    }

    @Override
    public void criar(Pessoa pessoa) throws Exception {
        operacaoInserir.clearParameters();
        operacaoInserir.setString(1, pessoa.getNome());
        operacaoInserir.executeUpdate();
    }

    @Override
    public List<Pessoa> listar() throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();

        ResultSet resultado = operacaoListar.executeQuery(sqlListar);

        while (resultado.next()) {
            Pessoa p = new Pessoa();
            p.setId(Integer.parseInt(resultado.getString("id")));
            p.setNome(resultado.getString("nome"));
            pessoas.add(p);
        }

        return pessoas;
    }

    @Override
    public Pessoa selecionar(Integer id) throws Exception {
        operacaoSelecionar.clearParameters();
        operacaoSelecionar.setInt(1, id);
        ResultSet resultado = operacaoSelecionar.executeQuery();

        Pessoa p = new Pessoa();
        p.setId(Integer.parseInt(resultado.getString("id")));
        p.setNome(resultado.getString("nome"));

        return p;
    }

}
