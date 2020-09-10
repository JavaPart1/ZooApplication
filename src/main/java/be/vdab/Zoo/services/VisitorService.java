package be.vdab.Zoo.services;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.exceptions.VisitorException;
import be.vdab.Zoo.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.Zoo.repositories.JdbcPass.*;

@Service
public class VisitorService {
    @Autowired
    private final VisitorRepository visitorRepository = new
            VisitorRepository();
    // je kan in bovenstaande gn parameters aan de constructor meegeven !!

    public void saveVisitor(Visitor visitor) throws SQLException {
        visitorRepository.addVisitor(visitor);
    }

    public List<Visitor> getAllVisitores() throws VisitorException {
        return visitorRepository.getAllVisitors();
    }

    public void addVisitor(Visitor visitor) throws SQLException, VisitorException {
        // Determine next idvisitor
        visitor.setId(visitorRepository.getMaxIdVisitor() + 1);
        visitorRepository.addVisitor(visitor);
    }

    public void deleteVisitor(Visitor visitor) {
    }

    public void deleteByName(String name) {
        // Determine visitor
        List<Visitor> visitorList = new ArrayList<>();
        int index = 99999;
        for (int i = 0; i < visitorList.size(); i++) {
            if (visitorList.get(i).getName().equals(name) ){
                index =i;
                break;
            }
        }
        if (index != 99999){
            Visitor v = visitorList.get(index);
            //visitorRepository.deleteVisitor(v);
        }
    }

    public void deleteVisitorById(int id) throws SQLException {
        visitorRepository.deleteVisitorById(id);
    }

    public Visitor getVisitorById(int id) throws VisitorException {
        return visitorRepository.getVisitorById(id);
    }

    public void updateVisitor(Visitor visitor) throws SQLException {
        visitorRepository.updateVisitor(visitor);
    }

    //public List<Visitor> findVisitorsByName(String name) {
        //return visitorRepository.findByName(name);
    //}
}
