package be.vdab.Zoo.repositories;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import be.vdab.Zoo.exceptions.VisitorException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.Zoo.repositories.JdbcPass.*;

@Repository
public class VisitorRepository {
    private String url;
    private String user;
    private String password;

    List<Visitor> visitors = new ArrayList<>();

    public VisitorRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public VisitorRepository() {

    }

    public Visitor getVisitorById(int id) throws VisitorException {
        String query = "SELECT * FROM visitor WHERE idvisitor=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    Visitor visitor = new Visitor();
                    visitor.setId(id);
                    visitor.setName(rs.getString("visitorname"));
                    visitor.setVisitorType(VisitorType.valueOf(rs.getString("visitortype")));
                    return visitor;
                } else {
                    System.out.println("Vistor not found with Id : " + id);
                    throw new VisitorException("V not found");
                    //return null;

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new VisitorException("SQL err");
        }
    }

    public int getMaxIdVisitor() throws VisitorException {
        String query = "SELECT MAX(idvisitor) FROM visitor";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new VisitorException("Max went in error");
                    //return null;

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new VisitorException("SQL err");
        }
    }

    public void addVisitor(Visitor nwVisitor) throws SQLException {
        String insert = "INSERT INTO visitor (idvisitor, visitortype, visitorname) " +
                " VALUES (?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwVisitor.getId());
            stmt.setString(2, nwVisitor.getVisitorType().name());
            stmt.setString(3, nwVisitor.getName());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }
    }

    public void updateVisitor(Visitor corVisitor) throws SQLException {
        String update = "UPDATE visitor SET visitortype =?, visitorname =? " +
                " WHERE idvisitor=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(update)
        ) {
            stmt.setString(1, corVisitor.getVisitorType().name());
            stmt.setString(2, corVisitor.getName());
            stmt.setInt(3, corVisitor.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }

    public void deleteVisitor(Visitor exVisitor) throws SQLException {
        String del = "DELETE visitor WHERE idvisitor=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(del)
        ) {
            stmt.setInt(1, exVisitor.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCURL,JDBCUSER,PASSW);
    }

    public List<Visitor> getAllVisitors() throws VisitorException {
        List<Visitor> visitorList = new ArrayList<>();
        String query = "SELECT * FROM visitor";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                while (rs.next()) {
                    Visitor visitor = new Visitor();
                    visitor.setId(rs.getInt("idvisitor"));
                    visitor.setName(rs.getString("visitorname"));
                    visitor.setVisitorType(VisitorType.valueOf(rs.getString("visitortype")));
                    visitorList.add(visitor);
                }
                return visitorList;
            }
            catch (SQLException throwables){
                throwables.printStackTrace();
                throw new VisitorException("SQL err");
            }
        } catch (SQLException | VisitorException throwables) {
            throwables.printStackTrace();
            throw new VisitorException("SQL err");
        }
    }


    public void deleteVisitorById(int id) throws SQLException {
        String del = "DELETE FROM visitor WHERE idvisitor=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(del)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }
    }
}
