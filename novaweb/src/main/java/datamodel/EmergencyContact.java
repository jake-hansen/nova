package datamodel;

import javax.persistence.*;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "primary_phone")
    private String primaryPhone;

    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "email")
    private String email;
}
