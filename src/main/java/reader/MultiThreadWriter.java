package reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MultiThreadWriter extends Thread {

    private Reader doneReader;
    private int batchSize;
    private int rowCount;
    private int threadCount;
    private int threadNumber;

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

    public MultiThreadWriter(){}

    public MultiThreadWriter( Reader reader, int batchSize, int rowCount, int threadCount, int threadNumber){
        this.doneReader = reader;
        this.batchSize = batchSize;
        this.rowCount = rowCount;
        this.threadCount = threadCount;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run(){

        int everyThreadPart = rowCount / threadCount;
        int whenThreadStartPart = everyThreadPart*threadNumber+1;
        int whenThreadStopPart = whenThreadStartPart + everyThreadPart;

        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO table_name ( FIRST_RANDOM, SECOND_RANDOM, THIRD_RANDOM) VALUES ( ?, ?, ?)";
            ps = connection.prepareStatement(sql);

            if(this.threadCount==1){
                for(int j=0; j<=rowCount; j++){
                    ps.setString(1, doneReader.getRandomList().get(j)[1]);
                    ps.setString(2, doneReader.getRandomList().get(j)[2]);
                    ps.setString(3, doneReader.getRandomList().get(j)[3]);

                    ps.addBatch();

                    if (count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
            } else if(this.threadCount>1){
                for(int j=whenThreadStartPart; j<=whenThreadStopPart; j++){
                    ps.setString(1, doneReader.getRandomList().get(j)[1]);
                    ps.setString(2, doneReader.getRandomList().get(j)[2]);
                    ps.setString(3, doneReader.getRandomList().get(j)[3]);

                    ps.addBatch();

                    if (count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
            }

            ps.executeBatch();

            connection.commit();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
