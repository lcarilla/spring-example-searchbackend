package de.kausimausi.searchbackend;

import de.kausimausi.searchbackend.exception.BadRequestException;
import de.kausimausi.searchbackend.persistence.Person;
import de.kausimausi.searchbackend.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private Controller controller;
    @Mock
    private PersonService personService;
    @BeforeEach
    public void before(){
    }
    @Test
    public void testInvalidPageSize(){
        controller = new Controller(null);
        assertThrows(BadRequestException.class, ()->{
            controller.get("fist", "last", 2, 0);
        });
        assertThrows(BadRequestException.class, ()->{
            controller.get("fist", "last", -1, 4);
        });
    }
    @Test
    public void testGetPersons(){
        var expectedDto = new ResponseDto<>(
                List.of(new Person(1L, "kausi", "mausi")), 2
        );
        when(personService.findByName(
                eq(PageRequest.of(3,2)), eq("kausi"), eq("mausi")
        )).thenReturn(expectedDto);
        controller = new Controller(personService);
        var res = controller.get("kausi", "mausi", 3, 2);
        verify(personService, times(1)).findByName(
               any(), any(), any()
        );
        assertThat(res).isEqualTo(expectedDto);
    }
}
