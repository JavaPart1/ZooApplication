package be.vdab.Zoo.services;

import be.vdab.Zoo.domain.Ticket;
import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.exceptions.TicketException;
import be.vdab.Zoo.exceptions.VisitorException;
import be.vdab.Zoo.repositories.TicketRepository;
import be.vdab.Zoo.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private final TicketRepository ticketRepository = new
            TicketRepository();
    // je kan in bovenstaande gn parameters aan de constructor meegeven !!

    public void saveTicket(Ticket ticket) throws SQLException {
        ticketRepository.addTicket(ticket);
    }

    public List<Ticket> getAllTickets() throws TicketException {
        return ticketRepository.getAllTickets();
    }

    public void addTicket(Ticket ticket) throws SQLException, TicketException {
        // Determine next idticket
        ticket.setId(ticketRepository.getMaxIdTicket() + 1);
        ticketRepository.addTicket(ticket);
    }

    public void deleteTicketById(int id) throws SQLException {
        ticketRepository.deleteTicketById(id);
    }

    public Ticket getTicketById(int id) throws TicketException {
        return ticketRepository.getTicketById(id);
    }

    public void updateTicket(Ticket ticket) throws SQLException {
        ticketRepository.updateTicket(ticket);
    }

    public void addVisitorTicket(int visitorid,int ticketid) throws SQLException, TicketException {
        // Determine next idticket
        ticketRepository.addVisitorTicket(visitorid, ticketid);
    }

    public List<Ticket> getVisitorTicketByVisitorId(int visitorid) throws TicketException {
        List<Ticket> tickets = new ArrayList<>();
        try {
            tickets = ticketRepository.getVisitorTicketByVisitorId(visitorid);
            return tickets;

        }
        catch (TicketException message){
            Ticket noticket = new Ticket();
            noticket.setTicketName("No tickets found");
            tickets.add(noticket);
            return tickets;
        }
    }
}
