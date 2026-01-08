package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Managment.Contract;
import org.HomeApplianceStore.Managment.Leave;

public class Person implements Extent {

        private static ArrayList<Person> persons = new ArrayList<Person>();

        static {
                loadPersons();
        }

        private String name;
        private String middleName;
        private String surname;
        private LocalDate dateOfBirth;
        private Address address;

        public Person(String name, String surname, LocalDate dateOfBirth, Address address) {
                this.setName(name);
                this.setSurname(surname);
                this.setDateOfBirth(dateOfBirth);
                this.setAddress(address);
                addPerson(this);
        }

        public static void addPerson(Person person) {
                if (person == null) {
                        throw new IllegalArgumentException("Person cannot be null");
                }
                persons.add(person);
                savePersons();
        }

        public static void loadPersons() {
                persons = Extent.loadClassList("./org/HomeApplianceStore/Actors/Person.ser");
        }

        public static void savePersons() {
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Person.ser", persons);
        }

        public static List<Person> getPersons() {
                return Extent.getImmutableClassList(persons);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty");
                }
                this.name = name.trim();
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                if (surname == null || surname.trim().isEmpty()) {
                        throw new IllegalArgumentException("Surname cannot be empty");
                }
                this.surname = surname.trim();
        }

        public LocalDate getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
                if (dateOfBirth == null) {
                        throw new IllegalArgumentException("Date of birth cannot be null");
                }
                if (dateOfBirth.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException("Date of birth cannot be in the future");
                }
                this.dateOfBirth = dateOfBirth;
        }

        public Address getAddress() {
                return address;
        }
        public void setAddress(Address address) {
                this.address = address;
        }

        public long getAge(){
                return dateOfBirth.until(LocalDate.now()).getYears();
        }

        public Optional<String> getMiddleName() {
                return Optional.of(middleName);
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }
        
        public void delete(){
                persons.remove(this);
                savePersons();
                for(CustomerPerson customerPerson : CustomerPerson.getCustomerPersons()) {
                        if (customerPerson.getPerson() == this) customerPerson.delete();
                }
                for(Employee employee : Employee.getEmployees()) {
                        if (employee.getPerson() == this) employee.delete();
                }
        }

        private class CustomerPerson extends Customer {

                private static ArrayList<CustomerPerson> customerPersons = new ArrayList<>();

                static {
                        loadCustomerPersons();
                }

                private long points;
                // private Person person;
                
                public CustomerPerson(long points, String name, String email, Address address/*, Person person*/) {
                        super(name, email, address);
                        this.setPoints(points);
                        //this.person = person;
                        addCustomerPerson(this);
                }

                public static void addCustomerPerson(CustomerPerson customerPerson) {
                        if (customerPerson == null) {
                                throw new IllegalArgumentException("CustomerPerson cannot be null");
                        }
                        customerPersons.add(customerPerson);
                        saveCustomerPersons();
                }

                public static void loadCustomerPersons() {
                        customerPersons = Extent.loadClassList("./org/HomeApplianceStore/Actors/CustomerPerson.ser");
                }

                public static void saveCustomerPersons() {
                        Extent.saveClassList("./org/HomeApplianceStore/Actors/CustomerPerson.ser", customerPersons);
                }

                public static List<CustomerPerson> getCustomerPersons() {
                        return Extent.getImmutableClassList(customerPersons);
                }

                public long getPoints() {
                        return points;
                }

                public void setPoints(long points) {
                        if (points < 0) {
                                throw new IllegalArgumentException("Points cannot be negative");
                        }
                        this.points = points;
                }

                public Person getPerson() {
                        return Person.this;
                }

                public void delete(){
                        super.delete();
                        customerPersons.remove(this);
                        saveCustomerPersons();
                }
        }

        private class Employee implements Extent {

                private static ArrayList<Employee> employees = new ArrayList<Employee>();

                static {
                        loadEmployees();
                }

                private BigDecimal bonusPay;
                private long sickDays;
                private long paidLeaveDays;
                private long unpaidLeaveDays;
                // private EmpRole role;
                private ArrayList<Leave> leaves = new ArrayList<>();
        

                // private Person person;

                public Employee(BigDecimal bonusPay,
                                long sickDays,
                                long paidLeaveDays,
                                long unpaidLeaveDays
                                // EmpRole role,
                                // Person person
                                ) {
                        if (bonusPay == null) {
                            throw new IllegalArgumentException("Bonus pay cannot be null");
                        }
                        // if (role == null) {
                        //     throw new IllegalArgumentException("Role cannot be null");
                        // }
                        this.setBonusPay(bonusPay);
                        this.setSickDays(sickDays);
                        this.setPaidLeaveDays(paidLeaveDays);
                        this.setUnpaidLeaveDays(unpaidLeaveDays);
                        // this.role = role;
                        // this.person = person;
                        addEmployee(this);
                }

                public static void addEmployee(Employee employee) {
                        if (employee == null) {
                                throw new IllegalArgumentException("Employee cannot be null");
                        }
                        employees.add(employee);
                        saveEmployees();
                }

                public static void loadEmployees() {
                        employees = Extent.loadClassList("./org/HomeApplianceStore/Actors/Employee.ser");
                }

                public static void saveEmployees() {
                        Extent.saveClassList("./org/HomeApplianceStore/Actors/Employee.ser", employees);
                }

                public static List<Employee> getEmployees() {
                        return Extent.getImmutableClassList(employees);
                }

                public BigDecimal getBonusPay() {
                        return bonusPay;
                }

                public void setBonusPay(BigDecimal bonusPay) {
                        if (bonusPay != null && bonusPay.signum() < 0) {
                                throw new IllegalArgumentException("Bonus pay cannot be negative");
                        }
                        this.bonusPay = bonusPay;
                }

                public long getSickDays() {
                        return sickDays;
                }

                public void setSickDays(long sickDays) {
                        if (sickDays < 0) {
                                throw new IllegalArgumentException("Sick days cannot be negative");
                        }
                        this.sickDays = sickDays;
                }

                public long getPaidLeaveDays() {
                        return paidLeaveDays;
                }

                public void setPaidLeaveDays(long paidLeaveDays) {
                        if (paidLeaveDays < 0) {
                                throw new IllegalArgumentException("Paid leave days cannot be negative");
                        }
                        this.paidLeaveDays = paidLeaveDays;
                }

                public long getUnpaidLeaveDays() {
                        return unpaidLeaveDays;
                }

                public void setUnpaidLeaveDays(long unpaidLeaveDays) {
                        if (unpaidLeaveDays < 0) {
                                throw new IllegalArgumentException("Unpaid leave days cannot be negative");
                        }
                        this.unpaidLeaveDays = unpaidLeaveDays;
                }

                // public EmpRole getRole() {
                //         return role;
                // }

                // public void setRole(EmpRole role) {
                //         this.role = role;
                // }

                public BigDecimal getFullPay(){
                        return new BigDecimal(0);
                }

                public long getLeaveDays(){
                        return paidLeaveDays + unpaidLeaveDays;
                }

                public Person getPerson() {
                        return Person.this;
                }

                public void delete(){
                        employees.remove(this);
                        for(Leave leave : getLeaves()){
                                leave.delete();
                        }
                        saveEmployees(); 
                }

                public void addLeave(Leave leave) {
                    leaves.add(leave);
                }

                public List<Leave> getLeaves() {
                        return Extent.getImmutableClassList(leaves);
                }

        
                public void removeLeave(Leave leave) {
                        leaves.remove(leave);
                }

                public void removeContract(Contract contract) {
                    contract.delete();
                }
        }
}
