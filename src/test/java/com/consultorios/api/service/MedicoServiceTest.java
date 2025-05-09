package com.consultorios.api.service;

import com.consultorios.api.dto.MedicoDTO;
import com.consultorios.api.model.Medico;
import com.consultorios.api.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {
    @InjectMocks
    private MedicoService medicoService;

    @Mock
    private MedicoRepository medicoRepository;

    private Medico medico;
    private MedicoDTO medicoDTO;

    @BeforeEach
    void setUp() {
        medico = Medico.builder()
                .id(1L)
                .nombre("Dr. Juan")
                .email("juan@correo.com")
                .telefono("1234567890")
                .especilidad("Cardiología")
                .build();

        medicoDTO = MedicoDTO.builder()
                .id(1L)
                .nombre("Dr. Juan")
                .email("juan@correo.com")
                .telefono("1234567890")
                .especilidad("Cardiología")
                .build();
    }

    @Test
    void saveTest() {
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);
        MedicoDTO saved = medicoService.save(medicoDTO);

        assertEquals(medicoDTO.getNombre(), saved.getNombre());
        verify(medicoRepository).save(any(Medico.class));
    }

    @Test
    void findByIdTest() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        Optional<MedicoDTO> result = medicoService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Dr. Juan", result.get().getNombre());
    }

    @Test
    void findAllTest() {
        when(medicoRepository.findAll()).thenReturn(List.of(medico));
        List<MedicoDTO> list = medicoService.findAll();

        assertEquals(1, list.size());
        assertEquals("Dr. Juan", list.get(0).getNombre());
    }

    @Test
    void updateTest() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        Optional<MedicoDTO> updated = medicoService.update(1L, medicoDTO);

        assertTrue(updated.isPresent());
        assertEquals("Dr. Juan", updated.get().getNombre());
    }

    @Test
    void deleteTest() {
        when(medicoRepository.existsById(1L)).thenReturn(true);
        boolean result = medicoService.delete(1L);

        assertTrue(result);
        verify(medicoRepository).deleteById(1L);
    }
}
