package tesksystems.psomos_michael_casestudy.database.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meetups")
public class MeetUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meetup_post_id", nullable = false)
    @EqualsAndHashCode.Exclude private MeetUpPost meetUpPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "water_activity_id", nullable = false)
    @EqualsAndHashCode.Exclude private WaterActivity waterActivity;
}
