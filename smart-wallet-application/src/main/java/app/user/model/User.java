package app.user.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    private String firstName;
    private String lastName;
    private String profilePicture;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean active;

    @Column(nullable = false)
    private LocalDate createdOn;

    @Column(nullable = false)
    private LocalDate updatedOn;

}
