package com.demo.lms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.lms.LmsApplication;
import com.demo.lms.entity.Subject;
import com.demo.lms.mapper.SubjectMapper;
import com.demo.lms.service.subjectService.SubjectService;

@SpringBootTest(classes = { LmsApplication.class })
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubjectControllerTest {

    public static final String subjectEndPoint = "/subject";

    @MockBean
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(subjectController).build();
    }

    private List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject(1, "maths", new ArrayList<>());
        Subject subject2 = new Subject(2, "physics", new ArrayList<>());
        Subject subject3 = new Subject(3, "chemistry", new ArrayList<>());
        Subject subject4 = new Subject(4, "english", new ArrayList<>());
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        return subjects;
    }

    @Test
    public void getAllSubjectTest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(subjectEndPoint).build().toUri();
        assertEquals(subjectEndPoint, uri.toString());

        when(subjectService.getAllSubject()).thenReturn(
                getAllSubjects().stream().map(sub -> SubjectMapper.mapSubject2DTO(sub)).collect(Collectors.toList()));

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON)).andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(subjectService, times(1)).getAllSubject();
    }
}
