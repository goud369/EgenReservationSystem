package restaurant.vs.model;

import java.sql.Time;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author goud
 */
public class Reservation {

    private int reservation_id;
    private int numberOfGuests;
    private java.sql.Date reservation_Date;
    private Time reservation_Time;
    private int customer_id;
    private int table_id;
    private  String reservation_Status;
    private Customers customer;
    private Tables table;

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }
 

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Date getReservation_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(Date reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public Time getReservation_Time() {
        return reservation_Time;
    }

    public void setReservation_Time(Time reservation_Time) {
        this.reservation_Time = reservation_Time;
    }

    public String getReservation_Status() {
        return reservation_Status;
    }

    public void setReservation_Status(String reservation_Status) {
        this.reservation_Status = reservation_Status;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }
}
