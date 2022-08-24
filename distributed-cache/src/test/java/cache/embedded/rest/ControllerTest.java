//package cache.embedded.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import cache.embedded.rest.Car;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest(classes= {cache.embedded.rest.ControllerTest.class})
//@AutoConfigureMockMvc
//class ControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
////    @Autowired
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void putGet() throws Exception {
//        // given
//        String number = "J46HNT";
//        Car car = Car.builder()
//                .number(number)
//                .name("Lamborghini")
//                .build();
//
//        // put
//        String content = objectMapper.writeValueAsString(car);
//        mockMvc.perform(
//                post("/cars")
//                        .content(content)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//        ).andExpect(status().isCreated());
//
//        // get
//        String json = mockMvc.perform(
//                get("/cars/" + number))
//                .andExpect(status().isOk()
//        ).andReturn().getResponse().getContentAsString();
//        Car response = objectMapper.readValue(json, Car.class);
//
//        assertThat(response).isEqualToComparingFieldByField(car);
//
//    }
//}