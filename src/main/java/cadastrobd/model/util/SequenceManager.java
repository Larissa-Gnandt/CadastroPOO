package cadastrobd.model.util;

import java.sql.*;

public class SequenceManager {
    public static int getNextAutoIncrement(String tableName) throws SQLException {
        int valor = -1;
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'mydb' AND TABLE_NAME = ?";
        try (Connection conn = ConectorBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    valor = rs.getInt("AUTO_INCREMENT");
                }
            }
        }
        return valor;
    }
}