package application;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;*/
import org.ejml.simple.SimpleMatrix;

//import cern.colt.matrix.DoubleFactory1D;
//import cern.colt.matrix.DoubleFactory2D;
//import cern.colt.matrix.DoubleMatrix1D;
//import cern.colt.matrix.DoubleMatrix2D;
//import cern.colt.matrix.linalg.Algebra;
//import cern.jet.math.Functions;
//import cern.jet.math.Mult;

import com.joptimizer.functions.ConvexMultivariateRealFunction;
//import com.joptimizer.functions.FunctionsUtils;
import com.joptimizer.functions.LinearMultivariateRealFunction;
//import com.joptimizer.functions.LogTransformedPosynomial;
import com.joptimizer.functions.PDQuadraticMultivariateRealFunction;
//import com.joptimizer.functions.PSDQuadraticMultivariateRealFunction;
//import com.joptimizer.functions.StrictlyConvexMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;
import com.joptimizer.optimizers.OptimizationResponse;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import cern.jet.math.Mult;


public class Optimizer {
	
	private Algebra ALG = Algebra.DEFAULT;
	private DoubleFactory1D F1 = DoubleFactory1D.dense;
	private DoubleFactory2D F2 = DoubleFactory2D.dense;
	private Log log = LogFactory.getLog(this.getClass().getName());

	
	public void testQuadraticProgramming2Dmc(SimpleMatrix cov, SimpleMatrix mean, double ret) throws Exception {
		
		log.debug("testQuadraticProgramming2Dmc");
		
        int size = cov.numCols();
		
		double[][] P = new double[size][size];
		
		double[] re = new double[size];
		
		double[] init = new double[size];
		
		for(int r=0; r<size; r++){
            
			re[r] = -mean.get(0,r);
			
			init[r] = 1/size;
			
			for(int c=0; c<size; c++) { P[r][c] = cov.get(r, c); }
            
            
        }
		
		// Objective function
		//P = new double[][] {{ 1., 0.4 }, { 0.4, 1. }};
		PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(P, null, 0);

		//equalities
		double[] a = new double[size];
    	Arrays.fill(a,1);
    	double[][] A = new double[][]{a};
		double[] b = new double[]{1};

		//inequalities
		ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[1];
		//inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1, 0, 0}, 0);
		//inequalities[1] = new LinearMultivariateRealFunction(new double[]{0, -1, 0}, 0);
		inequalities[0] = new LinearMultivariateRealFunction(re, ret);
		
		//optimization problem
		OptimizationRequest or = new OptimizationRequest();
		or.setF0(objectiveFunction);
		or.setInitialPoint(init);
		//or.setFi(inequalities); //if you want x>0 and y>0
		or.setA(A);
		or.setB(b);
		or.setToleranceFeas(1.E-12);
		or.setTolerance(1.E-12);
		
		//optimization
		JOptimizer opt = new JOptimizer();
		opt.setOptimizationRequest(or);
		int returnCode = opt.optimize();
		
	  if(returnCode==OptimizationResponse.FAILED){
		  System.out.println("fail");;
		}
		
		OptimizationResponse response = opt.getOptimizationResponse();
		double[] sol = response.getSolution();
		log.debug("sol: " + ArrayUtils.toString(sol));
		log.debug("value  : " + objectiveFunction.value(sol));
		System.out.println(sol[0]);
		System.out.println(sol[0]);
    }
	
	
	public double[][] testPrimalDualMethod(SimpleMatrix cov, SimpleMatrix mean) throws Exception {
		log.debug("testPrimalDualMethod");
		
		int size = cov.numCols();
		
		double[][] P = new double[size][size];
		
		double[] re = new double[size];
		
		double[] init = new double[size];
		
		double ret = mean.elementSum()/(size) -1;
		
		//System.out.println(ret);
		
		for(int r=0; r<size; r++){
            
			re[r] = -mean.get(r,0);
			
			//System.out.println(re[r]);
			
			init[r] = 1/size;
			
			for(int c=0; c<size; c++) { P[r][c] = cov.get(r, c); }
            
            
        }
		
		
		//DoubleMatrix1D qVector = F1.make(new double[] { 0, 0, 0 });
		
		// Objective function.
		//double theta = 0.5;
		//DoubleMatrix2D P = PMatrix.assign(Mult.mult(theta));
		//DoubleMatrix1D q = qVector.assign(Mult.mult(-1));
    	PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(P, null, 0);
        
    	//equalities
    	double[] a = new double[size];
    	Arrays.fill(a,1);
    	double[][] A = new double[][]{a};
    	
		double[] b = new double[]{1};

		//inequalities
		//ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[size+1];
		//ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[1];
		//inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1, 0, 0}, 0);
		//inequalities[1] = new LinearMultivariateRealFunction(new double[]{0, -1, 0}, 0);
		//inequalities[0] = new LinearMultivariateRealFunction(re, ret);

		OptimizationRequest or = new OptimizationRequest();
		or.setF0(objectiveFunction);
		or.setInitialPoint(init);
		//or.setFi(inequalities);
		or.setFi(null);
		or.setA(A);
		or.setB(b);
		or.setToleranceFeas(1.E-12);
		or.setTolerance(1.E-12);
		
		//optimization
		JOptimizer opt = new JOptimizer();
		opt.setOptimizationRequest(or);
		int returnCode = opt.optimize();
		
		if(returnCode == OptimizationResponse.FAILED){
			System.out.println("fail");
		}
		
		OptimizationResponse response = opt.getOptimizationResponse();
		double[] sol = response.getSolution();
		
		double[][] final_sol = new double[][] {sol};
		
		return final_sol;
		
		//log.debug("sol   : " + ArrayUtils.toString(sol));
		//log.debug("value : " + objectiveFunction.value(sol));
		//assertEquals(0.04632311555988555, sol[0], 0.000000001);
		//assertEquals(0.5086308460954377,  sol[1], 0.000000001);
		//assertEquals(0.44504603834467693, sol[2], 0.000000001);
  }
	
	
	public static void main(String[] args) throws Exception {
		
		Optimizer optest = new Optimizer();
		
		//optest.testQuadraticProgramming2Dmc();
		
		
	}
    

}
