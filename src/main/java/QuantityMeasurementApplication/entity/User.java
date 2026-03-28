package QuantityMeasurementApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email required")
    @Column(unique = true)
    private String email;

    // ❌ yaha NotBlank mat lagana (Google ke liye null hoga)
    private String password;

    @NotBlank(message = "Provider required")
    private String provider; // LOCAL or GOOGLE
}