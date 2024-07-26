package eg.pharma.api.features.doctor;

import eg.pharma.api.features.doctor.dto.DoctorMapper;
import eg.pharma.api.features.doctorClass.DoctorClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorMapper doctorMapper;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorClassService doctorClassService;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorService = new DoctorService(doctorMapper, doctorRepository, doctorClassService);
    }

    @Test
    void getDoctorById() {
        Doctor mockDoctor = new Doctor();
        mockDoctor.setId(1L);
        when(doctorRepository.findById(mockDoctor.getId())).thenReturn(Optional.of(mockDoctor));
        doctorService.getDoctorById(mockDoctor.getId());
        verify(doctorRepository).findById(mockDoctor.getId());
    }

    @Test
    void getAllDoctors() {
        doctorService.getAllDoctors();
        verify(doctorRepository).findAll();
    }

    @Test
    void createDoctor() {
    }

    @Test
    void updateDoctor() {
    }

    @Test
    void deleteDoctor() {
    }

    @Test
    void assignClass() {
    }

    @Test
    void getDoctorsByClass() {
    }
}
