import java.sql.*;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:doacoes.db";

    public static void criarTabela() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS doacoes (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "tipo TEXT NOT NULL," +
                         "quantidade INTEGER NOT NULL," +
                         "data TEXT NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarDoacao(Doacao doacao) {
        String sql = "INSERT INTO doacoes(tipo, quantidade, data) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doacao.getTipo());
            pstmt.setInt(2, doacao.getQuantidade());
            pstmt.setString(3, doacao.getData());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int calcularTotalDoacoes() {
        String sql = "SELECT SUM(quantidade) FROM doacoes";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
