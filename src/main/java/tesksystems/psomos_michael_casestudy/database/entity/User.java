package tesksystems.psomos_michael_casestudy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "town_state")
    private String townState;

    @Column(name = "profile_description")
    private String profileDescription;

    @Column(name = "favorite_meetups")
    private String favoriteMeetups;

    @Column(name = "profile_image")
    private String profileImg;

    @Column(name = "create_date")
//    @Temporal(TemporalType.DATE)
    private Timestamp createDate;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "id")
    private Set<User> users;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "id")
    private Set<MeetUpPost> meetUpPost;


}
