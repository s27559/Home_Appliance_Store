package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod implements Extent {
        private static ArrayList<PaymentMethod> methods = new ArrayList<PaymentMethod>();

        private String name;
        public PaymentMethod(String name) {
                this.setName(name);
                addMethod(this);
        }

        private static void addMethod(PaymentMethod method){
                methods.add(method);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
        public static void loadMethods(){
                methods = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/PaymentMethod.ser");
        }

        public static void saveMethods(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/PaymentMethod.ser", methods);
        }

        public static List<PaymentMethod> getMethods() {
                return Extent.getImmutableClassList(methods);
        }

}
