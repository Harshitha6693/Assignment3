package edu.neu.coe.info6205.sort;

import static edu.neu.coe.info6205.sort.Helper.*;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {
    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement insertionSort
    	int n = xs.length;
//      if (to >= xs.length) {
//          System.out.println("note:to<n");
//          return;
//      }
      for (int i = from; i < to; i++) {
          for (int j = i; j > from; j--) {
              if (Helper.less(xs[j], xs[j - 1])) {
                  Helper.swap(xs, j, j - 1);
              } else {
                  break;
              }
          }
      }
    }
}
