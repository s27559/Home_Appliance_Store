package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.*;

public class ClosedFor implements Extent {
        private static ArrayList<ClosedFor> closedForEvents = new ArrayList<>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/ClosedFor.ser";
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        // periodDays;
        private ArrayList<Store> stores;

    public ClosedFor(LocalDate startDate, LocalDate endDate, String reason, ArrayList<Store> stores){
            Validation.validateDates(startDate, endDate);
            Validation.validateString(reason, "Reason");
            this.startDate = startDate;
            this.endDate = endDate;
            this.reason = reason;
            this.stores = stores;
            addClosedForEvent(this);
            saveClosedForEvents();
        }

        public long getPeriodDays() {
            return startDate.until(endDate).getDays();
        }

        private static void addClosedForEvent(ClosedFor event) {
            if(!closedForEvents.contains(event))
                    closedForEvents.add(event);
            saveClosedForEvents();
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
            Objects.requireNonNull(startDate, "Start date cannot be null");
            if(this.endDate != null && this.endDate.isBefore(startDate))
                throw new IllegalArgumentException("Start date cannot be after end date");
            this.startDate = startDate;
            saveClosedForEvents();
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
            Objects.requireNonNull(endDate, "End date cannot be null");
            if (this.startDate != null && endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
            this.endDate = endDate;
            saveClosedForEvents();
        }
        public String getReason() {
                return reason;
        }
        public void setReason(String reason) {
                this.reason = reason;
                saveClosedForEvents();
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
                saveClosedForEvents();
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClosedFor)) return false;
        ClosedFor other = (ClosedFor) o;
        return Objects.equals(startDate, other.startDate)
                && Objects.equals(endDate, other.endDate)
                && Objects.equals(reason, other.reason)
                && Objects.equals(stores, other.stores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, reason, stores);
    }
}
