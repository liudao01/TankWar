package mytest;

import org.junit.Test;

/**
 * desc
 * Created by liuml.
 * Created time 2019/5/8.
 */
public class Test2 {

    @Test
    public void myTest() {
        Person person = new Person(10, 150);
        Person person2 = new Person(8, 100);

    }
}

class Person implements MyComparable {
    int age;
    int height;

    public Person(int age, int height) {
        this.age = age;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int MycomparaTo(Object obj) {

        if (!(obj instanceof Person))
            throw new RuntimeException("不是Person对象");
        Person p = (Person) obj;
        if (this.age > p.age) {
            return 1;
        }
        if (this.age == p.age) {
            return 0;
        }
        return -1;
    }
}


interface MyComparable<T> {
    int MycomparaTo(T o);
}

interface MyComparator<T> {
    int Mycompare(T t1, T t2);
}

