package db;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqliteJDBCConnector {

    public static void runSql(Connection c, String path) throws IOException,SQLException {
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            String[] inst = sb.toString().split(";");
            Statement st = c.createStatement();
            for(Integer i = 0; i<inst.length; i++) {
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}