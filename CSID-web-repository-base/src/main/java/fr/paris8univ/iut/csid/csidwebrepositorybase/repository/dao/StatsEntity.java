package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao;


import javax.persistence.*;

@Entity
@Table(name = "stats")
public class StatsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "entry_type")
    private String entry_type;

    @Column(name = "datetime")
    private String datetime;

    @Column(name = "value_entry")
    private int value;

    @Column(name = "name")
    private String name;

    public StatsEntity() {}

    public StatsEntity(String entry_type, String datetime, int value, String name) {
        this.entry_type = entry_type;
        this.datetime = datetime;
        this.value = value;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry_type() {
        return entry_type;
    }

    public void setEntry_type(String entry_type) {
        this.entry_type = entry_type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
