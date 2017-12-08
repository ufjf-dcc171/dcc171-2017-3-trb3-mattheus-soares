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
public class ProjetoDAOJDBC implements ProjetoDAO {

    private Connection conexao;
    private PreparedStatement operacaoInserir;
    private String sqlListar;
    private Statement operacaoListar;
    private PreparedStatement operacaoSelecionar;

    public ProjetoDAOJDBC() throws Exception {
        conexao = ConexaoJavaDB.getConnection();
        operacaoInserir = conexao.prepareStatement("INSERT INTO projetos(tipo,nome) VALUES(?,?)");
        operacaoListar = conexao.createStatement();
        sqlListar = "SELECT id,tipo,nome FROM projetos";
        operacaoSelecionar = conexao.prepareStatement("SELECT tipo,nome FROM projetos WHERE id = ?");
    }

    @Override
    public void criar(Projeto projeto) throws Exception {
        operacaoInserir.clearParameters();
        operacaoInserir.setString(1, projeto.getTipo());
        operacaoInserir.setString(2, projeto.getNome());
        operacaoInserir.executeUpdate();
    }

    @Override
    public List<Projeto> listar() throws Exception {
        List<Projeto> projetos = new ArrayList<>();

        ResultSet resultado = operacaoListar.executeQuery(sqlListar);

        while (resultado.next()) {
            Projeto p = new Projeto();
            p.setId(Integer.parseInt(resultado.getString("id")));
            p.setTipo(resultado.getString("tipo"));
            p.setNome(resultado.getString("nome"));
            projetos.add(p);
        }

        return projetos;
    }

    @Override
    public Projeto selecionar(Integer id) throws Exception {
        operacaoSelecionar.clearParameters();
        operacaoSelecionar.setInt(1, id);
        ResultSet resultado = operacaoSelecionar.executeQuery();
        
        Projeto p = new Projeto();
        p.setId(Integer.parseInt(resultado.getString("id")));
        p.setTipo(resultado.getString("tipo"));
        p.setNome(resultado.getString("nome"));
        
        return p;
    }

}
