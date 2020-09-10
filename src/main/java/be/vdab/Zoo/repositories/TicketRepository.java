package be.vdab.Zoo.repositories;

import be.vdab.Zoo.domain.Ticket;
import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import be.vdab.Zoo.exceptions.TicketException;
import be.vdab.Zoo.exceptions.VisitorException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.Zoo.repositories.JdbcPass.*;

@Repository
public class TicketRepository {
    List<Ticket> tickets = new ArrayList<>();

    public TicketRepository() {

    }

    public Ticket getTicketById(int id) throws TicketException {
        String query = "SELECT * FROM ticket WHERE idticket=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setInt(1, id);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(id);
                    ticket.setTicketName(rs.getString("ticketname"));
                    ticket.setOriginalPrice(rs.getDouble("originprice"));
                    ticket.setReductionPrice(rs.getDouble("reductionprice"));
                    return ticket;
                } else {
                    System.out.println("Ticket not found with Id : " + id);
                    throw new TicketException("T not found");
                }
            }
        } catch (SQLException | TicketException throwables) {
            throwables.printStackTrace();
            throw new TicketException("SQL err");
        }
    }

    public int getMaxIdTicket() throws TicketException {
        String query = "SELECT MAX(idticket) FROM ticket";
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
                    throw new TicketException("Max went in error");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new TicketException("SQL err");
        }
    }

    public void addTicket(Ticket nwTicket) throws SQLException {
        String insert = "INSERT INTO ticket (idticket, ticketname, originprice, reductionprice) " +
                " VALUES (?, ?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwTicket.getId());
            stmt.setString(2, nwTicket.getTicketName());
            stmt.setDouble(3, nwTicket.getOriginalPrice());
            stmt.setDouble(4, nwTicket.getReductionPrice());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }
    }

    public void updateTicket(Ticket corTicket) throws SQLException {
        String update = "UPDATE ticket SET ticketname =?, originprice =?, reductioprice =? " +
                " WHERE idticket=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(update)
        ) {
            stmt.setString(1, corTicket.getTicketName());
            stmt.setDouble(2, corTicket.getOriginalPrice());
            stmt.setDouble(3, corTicket.getReductionPrice());
            stmt.setInt(4, corTicket.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }

    public void deleteTicket(Ticket exTicket) throws SQLException {
        String del = "DELETE ticket WHERE idticket=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(del)
        ) {
            stmt.setInt(1, exTicket.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBCURL,JDBCUSER,PASSW);
    }

    public List<Ticket> getAllTickets() throws TicketException {
        List<Ticket> ticketList = new ArrayList<>();
        String query = "SELECT * FROM ticket";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                while (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(rs.getInt("idticket"));
                    ticket.setTicketName(rs.getString("ticketname"));
                    ticket.setOriginalPrice(rs.getDouble("originprice"));
                    ticket.setReductionPrice(rs.getDouble("reductionprice"));
                    ticketList.add(ticket);
                }
                return ticketList;
            }
            catch (SQLException throwables){
                throwables.printStackTrace();
                throw new TicketException("SQL err");
            }
        } catch (SQLException | TicketException throwables) {
            throwables.printStackTrace();
            throw new TicketException("SQL err");
        }
    }


    public void deleteTicketById(int id) throws SQLException {
        String del = "DELETE FROM ticket WHERE idticket=?";
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

    public void addVisitorTicket(int visitorid, int ticketid) throws SQLException {
        String insert = "INSERT INTO visitorticket (idvisitor, idticket) " +
                " VALUES (?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, visitorid);
            stmt.setInt(2, ticketid);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }
    }

    public List<Ticket> getVisitorTicketByVisitorId(int visitorid) throws TicketException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM visitorticket WHERE idvisitor=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setInt(1, visitorid);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                while (rs.next()) {
                    int ticketid = rs.getInt("idticket");
                    Ticket ticket = getTicketById(ticketid);
                    tickets.add(ticket);
                }
                return tickets;
                } catch (SQLException e) {
                e.printStackTrace();
                throw new TicketException("T not found");
            }
        } catch (SQLException | TicketException throwables) {
            throwables.printStackTrace();
            throw new TicketException("SQL err");
        }
    }
}
