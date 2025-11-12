package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClosedFor implements Extent {
        private static ArrayList<ClosedFor> closedForEvents = new ArrayList<>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/ClosedFor.ser";
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        // periodDays;
        private ArrayList<Store> stores;

        public ClosedFor(){
                addClosedForEvent(this);
        }

        private static void addClosedForEvent(ClosedFor event) {
                closedForEvents.add(event);
        }

        public ArrayList<Store> getStores() {
                return stores;
        }
        public void setStores(ArrayList<Store> stores) {
                this.stores = stores;
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
        public String getReason() {
                return reason;
        }
        public void setReason(String reason) {
                this.reason = reason;
        }

        // extend methods
        public static void loadClosedForEvents() {
                closedForEvents = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveClosedForEvents() {
                Extent.saveClassList(FILE_LOCATION, closedForEvents);
        }

        public static List<ClosedFor> getClosedForEvents() {
                return Extent.getImmutableClassList(closedForEvents);
        }

        public void delete() {
                closedForEvents.remove(this);
        }
}
