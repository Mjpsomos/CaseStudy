package tesksystems.psomos_michael_casestudy.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Order(1)
    public void createUserTest() {

        User expected = new User();

        expected.setEmail("test@gmail.com");
        expected.setFirstName("Michael");
        expected.setLastName("Psomos");
        expected.setPassword("root");
        expected.setTownState("02112");
        expected.setProfileDescription("description");
        expected.setFavoriteMeetups("beach");
        expected.setProfileImg("image");

        userDao.save(expected);

        Assertions.assertTrue(expected.getId() > 0);

    }

    @Test
    @Order(2)
    public void getUserTest() { //test passed
        Integer userId = 1;

        User expected = new User();
        expected.setId(userId);

        User actual = userDao.findById(userId);

        Assertions.assertEquals(expected.getId(), actual.getId());

    }

    @ParameterizedTest
    @CsvSource({"1,michael", "2,paco", "3,michael"})
    public void testJUnit5CsvParameters(int id, String firstName) {
        User expected = new User();
        expected.setId(id);
        expected.setFirstName(firstName);

        User actual = userDao.findById(id);

        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());

    }

    @Test
    @Order(3)
    public void updateUserTest() {
        Integer userId = 1;
        String nameChange = "Update";

        User actual = userDao.findById(userId);
        String currentFirstName = actual.getFirstName();

        actual.setFirstName(nameChange);
        userDao.save(actual);

        String updatedFirstName = actual.getFirstName();


        Assertions.assertEquals(nameChange, actual.getFirstName());
        Assertions.assertNotEquals(currentFirstName, updatedFirstName);

    }


    @Test
    @Order(4)
    public void deleteUserTest() {

        User actual = new User();
        actual.setEmail("test@gmail.com");
        actual.setFirstName("Michael");
        actual.setLastName("Psomos");
        actual.setPassword("root");
        actual.setTownState("02112");
        actual.setProfileDescription("description");
        actual.setFavoriteMeetups("beach");
        actual.setProfileImg("image");

        userDao.save(actual);

        userDao.delete(actual);

        Assertions.assertNull(userDao.findById(actual.getId()));

    }
}