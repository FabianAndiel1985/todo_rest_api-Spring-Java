package com.example.myrestapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    //zum authentifizieren
    //wichtig der Name muss genauso heissen wie die beiden Felder
    // diese Schreibweise hat etwas mit Jakarta persistence api zu tun
    //What Is Jakarta EE?
    //Jakarta EEis a set of software components, APIs, for developing specifically enterpriseJava applications. These components are often referred to as specifications.
    //What is Jakarta used for?
    //It is often used to connect the front-end (user facing) with the back-end (connection to databases, data processing). Jakarta Context and Dependency Injection (CDI) - provides features that allow for dependency injectio

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findBySecret(String secret);
}
