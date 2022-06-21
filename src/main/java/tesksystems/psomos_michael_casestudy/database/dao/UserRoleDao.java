package tesksystems.psomos_michael_casestudy.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tesksystems.psomos_michael_casestudy.database.entity.UserRole;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(@Param("userId") Integer userId);


    //needs more work
    @Query(value =" select *  from users u join user_roles ur  on u.id = ur.user_id  where ur.user_role = 'USER' ",
            nativeQuery = true)
    List<UserRole> findByUserRole();




}
