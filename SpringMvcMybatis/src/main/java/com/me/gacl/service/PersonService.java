package com.me.gacl.service;

import com.me.gacl.domain.Person;

/**
 * Created by CH-yfy on 2017/8/23.
 */
public interface PersonService {

    Person getPerson(String name);

    boolean addPerson(Person person);
}
