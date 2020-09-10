package be.vdab.Zoo.domain;

import java.util.List;

public class Ticket extends BaseEntity{
    private String ticketName;
    private double originalPrice;
    private double reductionPrice;

    private List<Visitor> visitors;

    public Ticket() {
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(double reductionPrice) {
        this.reductionPrice = reductionPrice;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
