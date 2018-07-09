package edu.neu.coe.info6205.sort;

import static edu.neu.coe.info6205.sort.Helper.less;
import static edu.neu.coe.info6205.sort.Helper.swap;

public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement selection sort
    	int n = xs.length;
//      if (to >= xs.length) {
//          System.out.println("note:to<n");
//          return;
//      }
      for (; from <to; from++) {
          int minimum = from;
          for (int j = from + 1; j < to; j++) {
              if (Helper.less(xs[j], xs[minimum])) {
                  minimum = j;
              }
          }
          Helper.swap(xs, from, minimum);
      }
    }
}
