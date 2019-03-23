package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees", schema = "hypermarket")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", length = 40, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 40, nullable = false)
    private String lastName;

    @Column(name = "salary", length = 20, nullable = false)
    private BigDecimal salary;

    @Column(name = "jobTitle", length = 40, nullable = false)
    private String jobTitle;

    @Column(name = "city", length = 40, nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="manager_id")
    private Employee manager;

//    @OneToMany(mappedBy="manager")
//    private Set<Employee> subordinates = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(jobTitle, employee.jobTitle) &&
                Objects.equals(city, employee.city) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(manager, employee.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), firstName, lastName, salary, jobTitle, city, department, manager);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", jobTitle='" + jobTitle + '\'' +
                ", city='" + city + '\'' +
                ", department=" + department +
                ", manager=" + manager +
                '}';
    }

}
