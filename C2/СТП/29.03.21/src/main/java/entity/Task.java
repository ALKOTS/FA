package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    private Long id;

    private String Name;
    private String Description;
    private LocalDate finishDate;
    private boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category Category;
}