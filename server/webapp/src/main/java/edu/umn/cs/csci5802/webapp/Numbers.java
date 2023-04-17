package edu.umn.cs.csci5802.webapp;

import java.util.List;

public class Numbers {

  private List<Double> numbers;

  public Numbers(){

  }

  public Numbers(List<Double> numbers){
    this.numbers=numbers;
  }

  public List<Double> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<Double> numbers){
    this.numbers=numbers;
  }
}
