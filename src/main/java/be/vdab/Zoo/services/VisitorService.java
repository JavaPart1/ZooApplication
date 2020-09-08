package be.vdab.Zoo.services;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorService {
    @Autowired
    private final VisitorRepository visitorRepository = new VisitorRepository();

    public void saveVisitor(Visitor visitor) {
        visitorRepository.insertVisitor(visitor);
    }

    public List<Visitor> getAllVisitores() {
        return visitorRepository.getAllVisitors();
    }

    public void addVisitor(Visitor visitor) {
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
            visitorRepository.deleteVisitor(v);
        }
    }

    //public List<Visitor> findVisitorsByName(String name) {
        //return visitorRepository.findByName(name);
    //}
}
