package org.HomeApplianceStore.Ordering;

import java.time.LocalDate;

public class Order {

        private LocalDate date;
        private boolean paidFor;
        private boolean readyForPickUp;
        // cost
        public LocalDate getDate() {
                return date;
        }
        public void setDate(LocalDate date) {
                this.date = date;
        }
        public boolean isPaidFor() {
                return paidFor;
        }
        public void setPaidFor(boolean paidFor) {
                this.paidFor = paidFor;
        }
        public boolean isReadyForPickUp() {
                return readyForPickUp;
        }
        public void setReadyForPickUp(boolean readyForPickUp) {
                this.readyForPickUp = readyForPickUp;
        }
        
}
