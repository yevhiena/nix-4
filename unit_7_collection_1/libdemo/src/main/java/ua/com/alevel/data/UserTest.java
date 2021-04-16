package ua.com.alevel.data;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserTest implements Comparable<UserTest>{

    private String name;

    public UserTest(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(UserTest userTest) {
        return this.name.compareTo(userTest.name);
    }

    @Override
    public String toString(){
        return "User{" +
                " name ='" + name + '\'' +
                '}';
    }

}
