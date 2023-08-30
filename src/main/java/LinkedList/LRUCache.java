package LinkedList;

import java.util.LinkedHashMap;

class LRUCache {
    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            int value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        }
        else {
            if(map.size()==capacity) {
                for(Integer k : map.keySet()) {
                    map.remove(k);
                    break;
                }
            }
            map.put(key, value);
//            System.out.println(map);
        }
    }
}
