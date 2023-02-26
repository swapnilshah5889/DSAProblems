package Heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Integer> minList;

    MinHeap() {
        this.minList = new ArrayList<>();
    }

    MinHeap(List<Integer> list) {
        this.minList = list;
        for(int i=list.size()-1; i>=0; i--) {
            int currIndex = i;
            int parentIndex = (currIndex-1)/2;
            while (currIndex>0 && minList.get(parentIndex)>minList.get(currIndex)){
                swapElements(parentIndex, currIndex);
                currIndex = parentIndex;
                parentIndex = (currIndex-1)/2;
            }
        }
    }
    public Integer getMin() {
        if(minList.size()>0)
            return minList.get(0);
        return null;
    }

    public void pushHeap(Integer val) {
        int currIndex = minList.size();
        minList.add(val);
        while (currIndex>0) {
            int parentInd = (currIndex-1)/2;
            if(minList.get(parentInd)>minList.get(currIndex)){
                swapElements(parentInd, currIndex);
                currIndex = parentInd;
            }
            else
                break;
        }
    }

    public void printHeap() {
        System.out.println(minList);
    }

    private void swapElements(int parentIndex, int currIndex) {
        int temp = minList.get(parentIndex);
        minList.set(parentIndex, minList.get(currIndex));
        minList.set(currIndex, temp);
    }

    public boolean isValidHeap() {
        return minList.size()%2==1? true:false;
    }
}
