package units;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {

    @Test
    public void testGetAllUsers() {

        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);

        List<User> expected = User.getAllUsers();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersMale() {

        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        List<User> expected = User.getAllUsers(Gender.M);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersFemale() {

        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        List<User> actual = new ArrayList<>();
        actual.add(user3);

        List<User> expected = User.getAllUsers(Gender.F);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCountMale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 2;
        int expected = User.getCount(Gender.M);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCountFemale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 1;

        int expected = User.getCount(Gender.F);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCount() {

        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 3;
        int expected = User.getCount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsers() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 93;
        int expected = User.getSumAgeUsers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsersMale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 77;
        int expected = User.getSumAgeUsers(Gender.M);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSumAgeUsersFemale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        int actual = 3;
        int expected = User.getSumAgeUsers(Gender.F);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsers() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        double actual = (1 + 2 + 3) / 3;

        double expected = User.getAvgAgeUsers();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsersMale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        double actual = (1 + 2 ) / 2;

        double expected = User.getAvgAgeUsers(Gender.M);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAvgAgeUsersFemale() {
        User user1 = new User("U1", 1, Gender.M);
        User user2 = new User("U2", 2, Gender.M);
        User user3 = new User("U3", 3, Gender.F);

        double actual = 3 / 1;

        double expected = User.getAvgAgeUsers(Gender.F);

        Assert.assertEquals(expected, actual);
    }

}