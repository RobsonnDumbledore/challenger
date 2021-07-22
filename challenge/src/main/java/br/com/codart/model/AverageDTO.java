package br.com.codart.model;

public class AverageDTO {

    private Double average;

    public AverageDTO() {
    }

    public AverageDTO(Double valueA, Double valueB) {
        this.average = (valueA + valueB) / 2;
    }

    public Double getAverage() {
        return average;
    }

}
