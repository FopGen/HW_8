package org.example;

import org.example.model.ClientModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private PreparedStatement createSt;
    private PreparedStatement selectByIdSt;
    private PreparedStatement updateNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("INSERT INTO client (name) VALUES (?) RETURNING id");
        selectByIdSt = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
        updateNameSt = connection.prepareStatement("UPDATE client SET name=? WHERE client.id = ?");
        deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id=?");
        listAllSt = connection.prepareStatement("SELECT * FROM client");

    }
    public long create(String name){
        try {

            createSt.setString(1, name);
            ResultSet rs = createSt.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id){
        try {

            selectByIdSt.setLong(1, id);
            ResultSet rs = selectByIdSt.executeQuery();
            rs.next();
            return rs.getString(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name){

        try {
            updateNameSt.setString(1, name);
            updateNameSt.setLong(2, id);
            updateNameSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        try{
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientModel> listAll(){

        ResultSet rs = null;
        try {

            rs = listAllSt.executeQuery();
            List<ClientModel> clientModelList = new ArrayList<>();

            while(rs.next()){
                clientModelList.add(new ClientModel(rs.getInt(1), rs.getString(2)));
            }
            return clientModelList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}