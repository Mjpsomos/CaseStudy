package tesksystems.psomos_michael_casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUp;

import java.util.List;

@Repository
public interface MeetUpDao extends JpaRepository<MeetUp, Long> {

    MeetUp findById(@Param("id") Integer id);

//    @Query("Select mu from MeetUp mu where mu.meetUpPost = :meetUpPostId")
//    List<MeetUp> findAll(@Param("meetup_post_id") Integer meetUpPostId);

    List<MeetUp>  findByMeetUpPostId(@Param("meetup_post_id") Integer meetUpPostId);

}
