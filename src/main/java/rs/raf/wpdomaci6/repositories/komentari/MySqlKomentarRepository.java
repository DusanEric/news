package rs.raf.wpdomaci6.repositories.komentari;

import rs.raf.wpdomaci6.entities.Komentar;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlKomentarRepository extends MySqlAbstractRepository implements KomentarRepository {

    @Override
    public Komentar add(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO komentar (autor, text, date, vest_id) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, komentar.getAutor());
            preparedStatement.setString(2, komentar.getText());
            preparedStatement.setString(3, komentar.getDate());
            preparedStatement.setInt(4, komentar.getVest_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentar.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }

    @Override
    public List<Komentar> allKomentari() {

        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from komentar");
            while (resultSet.next()) {
                komentari.add(new Komentar(resultSet.getInt("id"),  resultSet.getString("autor"), resultSet.getString("text"), resultSet.getString("date"), resultSet.getInt("vest_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentari;
    }

    @Override
    public Komentar find(Integer id) {

        Komentar komentar = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM komentar where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int komentarID = resultSet.getInt("id");
                String autor = resultSet.getString("autor");
                String text = resultSet.getString("text");
                String date = resultSet.getString("date");
                int vest_id = resultSet.getInt("vest_id");
                komentar = new Komentar(komentarID,autor,text,date,vest_id);
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

        return komentar;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM komentar where id = ?");
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
}
