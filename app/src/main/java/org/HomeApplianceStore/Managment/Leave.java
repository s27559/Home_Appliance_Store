package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Leave implements Extent {
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Leave.ser";
        private static ArrayList<Leave> leaves = new ArrayList<>();

        private boolean isSick;
        private boolean isPaid;
        private LocalDate startDate;
        private LocalDate endDate;

        public Leave() {
                addLeave(this);
        }

        private static void addLeave(Leave leave) {
                leaves.add(leave);
        }

        // periodDays
        public boolean isSick() {
                return isSick;
        }
        public void setSick(boolean isSick) {
                this.isSick = isSick;
        }
        public boolean isPaid() {
                return isPaid;
        }
        public void setPaid(boolean isPaid) {
                this.isPaid = isPaid;
        }
        public LocalDate getStartDate() {
                return startDate;
        }
        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }

        // extends methods
        public static void loadLeaves() {
                leaves = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveLeaves() {
                Extent.saveClassList(FILE_LOCATION, leaves);
        }

        public static List<Leave> getLeaves() {
                return Extent.getImmutableClassList(leaves);
        }

        public void delete() {
                leaves.remove(this);
        }
}
