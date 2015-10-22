/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.vs.model;

/**
 *
 * @author goud
 */
public class TableStatus {
    String Status;
    int Table_id;
    Tables table;

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public int getTable_id() {
        return Table_id;
    }

    public void setTable_id(int Table_id) {
        this.Table_id = Table_id;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
