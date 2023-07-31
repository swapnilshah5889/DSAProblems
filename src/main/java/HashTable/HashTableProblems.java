package HashTable;

import java.util.*;

public class HashTableProblems {

    public static String minimumWindowString(String s, String t) {
        if(t.length() > s.length())
            return "";

        HashMap<Character, Integer> mapt = new HashMap<>();
        for(int i=0; i<t.length(); i++) {
            mapt.put(t.charAt(i), mapt.getOrDefault(t.charAt(i),0)+1);
        }

        int start=0, end=0, matchCount=0, minStart=0, minLength = Integer.MAX_VALUE;
        HashMap<Character, Integer> maps = new HashMap<>();
        while(end<s.length()) {
            char c = s.charAt(end);
            //If character in base map
            if(mapt.containsKey(c)) {

                //If string map contains character
                if(maps.containsKey(c)) {
                    //If count less than required then increment length
                    if(maps.get(c)<mapt.get(c)) {
                        matchCount++;
                    }
                    maps.put(c, maps.get(c)+1);
                }
                else {
                    maps.put(c, 1);
                    matchCount++;
                }
            }
            end++;
            while(matchCount == t.length() && start<end) {
                if( (end-start) < minLength ) {
                    minLength = end-start;
                    minStart = start;
                }
                char ch = s.charAt(start);
                if(mapt.containsKey(ch)) {
                    if(maps.get(ch) == 1)
                        maps.remove(ch);
                    else
                        maps.put(ch, maps.get(ch)-1);
                    if(!maps.containsKey(ch) || maps.get(ch)<mapt.get(ch))
                        matchCount--;
                }
                start++;
            }
        }

        if(minLength == Integer.MAX_VALUE)
            return "";
        else
            return s.substring(minStart, minStart+minLength);
    }

    static class LionSchedule {
        String name;
        int enterTime;
        int exitTime;
        boolean entered;
        LionSchedule(String name, int enterTime, int exitTime) {
            this.name = name;
            this.enterTime = enterTime;
            this.exitTime = exitTime;
            this.entered = false;
        }
    }
    static class LionDescription {
        String name;
        int height;
        LionDescription(String name, int height) {
            this.name = name;
            this.height = height;
        }
    }

    public static class LionCompetition{

        HashMap<String, Integer> lionHeights;
        // Height - Lions
        TreeMap<Integer, List<LionSchedule>> myTeam;
        // Height - Enter Time
        TreeMap<Integer, List<Integer>> otherTeam;

        LionCompetition (List<LionDescription> lions, List<LionSchedule> schedule) {
            lionHeights = new HashMap<>();
            for(LionDescription lion : lions) {
                lionHeights.put(lion.name, lion.height);
            }
            myTeam = new TreeMap<>(Collections.reverseOrder());
            otherTeam = new TreeMap<>(Collections.reverseOrder());
            for(int i=0; i<schedule.size(); i++) {
                int height = lionHeights.get(schedule.get(i).name);
                if(myTeam.containsKey(height)) {
                    myTeam.get(height).add(schedule.get(i));
                }
                else{
                    myTeam.put(height, new ArrayList<>(
                            Arrays.asList(schedule.get(i))));
                }
            }
        }

        public void lionEntered(int currTime, int height) {
            boolean myLionEntered = false;
            // If My Lion
            if(myTeam.containsKey(height)) {
                for(int i=0; i<myTeam.get(height).size(); i++) {
                    // If lion not entered and curr time == enter time
                    if(!myTeam.get(height).get(i).entered &&
                            myTeam.get(height).get(i).enterTime == currTime) {
                        myTeam.get(height).get(i).entered = true;
                        myLionEntered = true;
                    }
                }
            }

            // If lion not in my team - add to other team
            if(!myLionEntered) {
                if (otherTeam.containsKey(height)) {
                    otherTeam.get(height).add(currTime);
                } else {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(currTime);
                    otherTeam.put(height, arr);
                }
            }
        }

        public void lionLeft(int currentTime, int height) {
            boolean removeMyLion = false;
            int myLionEnterTime = -1;
            // If my team has this height
            if(myTeam.containsKey(height)) {
                for(LionSchedule lion : myTeam.get(height)) {
                    if(lion.exitTime == currentTime) {
                        removeMyLion = true;
                        myLionEnterTime = lion.enterTime;
                        break;
                    }
                }
            }

            // If other team has this height and their enter time is earlier
            if(otherTeam.containsKey(height) &&
                    myLionEnterTime > otherTeam.get(height).get(0)){
                 removeMyLion = false;
            }

            // Remove lion from my team
            if(removeMyLion) {
                if(myTeam.get(height).size()==1) {
                    myTeam.remove(height);
                }
                else {
                    myTeam.get(height).remove(0);
                }
            }
            // Remove lion from other team
            else {
                if(otherTeam.get(height).size()==1) {
                    otherTeam.remove(height);
                }
                else {
                    otherTeam.get(height).remove(0);
                }
            }

        }

        public List<String> getBiggestLions(){
            TreeSet<String> myWinners = new TreeSet<>();
            // Other team has bigger lions
            if(myTeam.firstKey() < otherTeam.firstKey()) {
                return new ArrayList<>();
            }
            // My team has bigger or equal lions
            else {
                // Opponent biggest height
                int oppoH = otherTeam.firstKey();
                for(Integer height : myTeam.keySet()){

                    // My Lion has bigger height than opponent's biggest height
                    if(height>=oppoH) {
                        // Add all lions that have this height and have entered
                        for(LionSchedule lion : myTeam.get(height)) {
                            if(lion.entered)
                                myWinners.add(lion.name);
                        }
                    }
                    // Opponent has bigger height
                    else {
                        break;
                    }
                }
            }
            return myWinners.stream().toList();
        }
    }

    public static void main(String[] args) {
        //Get minimum window string that contains all characters of the base string
        /*System.out.println(minimumWindowString("ADOBECODEBANC", "ABC"));


        int[] arr = new int[]{-2892, 5148, -6190, 3966, 996, -3497, -2880, 783, 5982, 5405, 9517, 9458, 5188, 1184, 2875, 6116, -9891, -4253, -1333, 1915, 2058, 1647, -3224, -5148, 7820, -9879, -693, 5808, 5620, 7074, 6454, -9249, -5100, -5055, -943, 9105, 3146, -9434, 8641, 3066, -6946, 3874, -6776, -1644, 2486, 2266, -5125, 3221, -3619, -5566, 3217, -9906, -7508, -6257, 6210, 4188, -727, -4398, -8907, -5992, -3649, 44, 9322, -7698, 8524, 8840, -4727, 2077, -944, 8453, 9663, 4734, -478, -4399, 2228, 845, 8463, 3560, -6235, -5085, 5688, 9374, -7241, -1489, -5827, 7861, -5807, 6771, -9662, 6614, 7722, -239, 3523, -1666, -7196, 9175, 5068, -7873, 5979, 7105, -9524, 8359, -4185, -2066, -8825, -8236, 384, -1139, -732, -8474, -9344, -1273, -3333, -7335, -6474, -9874, 2095, -2382, -6021, 3767, 9524, 177, -2102, -748, 8384, 2432, 9842, -1574, -3696, 2779, 1627, 563, 6028, 7541, 41, 6361, 5342, 6238, 5539, 5997, -3222, 6504, 8070, 313, 2880, 388, -1838, -703, 6951, 5878, -9960, -5627, 9264, -5900, -6253, -1528, -8888, 7291, 6255, 9903, -2628, 2818, 7974, 9763, 3638, -1145, -8133, -1923, -6717, 8967, -3976, 9639, 2366, 4353, -97, -7662, 9848, 7419, -4860, -5128, -1296, 3419, -305, -1498, 5672, 1502, 8375, -795, -4311, -3062, -755, -3545, -1509, 3762, 3174, -2473, -231, -3421, 5736, -2538, 6552, 9651, -9453, 7562, 3700, -9534, -169, -553, 1741, -1550, -1641, -8584, -7116, 1047, -2110, 490, 3892, -7016, 7143, 5667, 5971, -1718, -7779, -5976, 8400, -1619, 8898, -1726, 7944, -5882, -6721, -6652, -9326, -1765, 3541, 2507, -6068, 2916, -7680, 8312, -6060, 7750, -2140, -2503, 7248, 6013, -7934, 5926, -1546, -9047, -323, -5033, 4631, -8421, 8221, -1760, 4024, 9562, -3847, 5038, -7319, 1245, -2486, -2559, 5345, 4291, -9248, -7083, -5319, -4400, 2167, -3018, 7730, 5952, 1844, -5307, -7432, -3889, -1403, -6607, 9622, 1745, 7246, 7076, 1501, 4019, 6266, -8373, -1651, 4717, -9970, -2113, 7337, -2764, -235, -7663, 9702, -954, -7553, 6326, -2751, -6938, 5672, -7356, -4202, 6075, -3699, -7614, -1804, -4278, 9811, 2485, 2688, 7674, -2865, -4165, 4470, 4443, -3256, -8269, -1724, -1269, -8155, 7340, 2271, -5885, 5384, 746, 482, -2593, 4584, 3891, -6059, 2764, -1113, -3777, 5381, 9905, -7227, 4354, -2794, -485, 5150, -8440, 9611, -8524, 2822, 6455, -4516, 9802, -5258, -5589, 8290, -3226, -2317, -3337, 8460, -3988, -8786, 8391, 8560, -8522, -2929, -7350, -10000, -6644, 2487, 9128, 1387, -5535, -2863, 1186, 5794, -3405, -1042, 1519, -4013, -4324, 561, 8420, -6794, 8572, 7233, -5277, 7361, -2615, 8592, -7697, -9074, -7403, 8576, -6983, -9754, -2297, 1271, 2859, 6942, -3329, -2183, 1282, 708, 8294, 7673, -4083, 2026, -604, 1702, 4462, -9521, -7183, -4800, -3565, 3626, -7486, 2481, 5728, 4223, -9062, -2549, 8249, -203, 7770, -4907, -3207, 5047, -8765, -3304, -4832, 5709, -7236, -4093, 9512, -4733, 4878, 187, 4566, 104, 2012, -5813, 6403, -3415, -2033, -8487, -2156, 9099, 2870, 6650, 5251, -7561, -8104, -1260, -3859, -8794, 4418, -3726, 1790, -6598, -2431, -3959, 4872, -7310, -1615, 4226, -7550, 107, -246, -7002, -8377, 4871, -4832, 8545, 1015, 8532, -3075, 4054, 7402, -9177, -4563, 5066, 2542, -5262, 9866, -3574, -1851, -1677, 3415, 6556, 1528, 3220, 806, -3178, -9444, 6616, 2619, 9930, -720, 4782, -2951, 4754, 2654, -1112, -6764, -1617, 3771, -6393, -6333, 895, 652, -9680, 609, 8440, -8784, 578, 4069, -9495, 6477, -1093, -4367, -3328, -5930, -9292, -9617, 783, -297, 7478, 7227, -9645, -9704, -6031, 5041, -517, 9046, 4922, 7893, -7238, -4559, 5007, -9338, 8399, 2913, 4959, 6848, 9, 5549, 1538, -3061, -1296, 7782, -4849, -1830, 3451, -5244, 9401, -225, 8923, -7365, -8224, -8925, 2256, 4, 3592, -2186, -7553, 770, -2837, 455};
        System.out.println(arr.length);
        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for(int i=1; i<arr.length; i++) {
            presum[i] = presum[i-1] + arr[i];
        }

        for(int i : presum)
            System.out.print(i+" ");*/


        List<LionDescription> lions = new ArrayList<>();
        lions.add(new LionDescription("marry", 300));
        lions.add(new LionDescription("rob", 250));
        List<LionSchedule> schedules = new ArrayList<>();
        schedules.add(new LionSchedule("marry", 10, 15));
        schedules.add(new LionSchedule("rob", 13, 20));
        LionCompetition lionCompetition = new LionCompetition(lions,schedules);

        lionCompetition.lionEntered(8,200);
        lionCompetition.lionEntered(9,300);
        List<String> winners = lionCompetition.getBiggestLions();
        System.out.println("Time 10 : ");
        winners.stream().forEach(lion -> {
            System.out.print(lion+" ");
        });
        System.out.println();

        lionCompetition.lionEntered(10,300);

        lionCompetition.lionEntered(13,250);
        lionCompetition.lionLeft(13,300);
        winners = lionCompetition.getBiggestLions();

        System.out.println("Time 13 : ");
        winners.stream().forEach(lion -> {
            System.out.print(lion+" ");
        });
        System.out.println();

        lionCompetition.lionLeft(15,300);
        System.out.println("Time 16 : ");
        winners = lionCompetition.getBiggestLions();
        winners.stream().forEach(lion -> {
            System.out.print(lion+" ");
        });
        System.out.println();

        lionCompetition.lionLeft(16, 200);
        lionCompetition.lionLeft(20, 250);
    }

}


//{[50, 0], [50, 1], [100, 0], [110, 0], [110, 0], [110, 1]}




