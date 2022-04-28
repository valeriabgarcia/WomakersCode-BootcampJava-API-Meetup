package com.bootcamp.microservicemeetup.service;

import com.bootcamp.microservicemeetup.model.entity.Meetup;
import com.bootcamp.microservicemeetup.model.entity.Registration;
import com.bootcamp.microservicemeetup.repository.MeetupRepository;
import com.bootcamp.microservicemeetup.repository.RegistrationRepository;
import com.bootcamp.microservicemeetup.service.impl.MeetupServiceImpl;
import com.bootcamp.microservicemeetup.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MeetupServiceTest {

    RegistrationService registrationService;
    MeetupService meetupService;

    @MockBean
    RegistrationRepository registrationRepository;

    @MockBean
    MeetupRepository meetupRepository;

    @BeforeEach
    public void setup(){
        this.registrationService = new RegistrationServiceImpl(registrationRepository);
        this.meetupService = new MeetupServiceImpl(meetupRepository);
    }

    @Test
    @DisplayName("Should save a meetup")
    public void saveMeetupTest() {

        //settings
        Meetup meetup = createValidMeetup();
        Registration registration = createValidRegistration();

        //execution
        Mockito.when(registrationRepository.existsByRegistration(Mockito.any())).thenReturn(true);
        Mockito.when(meetupRepository.save(meetup)).thenReturn(createValidMeetup());

        Meetup savedMeetup = meetupService.save(meetup);

        //assert
        assertThat(savedMeetup.getId()).isEqualTo(10);
        assertThat(savedMeetup.getRegistration()).isEqualTo(registration);
        assertThat(savedMeetup.getEvent()).isEqualTo("Resp API Meetup");
    }

    @Test
    @DisplayName("Should get a Meetup by Id")
    public void getByMeetupIdTest() {
        //getById
    }

    @Test
    @DisplayName("Should update a meetup")
    public void updateMeetupTest() {
        //update
    }

    @Test
    @DisplayName("Should filter meetups by properties")
    public void findMeetupsTest() {
        //find
    }




    private Registration createValidRegistration() {
        return  Registration.builder()
                .id(10)
                .name("Valeria Garcia")
                .dateOfRegistration("22/04/2022")
                .registration("001")
                .build();
    }

    private Meetup createValidMeetup(){
        return Meetup.builder()
                .id(10)
                //.registration(createValidRegistration())
                .event("Resp API Meetup")
                .build();
    }
}
