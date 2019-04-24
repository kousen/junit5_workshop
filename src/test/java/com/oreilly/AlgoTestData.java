package com.oreilly;

public class AlgoTestData {
    private final long num;
    private final long result;

    public AlgoTestData(long num, long result) {
        this.num = num;
        this.result = result;
    }

    public long getNum() {
        return num;
    }

    public long getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "num=" + num +
                ", result=" + result +
                '}';
    }
}
