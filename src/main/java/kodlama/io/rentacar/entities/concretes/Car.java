package kodlama.io.rentacar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.tools.javac.Main;
import jakarta.persistence.*;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToMany(mappedBy = "car")
    //@JsonBackReference
    private List<Maintenance> maintenances;
    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

}
