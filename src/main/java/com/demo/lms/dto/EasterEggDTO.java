package com.demo.lms.dto;

public class EasterEggDTO {
    private String number;
    private String fact;

    public EasterEggDTO() {
    }

    public EasterEggDTO(String number, String fact) {
        this.number = number;
        this.fact = fact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return "EasterEggDTO [number=" + number + ", fact=" + fact + "]";
    }

}
