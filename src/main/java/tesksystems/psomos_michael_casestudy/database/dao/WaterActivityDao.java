package tesksystems.psomos_michael_casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesksystems.psomos_michael_casestudy.database.entity.WaterActivity;

import java.util.List;

@Repository
public interface WaterActivityDao extends JpaRepository<WaterActivity, Long> {

    WaterActivity findById(@Param("id") Integer id);

    List<WaterActivity> findByWaterActivity(@Param("water_activity") String waterActivity);

    List<WaterActivity> findWaterActivitiesByUserId(@Param("id") Integer id);

    void deleteById(Integer waterActivityId);
}
