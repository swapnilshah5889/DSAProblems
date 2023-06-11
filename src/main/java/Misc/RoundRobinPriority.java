package Misc;

import java.util.*;

public class RoundRobinPriority {

    public static int timeQuantum = 4;
    public static final int priorityPA = 2;
    public static final int priorityPB = 3;
    public static final int priorityPC = 1;

    public static int runTimePA = 10;
    public static int runTimePB = 7;
    public static int runTimePC = 5;

    public static class Process {
        int pid;
        int priority;
        int signalCount;
        int arrivalTime;
        int totalTime;
        int runTime;
        Process(int pid, int priority, int arrivalTime, int runTime, int totalTime){
            this.pid = pid;
            this.priority = priority;
            this.arrivalTime = arrivalTime;
            this.runTime = runTime;
            this.totalTime = totalTime;
            signalCount = 0;
        }
    }

    public static class GanttValues {
        int pid;
        int time;
        GanttValues(int pid, int time){
            this.pid = pid;
            this.time = time;
        }
    }

    public static int findNextProcessToRun(List<Process> queue){
        int index=0;
        Process nextProcess = queue.get(index);
        for(int i=0; i<queue.size(); i++){
            if(queue.get(i).priority<nextProcess.priority)
                index = i;
        }
        return index;
    }


    public static List<GanttValues> getGanttList(List<Process> intialProcessess){

        //Build Queue
        List<Process> queue = new ArrayList<>();
        int totalProcess = 1;
        for(Process p : intialProcessess){
            queue.add(p);
            totalProcess++;
        }

        //Final Gantt Chart Values
        List<GanttValues> ganttList = new ArrayList<>();
        int totalTime=0;

        //Signal value hashmap
        HashMap<Integer, Integer> signalValues = new HashMap<>();

        while(!queue.isEmpty()){
            int index = findNextProcessToRun(queue);
            Process nextProcess = queue.get(index);
            queue.remove(index);
            int runTime = 0;
            for(int i=0;i<timeQuantum; i++) {

                //If runtime is less than burst time then increase run time by 1
                if (nextProcess.runTime < nextProcess.totalTime)
                    nextProcess.runTime++;
                //If runtime is equal to burst time then exit
                else
                    break;

                //If process at interval of 3
                if(nextProcess.runTime%3==0) {
                    switch (nextProcess.priority) {
                        //If process is PA then fork PB
                        case priorityPA:
                            queue.add(new Process(totalProcess,priorityPB,totalTime,0,runTimePB));
                            totalProcess++;
                            break;
                        //If process is PB then fork PC
                        case priorityPB:
                            queue.add(new Process(totalProcess,priorityPC,totalTime,0,runTimePC));
                            totalProcess++;
                            break;
                    }
                }

                //If Hardware failure then increament signal value of the current process
                if(totalTime!=0 && totalTime%3==0)
                    nextProcess.signalCount++;

                totalTime++;
                runTime++;

            }

            //Add last run process in gantt chart list
            ganttList.add(new GanttValues(nextProcess.pid, runTime));

            //If process still has burst time left add back to queue
            if(nextProcess.runTime<nextProcess.totalTime)
                queue.add(nextProcess);
            //If process burst time over then remove from queue & log the signal value
            else
                signalValues.put(nextProcess.pid,nextProcess.signalCount);

        }

        //Print
        printSignalValues(signalValues);

        return ganttList;

    }

    public static void printSignalValues(HashMap<Integer,Integer> signalMap){
        System.out.println("\n--- Process' Signal Count ---");
        int i=1;
        Set<Integer> set = signalMap.keySet();
        for(Integer index : set)
            System.out.println("P"+index + " : " + signalMap.get(index));
        /*while(signalMap.containsKey(i)) {
            System.out.println("P"+i + " : " + signalMap.get(i));
            i++;
        }*/
        System.out.println();
    }

    public static void printGanttValues(List<GanttValues> ganttValues){
        int totalTime = 0;
        for(GanttValues g:ganttValues){
            System.out.println(g.pid+" -> "+g.time);
            totalTime+=g.time;

        }

    }

    public static void printGanttChart(List<GanttValues> ganttList){
        System.out.println("\n--- Gantt Chart ---");
        System.out.print("-");
        for(GanttValues g : ganttList){
            int len=5;
            if(g.pid>9)
                len+=1;
            for(int i=0;i<len;i++)
                System.out.print("-");
        }
        System.out.println();
        System.out.print("| ");
        for(GanttValues g : ganttList){
            System.out.print("p"+g.pid+" | ");

        }
        int totalTime=0;
        System.out.println();
        System.out.print("0");
        for(GanttValues g : ganttList){
            int len=5;
            if(g.pid>9)
                len+=1;
            totalTime+=g.time;
            if(totalTime>9 && totalTime<100)
                len--;
            else if(totalTime>99)
                len-=2;
            for(int i=0;i<len;i++) {
                if(i<len-1)
                    System.out.print("-");
                else
                    System.out.print(totalTime);
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        //Build Initial static process P1 and P2
        List<Process> processesList = new ArrayList<>();
        processesList.add(new Process(1, priorityPA, 0,0, runTimePA));
        processesList.add(new Process(2, priorityPB, 0,0, runTimePB));

        //Perform round robin pirority process scheduling and fetch gantt chart values
        List<GanttValues> ganttValues = getGanttList(processesList);

        //Print Gantt Chart
        printGanttChart(ganttValues);

    }
}
