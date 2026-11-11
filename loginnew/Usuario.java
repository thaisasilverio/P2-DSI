package loginnew;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Usuario {
    private String usuario;
    private String nome;
    private String senha;

    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    static String nomeUsuario;
    static String usuarioSistema;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String gerarHash(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaUsuario(String usuario, String senha){
        Conexao banco = new Conexao();

        try{
            banco.abrirConexao();

            banco.stmt= banco.con.createStatement();

            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                                          + "WHERE usuario = '" + usuario + "'"
                                          + " AND senha = '" + gerarHash(senha) + "'");

            if (banco.resultset.next()){
                resultUsuario = true;

                setUsuario(banco.resultset.getString("usuario"));
                setNome(banco.resultset.getString("nome"));

                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
            }else{
                resultUsuario = false;
            }

            banco.fecharConexao();

        }catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    public boolean verificaUsuario(String usuario){
        Conexao banco = new Conexao();

        try{
            banco.abrirConexao();

            banco.stmt= banco.con.createStatement();

            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                                          + "WHERE usuario = '" + usuario + "'");

            if (banco.resultset.next()){
                resultUsuario = true;
            }else{
                resultUsuario = false;
            }

            banco.fecharConexao();

        }catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;
    }

    public boolean cadastraUsuario(String nome, String usuario, String senha){
        Conexao banco = new Conexao();

        try{
            banco.abrirConexao();

            banco.stmt = banco.con.createStatement();

            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                    + " VALUES ('" + nome + "','" + usuario + "', '"
                    + gerarHash(senha) + "')");

            resultCadastro = true;

        }catch(SQLException ec){
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        }

        return resultCadastro;
    }

    public boolean alteraUsuario(String nome, String usuario, String senha){
        Conexao banco = new Conexao();

        try {
            banco.abrirConexao();

            banco.stmt = banco.con.createStatement();

            banco.stmt.execute("UPDATE usuario SET nome = '" + nome +
                    "', senha = '" + gerarHash(senha) +
                    "' WHERE usuario = '" + usuario + "'");
            resultAlteracao = true;

        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }

        banco.fecharConexao();

        return resultAlteracao;
    }

    public boolean excluiUsuario(String usuario){
        Conexao banco = new Conexao();

        try{
            banco.abrirConexao();

            banco.stmt = banco.con.createStatement();

            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '"
                    + usuario + "'");

            resultExclusao = true;

        }catch (SQLException ec){
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        }

        banco.fecharConexao();

        return resultExclusao;
    }
}