package com.moyu.example.structure.reflect;

/**
 * Created by Joker on 19/8/24.
 */
public class Son extends Father {
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;
    int time;
    private String MSG = "Original";

    private final String FINAL_VALUE = "FINAL";
    private final int AG = 100;
    private final Integer AC = 1000;

    private final String NAME ;

    private final String OP = null == null ? "OP" : null;

    private static final String TESTSTATIC = "static";

    private static final Integer STATICINT = 5000;


    public Son() {
        this.NAME = "Joker";
    }

    public String getTESTSTATICValue() {
        return TESTSTATIC;
    }

    public Integer getSTATICINTValue() {
        return STATICINT;
    }

    public String getOPValue() {
        return OP;
    }

    public String getNAMEValue() {
        return NAME;
    }

    public int getAGValue() {
        return AG;
    }

    public Integer getACValue() {
        return AC;
    }

    public String getFinalValue(){
        //剧透，会被优化为: return "FINAL" ,拭目以待吧
        return FINAL_VALUE;
    }

    public String getMsg(){
        return MSG;
    }

    public void printSonMsg(){
        System.out.println("Son Msg - name : "
                + mSonName + "; age : " + mSonAge);
    }

    private void privateMethod(String head , int tail){
        System.out.print(head + tail);
    }

    private void privateMethod2(String head , Integer tail){
        System.out.print(head + tail);
    }

    private void setSonName(String name){
        mSonName = name;
    }

    private void setSonAge(int age){
        mSonAge = age;
    }

    private int getSonAge(){
        return mSonAge;
    }

    private String getSonName(){
        return mSonName;
    }
}
