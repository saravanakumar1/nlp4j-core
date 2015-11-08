/**
 * Copyright 2015, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.mathcs.nlp.learning.optimization.method;

import edu.emory.mathcs.nlp.common.util.MathUtils;
import edu.emory.mathcs.nlp.learning.optimization.reguralization.RegularizedDualAveraging;
import edu.emory.mathcs.nlp.learning.vector.WeightVector;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AdaDeltaMiniBatch extends AdaGradMiniBatch
{
	private float decaying_rate; 
	private float growth_rate; 
	
	public AdaDeltaMiniBatch(WeightVector vector, float learningRate, float decayingRate)
	{
		this(vector, learningRate, decayingRate, null);
	}
	
	public AdaDeltaMiniBatch(WeightVector vector, float learningRate, float decayingRate, RegularizedDualAveraging rda)
	{
		super(vector, learningRate, rda);
		decaying_rate = decayingRate;
		growth_rate   = 1 - decayingRate;
	}
	
	@Override
	protected float getDiagonal(float previousDiagonal, float gradient)
	{
		return decaying_rate*previousDiagonal + growth_rate*(float)MathUtils.sq(gradient);
	}
	
	@Override
	public String toString()
	{
		return toString("AdaDelta-MiniBatch", "decaying rate = "+decaying_rate);
	}
}