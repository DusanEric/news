package rs.raf.wpdomaci6.repositories.vesti;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;
import rs.raf.wpdomaci6.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestRepository {

    @Override
    public Vest add(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO vest (title, text, number_of_visits, date_of_creation, autor, kategorija, tagovi) VALUES(?, ?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, vest.getTitle());
            preparedStatement.setString(2, vest.getText());
            preparedStatement.setInt(3, vest.getNumber_of_visits());
            preparedStatement.setString(4, vest.getDate_of_creation());
            preparedStatement.setString(5, vest.getAutor());
            preparedStatement.setString(6, vest.getKategorija());
            preparedStatement.setString(7, vest.getTagovi());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public List<Vest> allVesti() {

        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest");
            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),  resultSet.getString("title"), resultSet.getString("text"), resultSet.getInt("number_of_visits"), resultSet.getString("date_of_creation"),resultSet.getString("autor"),resultSet.getString("kategorija"),resultSet.getString("tagovi")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public Vest find(Integer id) {

        Vest vest = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int vestID = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                int number_of_visits = resultSet.getInt("number_of_visits");
                String date_of_creation = resultSet.getString("date_of_creation");
                String autor = resultSet.getString("autor");
                String kategorija = resultSet.getString("kategorija");
                String tagovi = resultSet.getString("tagovi");
                vest = new Vest(vestID, title, text, number_of_visits, date_of_creation, autor, kategorija,tagovi);
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

        return vest;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vest where id = ?");
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
    public Vest update(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE vest SET title = ?, text = ?, number_of_visits = ?, date_of_creation = ?, autor = ?, kategorija = ?, tagovi = ? WHERE id = ?");
            preparedStatement.setString(1, vest.getTitle());
            preparedStatement.setString(2, vest.getText());
            preparedStatement.setInt(3, vest.getNumber_of_visits());
            preparedStatement.setString(4, vest.getDate_of_creation());
            preparedStatement.setString(5, vest.getAutor());
            preparedStatement.setString(6, vest.getKategorija());
            preparedStatement.setString(7, vest.getTagovi());
            preparedStatement.setInt(8, vest.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public List<Vest> findByKategorija(String kategorija) {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest WHERE kategorija = ?");
            preparedStatement.setString(1, kategorija);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int vestID = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                int number_of_visits = resultSet.getInt("number_of_visits");
                String date_of_creation = resultSet.getString("date_of_creation");
                String autor = resultSet.getString("autor");
                String tagovi = resultSet.getString("tagovi");

                vesti.add(new Vest(vestID, title, text, number_of_visits, date_of_creation, autor, kategorija, tagovi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }
}
