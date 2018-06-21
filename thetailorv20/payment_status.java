package com.example.junior.thetailorv20;

public class payment_status {
    private float amountDue, amountPaid;
    private String status;
    private int PID;
    private long DID;

    public payment_status(){

    }

    public payment_status(float amountDue, float amountPaid, long DID, int PID){
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.DID = DID;
        this.PID = PID;

        if(getBalance() == 0)
            this.status = "PAID";
        else
            this.status = "NOT PAID";
    }

    public payment_status(float amountDue, float amountPaid){
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        /*this.DID = DID;
        this.PID = PID;*/

        if(getBalance() == 0)
            this.status = "PAID";
        else
            this.status = "NOT_PAID";
    }

    public String getStatus(){
        return status;
    }
    public float getBalance(){
        return amountDue - amountPaid;
    }

    public float getAmountDue() {
        return amountDue;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }
}
