package com.kraqqen.util.storage;

public class PriorityQueueMerger {
	
	private PriorityQueueMerger() { }
	
    private static void merge(String[] streams) {
        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
        for (int i = 0; i < n; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].toString());

        while (!pq.isEmpty()) {
            System.out.print(pq.minKey() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].toString());
        }
        System.out.println();
    }

}
