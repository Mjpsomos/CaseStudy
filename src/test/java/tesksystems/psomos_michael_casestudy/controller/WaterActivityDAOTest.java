package tesksystems.psomos_michael_casestudy.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tesksystems.psomos_michael_casestudy.database.dao.WaterActivityDao;
import tesksystems.psomos_michael_casestudy.database.entity.WaterActivity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WaterActivityDAOTest {

    @Autowired
    private WaterActivityDao waterActivityDao;


    @Test
    @Order(1) //test passed
    public void createWaterActivityTest(){

        WaterActivity expected = new WaterActivity ();

        expected.setWaterActivity("Kayak");
        expected.setUserId(1);
        expected.setImage("No image");

        waterActivityDao.save(expected);

        Assertions.assertTrue(expected.getId()>0);

    }

    @Test
    @Order(2)
    public void getWaterActivityTest(){ //test passed

        WaterActivity expected = new WaterActivity();
        expected.setId(1);

        WaterActivity actual = waterActivityDao.findById(1);

        Assertions.assertEquals(expected.getId(), actual.getId());

    }

    @Test
    @Order(3)
    public void updateWaterActivityTest(){ //test passed

        String expected = "JetSki";

        WaterActivity actual = waterActivityDao.findById(1);
        actual.setWaterActivity("JetSki");

        waterActivityDao.save(actual);

        Assertions.assertEquals(expected, actual.getWaterActivity());

    }

    @Test
    @Order(4)       //test passed
    public void deleteWaterActivityTest(){

        WaterActivity actual = new WaterActivity ();

        actual.setWaterActivity("JetSki");
        actual.setUserId(1);

        waterActivityDao.save(actual);

        waterActivityDao.delete(actual);

        Assertions.assertNull(waterActivityDao.findById(actual.getId()));

    }

}