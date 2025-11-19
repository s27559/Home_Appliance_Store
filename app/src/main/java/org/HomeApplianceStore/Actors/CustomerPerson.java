package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class CustomerPerson extends Customer implements Extent {

        private static ArrayList<CustomerPerson> customerPersons = new ArrayList<>();

        private long points;
        private Person person;

        public CustomerPerson(Person person, long points) {
                this.setPerson(person);
                this.setPoints(points);
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

        public Person getPerson() {
                return person;
        }

        public void setPerson(Person person) {
                if (person == null) {
                        throw new IllegalArgumentException("Person for CustomerPerson cannot be null");
                }
                this.person = person;
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
}
