package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//import org.apache.commons.logging.LogFactory;

import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.functions.PDQuadraticMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;
import com.joptimizer.optimizers.OptimizationResponse;

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
//import cern.colt.matrix.linalg.Algebra;
import cern.jet.math.Mult;

public class OptimizerTest {
	
	//private Algebra ALG = Algebra.DEFAULT;
	private DoubleFactory1D F1 = DoubleFactory1D.dense;
	private DoubleFactory2D F2 = DoubleFactory2D.dense;
	
	
	
	public void testPrimalDualMethod() throws Exception {
		
		DoubleMatrix2D PMatrix = F2.make(new double[][] { 
    		{ 1.68, 0.34, 0.38 },
				{ 0.34, 3.09, -1.59 }, 
				{ 0.38, -1.59, 1.54 } });
		DoubleMatrix1D qVector = F1.make(new double[] { 0.018, 0.025, 0.01 });
		
		// Objective function.
		double theta = 0.01522;
		DoubleMatrix2D P = PMatrix.assign(Mult.mult(theta));
		DoubleMatrix1D q = qVector.assign(Mult.mult(-1));
    	PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(P.toArray(), q.toArray(), 0);

    	//equalities
    	double[][] A = new double[][]{{1,1,1}};
		double[] b = new double[]{1};

		//inequalities
		ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[3];
		inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1, 0, 0}, 0);
		inequalities[1] = new LinearMultivariateRealFunction(new double[]{0, -1, 0}, 0);
		inequalities[2] = new LinearMultivariateRealFunction(new double[]{0, 0, -1}, 0);

		OptimizationRequest or = new OptimizationRequest();
		or.setF0(objectiveFunction);
		or.setInitialPoint(new double[] { 0.2, 0.2, 0.6 });
		or.setFi(inequalities);
		or.setA(A);
		or.setB(b);
		or.setToleranceFeas(1.E-12);
		or.setTolerance(1.E-12);
		
		//optimization
		JOptimizer opt = new JOptimizer();
		opt.setOptimizationRequest(or);
		int returnCode = opt.optimize();
		
		if(returnCode == OptimizationResponse.FAILED){
			fail();
		}
		
		OptimizationResponse response = opt.getOptimizationResponse();
		double[] sol = response.getSolution();
		assertEquals(0.04632311555988555, sol[0], 0.000000001);
		assertEquals(0.5086308460954377,  sol[1], 0.000000001);
		assertEquals(0.44504603834467693, sol[2], 0.000000001);
  }

}
