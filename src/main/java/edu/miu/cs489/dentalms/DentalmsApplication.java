package edu.miu.cs489.dentalms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs489.dentalms.auth.AuthenticationService;
import edu.miu.cs489.dentalms.auth.RegisterRequest;
import edu.miu.cs489.dentalms.dto.request.*;
import edu.miu.cs489.dentalms.service.AppointmentService;
import edu.miu.cs489.dentalms.service.DentistService;
import edu.miu.cs489.dentalms.service.PatientService;
import edu.miu.cs489.dentalms.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class DentalmsApplication {
    private final DentistService dentistService;
    private final PatientService patientService;
    private final SurgeryService surgeryService;
    private final AppointmentService appointmentService;
    private final AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication.run(DentalmsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            authenticationService.registerOfficeManager(
                new RegisterRequest(
                        "john",
                        "doe",
                        "admin@mail.com",
                        "111"
                )
            );
            DentistRequestDto[] dentistRequestDtos = new DentistRequestDto[] {
                    new DentistRequestDto(
                    "Tony",
                    "Smith",
                    "(618)-123-456",
                    "tony.smith@dent.com",
                    "111",
                    "Endodontist"),
                    new DentistRequestDto(
                            "Helen",
                            "Pearson",
                            "(618)-123-456",
                            "helen.pear@dent.com",
                            "111",
                            "Orthodontist"),
                    new DentistRequestDto(
                            "Robin",
                            "Plevin",
                            "(618)-123-456",
                            "robin.p@dent.com",
                            "111",
                            "Oral and Maxillofacial Surgeon"),
            };

            PatientRequestDto[] patients = new PatientRequestDto[]{
                    new PatientRequestDto(
                           "Gillian",
                            "White",
                            "(123)-456-789",
                            "gillian.w@mail.com",
                            "111",
                            new AddressRequestDto( "123 Maple Avenue", "Springfield", "IL", "62704")
                    ),
                    new PatientRequestDto(
                            "Jill",
                            "Bell",
                            "(123)-456-789",
                            "jill.b@mail.com",
                            "111",
                            new AddressRequestDto("4502 Oak Street","Austin","TX","78701" )
                    ),
                    new PatientRequestDto(
                            "Ian",
                            "MacKey",
                            "(123)-456-789",
                            "ian.m@mail.com",
                            "111",
                            new AddressRequestDto("789 Pine Road","Denver","CO","80202")
                    ),
                    new PatientRequestDto(
                            "John",
                            "Walker",
                            "(123)-456-789",
                            "john.w@mail.com",
                            "111",
                            new AddressRequestDto("321 Elm Boulevard","Seattle","WA","98101")
                    ),
            };
            SurgeryRequestDto[] surgeries = new SurgeryRequestDto[]{
                    new SurgeryRequestDto(
                        "S15",
                            "(217) 555 0193",
                            new AddressRequestDto("300 Biscayne Way","Miami","FL","33131")
                    ),
                    new SurgeryRequestDto(
                            "S10",
                            "(212) 555 6789",
                            new AddressRequestDto("125 Liberty Street","New York","NY","10005")
                    ),
                    new SurgeryRequestDto(
                            "S13",
                            "(323) 555 0124",
                            new AddressRequestDto("880 Sunset Blvd","Los Angeles","CA", "90028")
                    ),
            };
            Long[] dentistIds = Arrays.stream(dentistRequestDtos)
                    .map(d -> dentistService.createDentist(d).id())
                    .toArray(Long[]::new);
            Long[] patientIds= Arrays.stream(patients)
                    .map((p) -> patientService.createPatient(p).id())
                    .toArray(Long[]::new);
            Long[] surgeryIds = Arrays.stream(surgeries)
                    .map((s) -> surgeryService.createSurgery(s).id())
                    .toArray(Long[]::new);

            AppointmentRequestDto[] appointments = new AppointmentRequestDto[]{
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-12"),
                            LocalTime.parse("10:00"),
                            dentistIds[0],
                            patientIds[0],
                            surgeryIds[0]

                    ),
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-12"),
                            LocalTime.parse("12:00"),
                            dentistIds[0],
                            patientIds[1],
                            surgeryIds[0]
                    ),
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-12"),
                            LocalTime.parse("10:00"),
                            dentistIds[1],
                            patientIds[2],
                            surgeryIds[1]
                    ),
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-14"),
                            LocalTime.parse("14:00"),
                            dentistIds[1],
                            patientIds[2],
                            surgeryIds[1]
                    ),
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-14"),
                            LocalTime.parse("16:30"),
                            dentistIds[2],
                            patientIds[1],
                            surgeryIds[0]
                    ),
                    new AppointmentRequestDto(
                            LocalDate.parse("2013-09-15"),
                            LocalTime.parse("18:00"),
                            dentistIds[2],
                            patientIds[3],
                            surgeryIds[2]
                    ),
            };
            Arrays.stream(appointments).forEach(appointmentService::officeManagerCreateAppointment);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            System.out.println(objectMapper.writeValueAsString(appointmentService.getAllAppointments()));
        };
    }

}
