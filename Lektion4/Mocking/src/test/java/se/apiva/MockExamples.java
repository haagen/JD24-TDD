package se.apiva;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockExamples {

    @Test
    public void checkNoAvailability(){

        // Assemble
        OrderService orderService = new OrderService(new InventoryService() {
            @Override
            public Boolean checkAvailability(Integer productId, Integer quantity) {
                return false;
            }
        });

        // Act
        Boolean result = orderService.placeOrder(4, 2);

        // Assert
        assertFalse(result);

    }

    @Test
    public void checkHasAvailability() {

        // Implementerar interfaces genom ett "functional interface"
        // fungerar endast om interfacet har en metod
        // dvs. nedan implementerar vi checkAvailability() med en lambda
        OrderService orderService = new OrderService(
                (productId, quantity) -> true
        );

        assertTrue(orderService.placeOrder(4, 2));
    }

    @Test
    public void checkAvailabilityWithMockito() {

        // 1. Skapa en mock instans av InventoryService
        InventoryService mockInventoryService = mock(InventoryService.class);

        // 2. Definiera beteendet (stubbar)
        when(mockInventoryService.checkAvailability(4, 33))
            .thenReturn(true);
        when(mockInventoryService.checkAvailability(4, 22))
            .thenReturn(false);

        // 3. Skapa en instans av OrderService med mockat Inventory
        OrderService orderService = new OrderService(mockInventoryService);

        // 4. Anropa och testa beteende
        // False p.g.a. default beteende av mockito dvs boolean -> false
        assertFalse(orderService.placeOrder(4, 2));
        // True p.g.a. stubbing i steg 2
        assertTrue(orderService.placeOrder(4, 33));
        // False p.g.a. stubbing i steg 2
        assertFalse(orderService.placeOrder(4, 22));

        verify(mockInventoryService).checkAvailability(4, 2);
        verify(mockInventoryService, times(3))
            .checkAvailability(anyInt(), anyInt());
    }

    @Test
    public void exceptionToBeThrownOnNegativeProductId() {

        InventoryService mockInventoryService = mock(InventoryService.class);
        doThrow(new RuntimeException("Bad Id"))
                .when(mockInventoryService).checkAvailability(-1, 1);
        OrderService orderService = new OrderService(mockInventoryService);

        assertThrows(
            RuntimeException.class,
            () -> orderService.placeOrder(-1, 1)
        );

    }

    @Test
    public void exceptionToBeThrownOnNegativeQuantity() {
        InventoryService mockInventoryService = mock(InventoryService.class);
        doAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                int productId = (Integer) invocationOnMock.getArgument(0);
                int quantity = (Integer) invocationOnMock.getArgument(1);

                if (quantity < 0) {
                    throw new IllegalArgumentException("Bad Quantity");
                }

                if(productId < 0){
                    throw new RuntimeException("Bad ProductId");
                }

                return true;
            }
        }).when(mockInventoryService).checkAvailability(anyInt(), anyInt());
        OrderService orderService = new OrderService(mockInventoryService);

        assertTrue(orderService.placeOrder(1, 2));

        assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(-9, 2)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.placeOrder(1, -2)
        );


    }

    @Test
    public void checkPlaceOrderWithMockito() {

        // Arrange
        InventoryService mockService = mock(InventoryService.class);
        OrderService orderService = new OrderService(mockService);
        when(mockService.checkAvailability(anyInt(), anyInt())).thenReturn(true);

        // Act
        Boolean result1 = orderService.placeOrder(1, 2);
        Boolean result2 = orderService.placeOrder(2, 4);

        // Assert
        assertTrue(result1);
        assertTrue(result2);

        // Create an ArgumentCaptor
        ArgumentCaptor<Integer> productIdCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> quantityCaptor = ArgumentCaptor.forClass(Integer.class);

        // Fetch argument using verify
        verify(mockService, times(2)).checkAvailability(
                productIdCaptor.capture(),
                quantityCaptor.capture()
        );
        //verify(mockService, times(2)).checkAvailability(anyInt(), quantityCaptor.capture());

        Integer productId1 = productIdCaptor.getAllValues().get(0);
        Integer quantity1 = quantityCaptor.getAllValues().get(0);
        assertAll(
                "Testing first call",
                () -> assertEquals(1, productId1),
                () -> assertEquals(2, quantity1)
        );

        Integer productId2 = productIdCaptor.getAllValues().get(1);
        Integer quantity2 = quantityCaptor.getAllValues().get(1);
        assertAll(
                "Testing first call",
                () -> assertEquals(2, productId2),
                () -> assertEquals(4, quantity2)
        );

    }


    @Test
    public void canWeMockStaticMethods() {

        try(MockedStatic<MathUtil> mocked = mockStatic(MathUtil.class)) {
            // Assemble
            mocked.when(() -> MathUtil.isPrime(7)).thenReturn(true);
            mocked.when(() -> MathUtil.isPrime(8)).thenReturn(false);

            // Act & Assert
            assertTrue(MathUtil.isPrime(7));
            assertFalse(MathUtil.isPrime(8));

            mocked.verify(() -> MathUtil.isPrime(7), times(1));
            mocked.verify(() -> MathUtil.isPrime(8), times(1));
            mocked.verify(() -> MathUtil.isPrime(anyInt()), times(2));
        }

    }


}
