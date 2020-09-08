package be.vdab.Zoo.domain;

import java.util.List;

public class Ticket extends BaseEntity{
    private double originalPrice;
    private double reductionPrice;

    private List<Visitor> visitors;

}
