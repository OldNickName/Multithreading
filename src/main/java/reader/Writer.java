package reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Writer {

    final String jdbcURL = "jdbc:mysql://localhost:3306/TZ" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";
    final String username = "gambit";
    final String password = "dadayada";

    Connection connection = null;

    public void writeToDB(Reader reader, int rowCount) throws ExecutionException, InterruptedException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String s = "INSERT INTO table_name ( FIRST_RANDOM, SECOND_RANDOM, THIRD_RANDOM) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(s);

            for (int i = 1; i <= rowCount; i++) {
                preparedStatement.setString(1, reader.getRandomList().get(i)[1]);
                preparedStatement.setString(2, reader.getRandomList().get(i)[2]);
                preparedStatement.setString(3, reader.getRandomList().get(i)[3]);
                preparedStatement.execute();
            }
            connection.commit();
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("Transfer to database done");
    }
}
