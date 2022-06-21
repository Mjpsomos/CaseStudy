package tesksystems.psomos_michael_casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesksystems.psomos_michael_casestudy.database.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findById(@Param("id") Integer id);

    User findByEmail(@Param("email") String email);
}
