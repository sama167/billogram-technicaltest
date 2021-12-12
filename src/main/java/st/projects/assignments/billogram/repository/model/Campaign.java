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
public class Campaign {
    //    enum DISCOUNT_TYPE {AMOUNT, PERCENTAGE};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    public Brand brand;

    @NotNull
    public String name;

    @NotNull
    private double value;

    @NotNull
    public int number;
    //    private DISCOUNT_TYPE type;

}
