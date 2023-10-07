package rs.raf.wpdomaci6.repositories.tagovi;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.TagVesti;
import rs.raf.wpdomaci6.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagVestiRepository extends MySqlAbstractRepository implements TagVestiRepository {

    @Override
    public TagVesti add(TagVesti tagVesti) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO tag_vesti (tag, vest) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, tagVesti.getTag());
            preparedStatement.setInt(2, tagVesti.getVest());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tagVesti.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagVesti;
    }

    @Override
    public List<TagVesti> allTagsVesti() {

        List<TagVesti> tagVestiList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag_vesti");
            while (resultSet.next()) {
                tagVestiList.add(new TagVesti(resultSet.getInt("id"),  resultSet.getString("tag"), resultSet.getInt("vest")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagVestiList;
    }

    @Override
    public TagVesti find(Integer id) {

        TagVesti tagVesti = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag_vesti where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int tagVestiID = resultSet.getInt("id");
                String tag = resultSet.getString("tag");
                int vest = resultSet.getInt("vest");
                tagVesti = new TagVesti(tagVestiID, tag,vest);
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

        return tagVesti;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tag_vesti where id = ?");
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
