package com.me.gacl;

import com.me.gacl.domain.Person;
import com.me.gacl.service.PersonService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author yunhua.he
 * @date 2017/12/22
 */
public class PersonTest extends BaseTest {

    @Autowired
    private PersonService personService;

    @Test
    public void getPerson(){
        Person person = personService.getPerson("may");
        System.out.println(person);
    }

    @Test
    public void addPerson(){
        Person person = new Person();
        person.setId(6);
        person.setName("Jhon");
        person.setAge(28);
        boolean flag = personService.addPerson(person);
        System.out.println(flag);
    }

    /**
     * 纯粹的mybatis,无controller,service,dao分层
     */
    String resource = "/config/mybatis.xml";
    InputStream in = PersonTest.class.getClassLoader().getResourceAsStream(resource);
    SqlSessionFactory sqlFactory = new SqlSessionFactoryBuilder().build(in);
    SqlSession sqlSession = sqlFactory.openSession();
    @Test
    public void testUpdate() {
        Person person = new Person();
        person.setId(16);
        person.setName("Adlin");
        //updatePerson是mapper.xml中的sql的id
        int result = sqlSession.update("updatePerson", person);
        sqlSession.commit();
        System.out.println(result);
        sqlSession.close();
    }
}
