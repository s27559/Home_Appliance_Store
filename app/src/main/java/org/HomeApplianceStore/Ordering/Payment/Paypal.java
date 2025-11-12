package org.HomeApplianceStore.Ordering.Payment;

public class Paypal extends PaymentMethod{

        private String paypalAccountId;

        public String getPaypalAccountId() {
                return paypalAccountId;
        }

        public void setPaypalAccountId(String paypalAccountId) {
                this.paypalAccountId = paypalAccountId;
        }
        
}
