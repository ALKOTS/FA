package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    private Long id;

    private String login;
    private String lastName;
    private String Name;
    private String middleName;
    private LocalDate birthday;

    @OneToMany(mappedBy = "Person")
    private List<Task> tasks;
}
