/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

import edu.neu.coe.info6205.sort.InsertionSort;
import edu.neu.coe.info6205.sort.SelectionSort;
import edu.neu.coe.info6205.sort.Sort;

public class Benchmark<T> {

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
    	double finalResult = 0;
        for (int i = 0; i < n; i++) {
            double begin = System.currentTimeMillis();
            f.apply(t);
            double stop = System.currentTimeMillis();
            double result = stop - begin;
            finalResult += result;
        }
        return finalResult / n;  // TODO
    }

    
    private final Function<T, Void> f;

    public static void main(String[] args) {
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        Random random = new Random();
        
        Integer[] orderedArray = new Integer[3200];
        for (int i = 0; i < orderedArray.length; i++) orderedArray[i] = i; // TODO populate the array with real random data
        
        Integer[] randomArray = new Integer[3200];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = Math.abs(random.nextInt(randomArray.length));
        }
        
        Integer[] reversedArray = new Integer[3200];
        for (int i = 0; i < reversedArray.length; i++) {
            reversedArray[i] = reversedArray.length - i - 1;
        }
        
        Integer[] partiallySortedArray = orderedArray.clone();    
        for (int i =0; i< partiallySortedArray.length;i++){
            if (i%2==0){
//              partiallySortedArray[i]=i;//the even positions are sorted while odd are not
            }else {
                partiallySortedArray[i]=Math.abs(random.nextInt(partiallySortedArray.length));
            }
        }
//        System.out.println("System is warming up");
//        
////        int n = 200;
//        // TODO You need to apply doubling to n
//        for(int j=0; j<10; j++) {
//        benchmarkSort(orderedArray, orderedArray.length, "SelectionSort", new SelectionSort<>(), 10);
//        benchmarkSort(orderedArray, orderedArray.length, "InsertionSort", new InsertionSort<>(), 10);
//    }
//        System.out.println("System has warmed up");

        Collections.shuffle(Arrays.asList(randomArray));
        Collections.shuffle(Arrays.asList(reversedArray));
        Collections.shuffle(Arrays.asList(partiallySortedArray));


        
        for (int n =200; n <= 3200; n *= 2) {
            System.out.println("Order");
            benchmarkSort(orderedArray, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("Random");
            benchmarkSort(randomArray, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("Reverse");
            benchmarkSort(reversedArray, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("PartiallySorted");
            benchmarkSort(partiallySortedArray,n,"SelectionSort",new SelectionSort<>(),m);
        }
        for (int n=200;n<=3200;n*=2){
            System.out.println("Order");
            benchmarkSort(orderedArray, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("Random");
            benchmarkSort(randomArray, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("Reverse");
            benchmarkSort(reversedArray, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("PartiallySorted");
            benchmarkSort(partiallySortedArray,n,"InsertionSort",new InsertionSort<>(),m);
        }
        
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
        	
            sorter.sort(xs.clone(), 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n=" + n);
    }
}
