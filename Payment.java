public class Payment {
    private Bill bill;
    private double amountPaid;

    public Payment(Bill bill, double amountPaid) {
        this.bill = bill;
        this.amountPaid = amountPaid;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "Payment [bill=" + bill + ", amountPaid=" + amountPaid + "]";
    }
}

