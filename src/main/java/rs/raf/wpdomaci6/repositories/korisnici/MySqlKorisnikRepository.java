package rs.raf.wpdomaci6.repositories.korisnici;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikRepository {


    @Override
    public Korisnik add(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO korisnik (name, lastname, email, password, type, status) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, korisnik.getName());
            preparedStatement.setString(2, korisnik.getLastname());
            preparedStatement.setString(3, korisnik.getEmail());
            preparedStatement.setString(4, korisnik.getPassword());
            preparedStatement.setString(5, korisnik.getType());
            preparedStatement.setBoolean(6, korisnik.isStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                korisnik.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public List<Korisnik> allKorisnici() {

        List<Korisnik> korisnici = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnik");
            while (resultSet.next()) {
                korisnici.add(new Korisnik(resultSet.getInt("id"),  resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getString("email"), resultSet.getString("password"),resultSet.getString("type"),resultSet.getBoolean("status")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnici;
    }

    @Override
    public Korisnik find(Integer id) {

        Korisnik korisnik = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int korisnikId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                boolean status = resultSet.getBoolean("status");
                korisnik = new Korisnik(korisnikId, name, lastname, email, password, type, status);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM korisnik where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Korisnik update(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnik SET name = ?, lastname = ?, email = ?, password = ?, type = ?, status = ? WHERE id = ?");
            preparedStatement.setString(1, korisnik.getName());
            preparedStatement.setString(2, korisnik.getLastname());
            preparedStatement.setString(3, korisnik.getEmail());
            preparedStatement.setString(4, korisnik.getPassword());
            preparedStatement.setString(5, korisnik.getType());
            preparedStatement.setBoolean(6, korisnik.isStatus());
            preparedStatement.setInt(7, korisnik.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return korisnik;
    }
}
