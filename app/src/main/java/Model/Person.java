package Model;

public class Person {
    private int id;
    private String name;
    private String lastName;
    private String startDate;
    private String endDate;
    private String description;


    public Person() {
    }

    public Person(String name, String lastName, String startDate, String endDate, String description) {
        this.name = name;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Person(int id, String name, String lastName, String startDate, String endDate, String description) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
