/*-
 * Copyright 2015 Kichwa Coders Ltd. and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jonah Graham - initial implementation
 */

package org.eclipse.dawnsci.analysis.examples.dataset;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.eclipse.dataset.Dataset;
import org.eclipse.dataset.DoubleDataset;
import org.eclipse.dataset.Maths;
import org.eclipse.dataset.Slice;
import org.slf4j.LoggerFactory;

/**
 * A basic example of using Datasets.
 * <p>
 * To run the example, right-click on the file and choose
 * "Run As -> Java Application"
 * <p>
 * The example projects has additional dependencies so that the examples can run
 * as a Java Application.
 */
public class BasicExample {
	public static void main(String[] args) {
		suppressSLF4JError();
		System.out.println("Welcome to a Basic Example of the org.eclipse.dataset.");

		// Create a Dataset:
		Dataset dataset = new DoubleDataset(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

		// Print the output:
		System.out.println("shape of dataset: " + Arrays.toString(dataset.getShape()));
		System.out.println("toString of dataset: " + dataset.toString());
		System.out.println("toString, with data, of dataset: \n" + dataset.toString(true));

		// Reshape the dataset:
		dataset = dataset.reshape(3, 3);
		System.out.println("reshaped dataset: \n" + dataset.toString(true));

		// create another [3, 3] shaped dataset directly
		Dataset another = new DoubleDataset(new double[] { 1, 1, 2, 3, 5, 8, 13, 21, 34 }, 3, 3);
		System.out.println("another dataset: \n" + another.toString(true));

		// do some maths on the datasets
		Dataset add = Maths.add(dataset, another);
		System.out.println("dataset + another dataset: \n" + add.toString(true));

		// do inplace maths on datasets
		Dataset inplace = new DoubleDataset(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3);
		inplace.iadd(100);
		System.out.println("dataset + 100 inplace: \n" + inplace.toString(true));

		// take a slice of a dataset
		Dataset slice = dataset.getSlice(new Slice(0, 2), new Slice(1, 3));
		System.out.println("slice of dataset: \n" + slice.toString(true));

		// for more examples, see the other java files in this project
	}

	/**
	 * The SLF4J logger produces output when it is not properly configured. For
	 * the purpose of a simple demo, suppress the error message rather than
	 * adding the requirement of a fully configured logger.
	 * <p>
	 * The error message suppressed is:
	 *
	 * <pre>
	 * SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
	 * SLF4J: Defaulting to no-operation (NOP) logger implementation
	 * SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
	 * </pre>
	 */
	private static void suppressSLF4JError() {
		PrintStream saved = System.err;
		try {
			System.setErr(new PrintStream(new OutputStream() {
				public void write(int b) {
				}
			}));

			LoggerFactory.getLogger(BasicExample.class);

		} finally {
			System.setErr(saved);
		}
	}
}
