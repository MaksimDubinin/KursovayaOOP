package database;
import java.sql.*;

public class MyConnect {

    private final String url;
    private final String user;
    private final String password;

    public MyConnect() {
        this.url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        this.user = "postgres";
        this.password = "root";
    }

    public String[][] allSelect() {
        String select = """
                Select name ,countWin
                from leaderboard
                """;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            System.out.println(e.getMessage());
            return new String[0][0];
        }

        final int row = getCountRow();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(select);
            String[][] mass = new String[row][2];
            if (rs.next()) {
                for (int i = 1; i <= row; ++i) {
                    mass[i - 1][0] = rs.getString(1);
                    mass[i - 1][1] = rs.getString(2);
                    rs.next();
                }
            }
            return mass;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.err.println(e.getMessage());
            return new String[0][0];
        }
    }

    public int getCountRow() {
        String selectCount = """
                Select count(*) from leaderboard
                """;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            System.out.println(e.getMessage());
            return 0;
        }

        int count = 0;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(selectCount);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.err.println(e.getMessage());
            return 0;
        }
        return count;
    }

    public boolean insertInDb(String name) {
        String insert = """
                insert into leaderboard
                (name, countWin)
                values
                (?, 0);
                """;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            System.out.println(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(insert);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean setCountWin(String name) {

        String sql = """
                UPDATE leaderboard
                SET countWin = ?
                WHERE name = ?
                """;
        int wins = getCountWin(name);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            System.out.println(e.getMessage());
            return false;
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, wins + 1);
            statement.setString(2, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public int getCountWin(String name) {
        String select = """
                Select countWin
                from leaderboard
                WHERE name = ?
                """;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            System.out.println(e.getMessage());
        }

        int countWinFromDb = 0;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(select);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            countWinFromDb = rs.getInt(1);
            return countWinFromDb;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.err.println(e.getMessage());
            return 0;
        }
    }
}