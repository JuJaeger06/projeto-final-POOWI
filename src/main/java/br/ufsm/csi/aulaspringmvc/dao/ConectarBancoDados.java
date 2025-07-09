package br.ufsm.csi.aulaspringmvc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {
    public static Connection conectarBancoPostgres() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/bibliotecaVirtual";
        String user = "postgres";
        String senha = "TEznl41B*";
        return DriverManager.getConnection(url, user, senha);
    }
}