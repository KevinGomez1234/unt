package org.example;

import org.example.json.ReceivePersonRequest.PersonRequest;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.example.client.person.PersonClient;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit test for simple App.
 *
 * Differences between Junit5 and Junit4 with SpringBootTest
 * If you are using Junit4 you would use RunWith for the extra extension being used RunWith(MockitoJunitRunner.class)
 * If you are using Junit5 Use ExtendedWith({MockitoExtension.class})
 *
 * Another difference is initializing the Mockbeans
 * Junit4 @Before setup(){MockitoAnnotations.initMocks(this)}
 * Junit5 @BeforeEach setup(){MockitoAnnotations.openMocks(this)}
 *
 * Final difference is that you do not use injectmocks with junit 5 instead you use autowired for the Sut
 */

@ExtendWith({MockitoExtension.class})
@SpringBootTest
public class AppTest
{
    private AutoCloseable closeable;

    @MockBean
    PersonClient personClient;
    @Autowired
    ReceivePersonController controller;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    //Old way of mocking in mockito is to use Mockito.mock(Class you want to mock)
//    @Test
//    public void testName_ShouldReturn_Kiwi(){
//        Car mockedCar = Mockito.mock(Car.class);
//        Mockito.when(mockedCar.getName()).thenReturn("Toyota");
//        assertEquals(mockedCar.getName(), "Toyota");
//        assertTrue(mockedCar instanceof Car, "Is the mocked car an instance of Car?: ");
//        Mockito.verify(mockedCar).getName(); //verifies the mockedCar getName waa called once
//    }
//
//    //assertThrows instead of @Test (expected = Excepetion.class) also disabled test
//    @Test
//    @Disabled
//    public void testingExceptions_ShouldThrowAnException(){
//        Exception exception = assertThrows(ArithmeticException.class, () -> {
//            int x = 1 / 0;
//        } );
//        System.out.println(exception);
//        assertEquals("/ by zero", exception.getMessage());
//    }
//
//    //Example failing a test
//    @Test
//    public void testVerify_ShouldReturnFailedAssertion(){
//        Car c = Mockito.mock(Car.class);
//        Mockito.when(c.getName()).thenReturn("Express");
//        // c.getName(); this will make the test pass since there was interaction with this mocked object
//        Mockito.verify(c).getName();
//    }
//
//    @Test
//    public void genericTest(){
//        Set<String> set = new HashSet<>();
//        set.add("A");
//        set.add("B");
//        for(String str: set){
//            System.out.println(str);
//        }
//    }

    @Test
    public void testGetPerson(){
        //the SUT is the Controller... therefore the spy is the personClient which we stub and verify a specific method from it was called, in this case our controller initialized this call once
        when(personClient.getPersonById("1234")).thenReturn(new RegularPerson("1234", "Kevin", "dob", Collections.emptyList()));
        PersonRequest pReq = new PersonRequest();
        pReq.setId("1234");
        controller.getEmployee(pReq);
        verify(personClient, times(1)).getPersonById("1234");
    }

    @Test
    public void testNoValidRequest(){
        //the SUT is the Controller... therefore the spy is the personClient which we stub and verify a specific method from it was called, in this case our controller initialized this call once
        when(personClient.getPersonById("1234")).thenReturn(new RegularPerson("1234", "Kevin", "dob", Collections.emptyList()));
        PersonRequest pReq = new PersonRequest();
        String response = controller.getEmployee(pReq);
        assertEquals("Empty Id", response);
        verify(personClient, never()).getPersonById("1234");
    }




}
