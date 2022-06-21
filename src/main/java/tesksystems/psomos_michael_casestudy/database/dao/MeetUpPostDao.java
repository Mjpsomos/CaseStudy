package tesksystems.psomos_michael_casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUpPost;

import java.util.List;

@Repository
public interface MeetUpPostDao extends JpaRepository<MeetUpPost, Long> {

    MeetUpPost findById(@Param("id") Integer id);

    Integer deleteById(@Param("id") Integer id);

    List<MeetUpPost> findMeetUpPostByUserIdOrderByMeetupDateDesc(@Param("user_id") Integer userId);

    List<MeetUpPost> findMeetUpPostByLocationContainsOrderByMeetupDateDesc(@Param("location") String location);


}
