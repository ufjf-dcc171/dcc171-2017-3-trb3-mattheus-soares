/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mattheus
 */
public class ConexaoJavaDB {

    private static Connection instancia = null;

    public static Connection getConnection() throws Exception {
        if (instancia == null) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driveURL = "jdbc:derby://localhost:1527/2017-dcc171";
            instancia = DriverManager.getConnection(driveURL, "usuario", "senha");

        }
        return instancia;
    }
}
