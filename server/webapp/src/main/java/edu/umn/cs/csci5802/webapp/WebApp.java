package edu.umn.cs.csci5802.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

@Path("/WebApp")
public class WebApp {

  @POST
  @Path("/getResult")
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Result getResult(Numbers numbers) {
    System.out.println("CSCI5802Spring2023: The webapp got a request.");
    Result result = performOperation(numbers);
    return result;
  }

  public Result performOperation(Numbers numbers){
    Mean mean = new Mean();
    double[] numbersArray = new double[numbers.getNumbers().size()];
    for(int i=0; i<numbers.getNumbers().size(); ++i){
      numbersArray[i]=numbers.getNumbers().get(i).doubleValue();
    }
    double meanValue = mean.evaluate(numbersArray);
    Result result = new Result(meanValue);
    return result;
  }
}
