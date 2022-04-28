package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class MeetupRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    MeetupRepository meetupRepository;

    @Test
    @DisplayName("Should SAVE a meetup")
    public void saveMeetupTest() {

        //settings
        Registration registration_attribute = createNewRegistration("20");
        Meetup meetup = createNewMeetup(registration_attribute);

        //execution
        Meetup savedMeetup = meetupRepository.save(meetup);

        //assert
        assertThat(savedMeetup.getId()).isNotNull();
    }




    private Registration createNewRegistration(String registration) {
        return Registration.builder()
                .name("Valeria Garcia")
                .dateOfRegistration("22/04/2022")
                .registration(registration).build();
    }

    private Meetup createNewMeetup(Registration registration) {
        return Meetup.builder()
                .id(10)
                .registration(registration)
                .meetupDate("22/04/2022")
                .registered(true)
                .event("Resp API Meetup")
                .build();
    }
}
