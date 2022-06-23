package tesksystems.psomos_michael_casestudy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meetup_posts")
public class MeetUpPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Lob
    @Column(name = "meetup_message")
    private String meetupMessage;

    @Column(name = "location")
    private String location;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "meetup_date")
    private String meetupDate;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "meetup_time")
    private String meetupTime;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;
}
