package org.HomeApplianceStore.Actors;

public class CustomerPerson extends Customer{

        private long points;
        private Person person;

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
