package be.vdab.Zoo.repositories;

import be.vdab.Zoo.domain.Visitor;
import be.vdab.Zoo.domain.enums.VisitorType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class VisitorRepository {
    List<Visitor> visitors = new ArrayList<>();

    public VisitorRepository() {
    }

    public void insertVisitor(Visitor visitor){


    }

    public List<Visitor> getAllVisitors(){

        Visitor v1 = new Visitor(VisitorType.ADULT,"Janssens");
        visitors.add(v1);

        Visitor v2 = new Visitor(VisitorType.ADULT,"Peeters");
        visitors.add(v2);

        return visitors;
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public void deleteVisitor(Visitor v) {
        visitors.remove(v);
    }
}
