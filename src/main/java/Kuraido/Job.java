package Kuraido;

public class Job
{
    private String name="";
    private String location="";
    private String salary="";
    private String description="";
    private String company="";
    private String link="";

    public  Job(String name, String location, String salary, String description, String company,String link)
    {
        this.name=name;
        this.location=location;
        this.salary=salary;
        this.description=description;
        this.company=company;
        this.link=link;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLink(String link){ this.link=link;}

    public String getLink() {return link;}

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", description='" + description + '\'' +
                ", company='" + company + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
