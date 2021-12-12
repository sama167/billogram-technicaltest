package st.projects.assignments.billogram.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DiscountCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    public String code;

    @ManyToOne
    @NotNull
    public User user;

    @ManyToOne
    @NotNull
    public Campaign campaign;
}