/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ed
 */
import java.util.ArrayList;

public class Item {

    private final String name;

    private ArrayList<Record> records;

    public Item(String name) {

        this.name = name;

    }

    public void addRecord(Record record) {

        records.add(record);

    }

    public Record getRecord(int index) {
        return records.get(index);

    }

    public String getName() {
        return name;
    }

}
