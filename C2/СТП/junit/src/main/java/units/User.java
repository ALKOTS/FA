package units;

import java.util.*;

public class User {

    private int id;
    private String name;
    private int age;
    private Gender gen;

    private static HashMap<Integer, User> allUsers;

    private static int countId = 0;

    public User(String name, int age, Gender gen) {
        this.name = name;
        this.gen = gen;
        this.age = age;

        if (allUsers == null){
            allUsers = new HashMap<Integer, User>();
        }

        if (!hasUser()){
            this.id = ++countId;
            allUsers.put(id, this);
        }
    }


    @Override
    public int hashCode(){
        return Objects.hash(name, age, gen);
    }

    private boolean hasUser(){
        for (User user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                name.equals(user.name) &&
                gen == user.gen;
    }


    public static List<User> getAllUsers(Gender gen){
        List<User> listAllUsers = new ArrayList<>();
        for (User user: allUsers.values()){
            if (user.gen == gen){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }

    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    public static int getCount() {
        return allUsers.size();
    }

    public static int getCount(Gender gen) {
        return getAllUsers(gen).size();
    }

    public static int getSumAgeUsers() {
        int counter = 0;
        for (User user: allUsers.values()) {
            counter += user.age;
        }
        return counter;
    }

    public static int getSumAgeUsers(Gender gen) {
        int counter = 0;
        for (User user: getAllUsers(gen)) {
            counter += user.age;
        }
        return counter;
    }

    public static double getAvgAgeUsers() {
        return getSumAgeUsers() / getCount();
    }

    public static double getAvgAgeUsers(Gender gen) {
        return getSumAgeUsers(gen) / getCount(gen);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gen +
                '}';
    }
}
