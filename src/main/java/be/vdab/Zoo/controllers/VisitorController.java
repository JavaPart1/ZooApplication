package be.vdab.Zoo.controllers;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import be.vdab.Zoo.exceptions.VisitorException;
import be.vdab.Zoo.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    VisitorService manager = new VisitorService();

/*
    @Qualifier("empService")
    @Autowired
    EmployeeService employeeService;

    @Qualifier("hrService")
    @Autowired
    EmployeeService hrService;
*/

/*
    @Value(value = "${vdab.cursus}")
    private String naamCursus;

*/

    @RequestMapping(value = "/getAllVisitors", method = RequestMethod.GET)
    public String getAllVisitors(Model model) throws VisitorException {
        model.addAttribute("visitors",manager.getAllVisitores());
        return "showVisitors";
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdatePage(@PathVariable("id") int id, ModelMap modelMap) throws VisitorException {
        modelMap.addAttribute("visitor", manager.getVisitorById(id));
        modelMap.addAttribute("visitortypes", VisitorType.values());
        //modelMap.addAttribute("professions", employeeService.getProfessions());
        return new ModelAndView("updateVisitor", modelMap);
    }

    @PostMapping("/{id}/update")
    public ModelAndView save(@PathVariable("id") int id, @ModelAttribute Visitor visitor) throws SQLException {
        manager.updateVisitor(visitor);
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id) throws SQLException {
        manager.deleteVisitorById(id);
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

    /*
    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelMap modelMap) {
//        employeeService.deleteById(id);
        modelMap.addAttribute("succes", true);
        return new ModelAndView("redirect:/employee/getAllEmployees");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        employeeService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", true);
        return new ModelAndView("redirect:/employee/getAllEmployees");
    }
*/



    @GetMapping("/add")
    public ModelAndView showAddView(ModelMap modelMap){
        // Wat zijn de visitortypes
        modelMap.addAttribute("visitortypes", VisitorType.values());



        return new ModelAndView("addVisitor");
    }

    @PostMapping("/add")
    public ModelAndView addVisitor(@ModelAttribute("visitor") Visitor nwVisitor,
                                   ModelMap modelMap) throws SQLException, VisitorException {
        manager.addVisitor(nwVisitor);
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

}
