package application;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import java.lang.Math;

public class RiskValuator {
	
	/*return variables*/
	private String riskRating;
	private double sharpeRatio;
	private double drawDown;
	
	
	/*set up variables*/
	private double[][] histReturn;
	
	
	/*constructor*/
	/*@param the hisReturn come from Analysis*/
	public RiskValuator(double[][] histReturn) {
		
		this.histReturn = histReturn;
		
	}
	
	
	/**
	 * update the riskRating sharpeRatio drawDown
	 * @param  private double[][] histReturn;
	 */
	
	public void RiskValuatorCompute() {
		
		SimpleMatrix X = new SimpleMatrix(histReturn);
		
		SimpleMatrix Xt = X.transpose();
		
		ArrayList<SimpleMatrix> result = varianceCompute(Xt);
		
		double averageReturn = result.get(0).get(0, 0);
		
		double variance = result.get(1).get(0, 0);
		
		double spyVariance = result.get(1).get(1, 1);
		
		sharpeRatio = averageReturn/Math.sqrt(variance);
		

		if (variance > 1.5*spyVariance) {
			
			riskRating = "high risk";
		
		}else if(variance < 0.5*spyVariance) {
			
			riskRating = "low risk";
		
		}else {
			
			riskRating = "mid risk";
		}
		
		
		drawDown = drawDownCompute(histReturn[0]);
		
		
		
	}
	
	
	public static ArrayList<SimpleMatrix> varianceCompute(SimpleMatrix X) {
		
		ArrayList<SimpleMatrix> result = new ArrayList<SimpleMatrix>();
		
		//SimpleMatrix X = new SimpleMatrix(returnD);
        int n = X.numRows();
        SimpleMatrix Xt = X.transpose();
        int m = Xt.numRows();

        // Means:
        SimpleMatrix x = new SimpleMatrix(m, 1);
        for(int r=0; r<m; r++ ){
            x.set(r, 0, Xt.extractVector(true, r).elementSum() / n);
        }
        //System.out.println(x);
        
        result.add(x);
        
        // Covariance matrix:
        SimpleMatrix S = new SimpleMatrix(m, m);
        for(int r=0; r<m; r++){
            for(int c=0; c<m; c++){
                if(r > c){
                    S.set(r, c, S.get(c, r));
                } else {
                    double cov = Xt.extractVector(true, r).minus( x.get((r), 0) ).dot(Xt.extractVector(true, c).minus( x.get((c), 0) ).transpose());
                    S.set(r, c, (cov / n));
                }
            }
        }
        
        result.add(S);
        
        return result;
	}
	
	
	
	
	public static double drawDownCompute(double[] portReturn) {
		
		double tempDraw1 = 0;
		
		double tempDraw2 = 0;
		
		for(int i = 0; i < portReturn.length; i++) {
			
			if (portReturn[i] < 0) {
				
				tempDraw2 = tempDraw2 +  portReturn[i];
				
			}
			else {
				
				//update the drawDown
				if(tempDraw2 < tempDraw1) {
					
					tempDraw1 = tempDraw2;
					
					tempDraw2 = 0;
				}else {
					
					tempDraw2 = 0;
					
				}
			}
		
		}
		
		return tempDraw1;
		
	}


	public double getSharpeRatio() {
		return sharpeRatio;
	}


	public double getDrawDown() {
		return drawDown;
	}


	public String getRiskRating() {
		return riskRating;
	}
	
	

}
