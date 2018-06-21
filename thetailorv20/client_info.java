package com.example.junior.thetailorv20;

public class client_info {

    private String clname, clphone, clmail;
    private int designCount, sampleCount, SN;
    public static  int count = 9;

    public client_info(){

    }

    public client_info(String clname, String clphone, String clmail) {

        this.clname = clname;
        this.clphone = clphone;
        this.clmail = clmail;
        designCount = 0;
        sampleCount = 0;
        SN = count;
        count++;
    }

    public int getID() {
        return SN;
    }

    public String getClname(){
        return clname;
    }
    public String getClmail(){
        return clmail;
    }
    public  String getClphone(){
        return  clphone;
    }
    public void setSampleCount(){
        this.sampleCount++;
    }
    public void setDesignCount(){
        this.designCount++;
    }
    public void setDesignAndSampleCount(){
        this.sampleCount++;
        this.designCount++;
    }
}