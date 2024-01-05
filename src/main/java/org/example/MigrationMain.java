package org.example;

import org.example.model.ClientModel;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MigrationMain {
    public static void main(String[] args) throws SQLException {

        String usernameForTest = "RobinGood";
        int idUserforTest = 11;
        Database db = new Database();

        Flyway flyway = Flyway.configure().dataSource(db.getDbUrl(), db.getUSERNAME(), db.getPASSWORD())
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();

        Connection connection = Database.getConnection();

        System.out.println(new ClientService(connection).create(usernameForTest));
        System.out.println(new ClientService(connection).getById(idUserforTest));
        new ClientService(connection).setName(idUserforTest, usernameForTest);
        new ClientService(connection).deleteById(idUserforTest);

        List<ClientModel> clientModelList = new ClientService(connection).listAll();
        for (ClientModel val: clientModelList){
            System.out.println(val.getId()+" - "+val.getName());
        }


        connection.close();
    }
}
