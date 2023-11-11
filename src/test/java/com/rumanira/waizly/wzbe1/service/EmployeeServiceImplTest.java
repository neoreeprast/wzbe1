package com.rumanira.waizly.wzbe1.service;

import com.rumanira.waizly.wzbe1.entity.Employee;
import com.rumanira.waizly.wzbe1.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        employees.add(new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now));
        employees.add(new Employee(2L, "Jane Smith","Sales", 10000, "Marketing", LocalDate.parse("2021-08-14"),now));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Manager", result.get(0).getJobTitle());
        assertEquals(20000, result.get(0).getSalary());
        assertEquals("IT", result.get(0).getDepartment());
        assertEquals("2021-08-16", result.get(0).getJoinedDate().toString());

        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals("Sales", result.get(1).getJobTitle());
        assertEquals(10000, result.get(1).getSalary());
        assertEquals("Marketing", result.get(1).getDepartment());
        assertEquals("2021-08-14", result.get(1).getJoinedDate().toString());

        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testGetEmployeeById() {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("John Doe", result.getName());
        assertEquals("Manager", result.getJobTitle());
        assertEquals(20000, result.getSalary());
        assertEquals("IT", result.getDepartment());
        assertEquals("2021-08-16", result.getJoinedDate().toString());

        verify(employeeRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.getEmployeeById(1L));

        verify(employeeRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testSaveEmployee() {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now);

        employeeService.saveEmployee(employee);

        verify(employeeRepository, times(1)).save(employee);
        verifyNoMoreInteractions(employeeRepository);
    }
    @Test
    public void testUpdateEmployee() {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now);
        Employee updatedEmployee = new Employee(1L, "Jane Smith","Sales", 10000, "Marketing", LocalDate.parse("2021-08-16"),now);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.updateEmployee(updatedEmployee);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(updatedEmployee);
        verifyNoMoreInteractions(employeeRepository);

        assertEquals("Jane Smith", employee.getName());
        assertEquals("Sales", employee.getJobTitle());
        assertEquals(10000, employee.getSalary());
        assertEquals("Marketing", employee.getDepartment());
        assertEquals("2021-08-16", employee.getJoinedDate().toString());
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now);
        Employee updatedEmployee = new Employee(1L, "Jane Smith","Sales", 10000, "Marketing", LocalDate.parse("2021-08-14"),now);

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.updateEmployee(updatedEmployee));

        verify(employeeRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testDeleteEmployee() {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1L, "John Doe","Manager", 20000, "IT", LocalDate.parse("2021-08-16"),now);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(1L));

        verify(employeeRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }
}
