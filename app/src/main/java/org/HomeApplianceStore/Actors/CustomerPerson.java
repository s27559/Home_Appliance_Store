package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class CustomerPerson extends Customer implements Extent {

        private static ArrayList<CustomerPerson> customerPersons = new ArrayList<>();

        static {
                loadCustomerPersons();
        }

        private long points;
        private Person person;

        public CustomerPerson(long points, String name, String email, Address address, Person person) {
            super(name, email, address);
            this.setPoints(points);
                this.person = person;
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
                return person;
        }

        public void delete(){
                super.delete();
                customerPersons.remove(this);
                saveCustomerPersons();
        }
}
