package com.api.sistemasactivos.costumer;

import static org.junit.jupiter.api.Assertions.*;

import com.api.sistemasactivos.costumer.model.Costumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CostumerTest {

    private Costumer costumer;

    @BeforeEach
    public void setUp() {
        costumer = new Costumer();
        costumer.setDni(12345678);
        costumer.setFirstName("John");
        costumer.setAge(30);
        costumer.setDatebirth("1991-01-01");
        costumer.setEmail("john.doe@example.com");
        costumer.setPhone("555-1234");
    }

    @Test
    void testCostumerCreation() {
        assertNotNull(costumer);
        assertEquals(12345678, costumer.getDni());
        assertEquals("John", costumer.getFirstName());
        assertEquals(30, costumer.getAge());
        assertEquals("1991-01-01", costumer.getDatebirth());
        assertEquals("john.doe@example.com", costumer.getEmail());
        assertEquals("555-1234", costumer.getPhone());
        assertNotNull(costumer.getCreatedAt());
        assertNotNull(costumer.getUpdatedAt());
    }

    @Test
    void testCostumerId() {
        assertNull(costumer.getId());
    }

}