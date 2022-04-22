package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Registration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    RegistrationRepository repository;


    @Test
    @DisplayName("Should return TRUE when exists a registration already created")
    public void returnTrueWhenRegistrationExists() {

        //settings
        String registration = "123";
        Registration registration_attribute = createNewRegistration(registration);

        //execution
        entityManager.persist(registration_attribute);
        boolean exists = repository.existsByRegistration(registration);

        //assert
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return FALSE when doesn't exists a registration_attribute with a registration already created")
    public void returnFalseWhenRegistrationAttributeDoesntExists() {

        //settings
        String registration = "123";

        //execution
        boolean exists = repository.existsByRegistration(registration);

        //assert
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Should get a registration by id")
    public void findByIdTest() {

        //settings
        Registration registration_attribute = createNewRegistration("555");
        entityManager.persist(registration_attribute);

        //execution
        Optional<Registration> foundRegistration = repository
                .findById(registration_attribute.getId());

        //assert
        assertThat(foundRegistration.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should SAVE a registration")
    public void saveRegistrationTest() {

        //settings
        Registration registration_attribute = createNewRegistration("323");

        //execution
        Registration savedRegistration = repository.save(registration_attribute);

        //assert
        assertThat(savedRegistration.getId()).isNotNull();
    }

    @Test
    @DisplayName("Should DELETE a registration from the base")
    public void deleteRegistration() {

        //settings
        Registration registration_attribute = createNewRegistration("323");
        entityManager.persist(registration_attribute);

        //execution
        Registration foundRegistration = entityManager
                .find(Registration.class, registration_attribute.getId());
        repository.delete(foundRegistration);

        Registration deleteRegistration = entityManager
                .find(Registration.class, registration_attribute.getId());

        //assert
        assertThat(deleteRegistration).isNull();
    }

    private Registration createNewRegistration(String registration) {
        return Registration.builder()
                .name("Valeria Garcia")
                .dateOfRegistration("20/04/2022")
                .registration(registration).build();
    }
}
