package be.vdab.Zoo.controllers;

import be.vdab.Zoo.domain.Ticket;
import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import be.vdab.Zoo.exceptions.TicketException;
import be.vdab.Zoo.exceptions.VisitorException;
import be.vdab.Zoo.services.TicketService;
import be.vdab.Zoo.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService manager = new TicketService();
    VisitorService visitorService = new VisitorService();

    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public String getAllTickets(Model model) throws TicketException {
        model.addAttribute("tickets",manager.getAllTickets());
        return "showTickets";
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdatePage(@PathVariable("id") int id, ModelMap modelMap) throws TicketException {
        modelMap.addAttribute("ticket", manager.getTicketById(id));
        return new ModelAndView("updateTicket", modelMap);
    }

    @PostMapping("/{id}/update")
    public ModelAndView saveTicket(@PathVariable("id") int id, @ModelAttribute Ticket ticket) throws SQLException {
        manager.updateTicket(ticket);
        return new ModelAndView("redirect:/ticket/getAllTickets");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id) throws SQLException {
        manager.deleteTicketById(id);
        return new ModelAndView("redirect:/ticket/getAllTickets");
    }

    @GetMapping("/add")
    public ModelAndView showAddView(ModelMap modelMap){
        return new ModelAndView("addTicket");
    }

    @PostMapping("/add")
    public ModelAndView addTicket(@ModelAttribute("ticket") Ticket nwTicket,
                                   ModelMap modelMap) throws SQLException, TicketException {
        manager.addTicket(nwTicket);
        return new ModelAndView("redirect:/ticket/getAllTickets");
    }

    @GetMapping("/{id}/buyticket")
    public ModelAndView showBuyPage(@PathVariable("id") int id, ModelMap modelMap) throws TicketException, VisitorException {
        modelMap.addAttribute("visitor", visitorService.getVisitorById(id));
        modelMap.addAttribute("tickets",manager.getAllTickets());
        modelMap.addAttribute("selection",new Ticket());
        return new ModelAndView("buyTicket", modelMap);
    }

    @PostMapping("/{id}/buyticket")
    public ModelAndView save(@PathVariable("id") int id, @ModelAttribute Ticket ticket) throws SQLException, TicketException {
        manager.addVisitorTicket(id,ticket.getId());
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

    @GetMapping("/{id}/showticket")
    public ModelAndView showTickets(@PathVariable("id") int id, ModelMap modelMap) throws TicketException, VisitorException {
        modelMap.addAttribute("visitor", visitorService.getVisitorById(id));
        modelMap.addAttribute("tickets",manager.getVisitorTicketByVisitorId(id));
        return new ModelAndView("showTicket", modelMap);
    }

}
