package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.annotation.CsvCell;

@Getter
@Setter
public class CsvData {
    @CsvCell("Id")
    private int id;

    @CsvCell("Item")
    private String item;

    @CsvCell("Cost")
    private double cost;

    @CsvCell("Tax")
    private double tax;

    @CsvCell("Total")
    private double total;

    @Override
    public String toString() {
        return "CsvData{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", cost=" + cost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
