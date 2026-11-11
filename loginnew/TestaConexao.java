package loginnew;

import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) {
        Conexao banco = new Conexao();

        try {
            banco.abrirConexao();
            System.out.println("Conexão realizada com sucesso!");
            banco.fecharConexao();
            System.out.println("Conexão fechada.");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        }
    }
}