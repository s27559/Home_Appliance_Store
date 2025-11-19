package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;

public class CustomerPerson extends Customer implements Extent{
        private static ArrayList<CustomerPerson> customerPersons = new ArrayList<>();

        private long points;
        private Person person;

        public CustomerPerson(long points) {
                super();
                this.points = points;
        }

        public static List<CustomerPerson> getCustomerPersons() {
                return Extent.getImmutableClassList(customerPersons);
        }

        public static void loadCustomerPerson(){
                customerPersons = Extent.loadClassList("./org/HomeApplianceStore/Actors/CustomerPerson.ser");
        }

        public static void saveCustomerPersons(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/CustomerPerson.ser", customerPersons);
        }

        private static void addCustomerPersons(CustomerPerson customerPerson) {
                customerPersons.add(customerPerson);
        }

        public Person getPerson() {
                return person;
        }

        public void setPerson(Person person) {
                this.person = person;
        }

        public long getPoints() {
                return points;
        }

        public void setPoints(long points) {
                this.points = points;
        }

}
