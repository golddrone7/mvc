package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {
    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest(){
        //given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(2);
        // when
        repository.savePerson(p);

        // then
    }

    @Test
    void removePersonTest(){
        // given
        long id = 4L;
        // when
        repository.removePerson(id);
    }


    @Test
    void updatePersonTest(){
        //given
        Person p = new Person();
        p.setId(6L);
        p.setPersonName("만지호");
        p.setPersonAge(10000);
        //when
        boolean flag = repository.modify(p);
        //then
        assertTrue(flag);

    }

    @Test
    void findAllTest(){
        List<Person> personList = repository.findAll();
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
    }
    @Test
    void findOneTest(){
        Person p = repository.findOne(5L);
        System.out.println("p = " + p);
        assertEquals("춘식이", p.getPersonName());
    }

}