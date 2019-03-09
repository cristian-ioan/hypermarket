package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments", schema = "hypermarket")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @OneToMany(mappedBy = "id")
    private Set<Employee> employeesManager;

}
