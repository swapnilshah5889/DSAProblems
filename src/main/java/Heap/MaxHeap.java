package Heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private List<Integer> maxList;

    MaxHeap() {
        this.maxList = new ArrayList<>();
    }

    public Integer getMax() {
        if(maxList.size()>0)
            return maxList.get(0);
        return null;
    }

    public void pushHeap(Integer val) {
        int currIndex = maxList.size();
        maxList.add(val);
        while (currIndex>0) {
            int parentInd = (currIndex-1)/2;
            if(maxList.get(parentInd)<maxList.get(currIndex)){
                int temp = maxList.get(parentInd);
                maxList.set(parentInd, maxList.get(currIndex));
                maxList.set(currIndex, temp);
                currIndex = parentInd;
            }
            else
                break;
        }
    }

    public void printHeap() {
        System.out.println(maxList);
    }

    public boolean isValidHeap() {
        return maxList.size()%2==1? true:false;
    }
}
