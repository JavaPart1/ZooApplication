package be.vdab.Zoo.domain;

import be.vdab.Zoo.domain.enums.VisitorType;
import org.apache.tomcat.jni.Address;

import java.io.Serializable;
import java.util.List;

public class Visitor extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private VisitorType visitorType;
    private String name;
    private Ticket ticket;
    private List<Address> address;

    public Visitor(VisitorType visitorType, String name) {

        this.visitorType = visitorType;
        this.name = name;
    }

    public Visitor() {
    }

    public VisitorType getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(VisitorType visitorType) {
        this.visitorType = visitorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
