package loginnew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private final String url = "jdbc:mysql://localhost:3306/bd_login"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "1828312007Th#";

    public Connection con;
    public Statement stmt;
    public ResultSet resultset;

    public void abrirConexao() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
    }

    public void fecharConexao() {
        try {
            if (resultset != null) resultset.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão " + ex.getMessage());
        }
    }
}