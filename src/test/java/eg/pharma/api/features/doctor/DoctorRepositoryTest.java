package eg.pharma.api.features.doctor;

import eg.pharma.api.enums.Specialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void itShouldReturnListOfDoctors() {
        List<Doctor> doctors = List.of(
                new Doctor(
                        "John",
                        "Doe",
                        Specialization.CARDIOLOGY,
                        "Tulsa",
                        "01001010101",
                        "01001010101"
                ),
                new Doctor(
                        "Jane",
                        "Doe",
                        Specialization.ONCOLOGY,
                        "NYC",
                        "01001010101",
                        "01001010101"
                )
        );
        doctorRepository.saveAll(doctors);

        List<Doctor> doctorList = doctorRepository.findAll();

        assertThat(doctorList.size()).isGreaterThan(0);
    }
}
