package be.vdab.Zoo.controllers;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import be.vdab.Zoo.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String getAllVisitors(Model model){
        model.addAttribute("visitors",manager.getAllVisitores());
        return "showVisitors";
    }

    @GetMapping("/{name}/delete")
    public ModelAndView delete(@PathVariable("name") String name) {
        manager.deleteByName(name);
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id, ModelMap modelMap) {
//        employeeService.deleteById(id);
        modelMap.addAttribute("succes", true);
        return new ModelAndView("redirect:/employee/getAllEmployees");
    }



    @GetMapping("/add")
    public ModelAndView showAddView(ModelMap modelMap){
        // Wat zijn de visitortypes
        modelMap.addAttribute("visitortypes", VisitorType.values());



        return new ModelAndView("addVisitor");
    }

    @PostMapping("/add")
    public ModelAndView addVisitor(@ModelAttribute("visitor") Visitor employeeVO,
                                   ModelMap modelMap){
        manager.addVisitor(employeeVO);
        return new ModelAndView("redirect:/visitor/getAllVisitors");
    }

}
