package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by antoine.boyer on 1/4/15.
 * All rights reserved.
 *
 * N: Number of highest rated nodes desired to be returned
 * n: Number of vertices in the similarity network of the root node
 *
 * Overall time complexity :
 *
 *  O(n + E) * 2log((N)) + N * (log (N))
 *  where
 *      BFS : O(n + E)
 *      min-heap insertion: O(log (N))
 *      min-heap removal: O(log (N))
 *      min-heap retrieval: O(log (N))
 *
 * Assumption: N is infinitesimal compared to n, heap construction is negligible compared to the graph traversal
 *
 *  O(n + E) * 2log((N)) + N * (log (N)) => O(n + E)
 */
public class TopNodesProcessor implements NodeProcessor {
    /*
     * Number of top nodes desired
     */
    private int N;
    /*
     * Let's use a min-heap to store the top nodes of our un-weighted un-directed graph.
     * Since min-heap is a partially ordered data structure we can achieve better time complexity than sorting
     * an entire collection of nodes.
     * The use of a min-heap allows to keep the size of the priority queue to N
     * Insertion is O(log(n))
     * Poll is O(log(n)) with essentially n <= N + 1
     */
    private PriorityQueue<Node> heap;

    public TopNodesProcessor(int N) {
        this.N = N;
        this.heap = new PriorityQueue<>(N + 1, new Comparator<Node>() {
            @Override public int compare(Node o1, Node o2) {
                //sort weight ASC
                if (o1.weight > o2.weight) {
                    return 1;
                } else if (o1.weight < o2.weight) {
                    return -1;
                } else { //if the weight is the same
                    //look at how close the two nodes are from the root
                    //SORT level from root DESC
                    if (o1.level > o2.level) {
                        return -1;
                    } else if (o1.level < o2.level) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }


    /**
     * @param n     node being currently looked at during traversal
     * @param level distance from the root node (level = 0 is root level)
     */
    @Override public void processNode(Node n, int level) {
        if (level > 0) {
            //O(log(N))
            heap.add(n);//add node to priority queue (except the root node..)
            //eject the min if size has reached N
            if (heap.size() > N && heap.peek() != null) {
                //O(log(N))
                heap.poll();
            }
        }
    }

    /**
     * This function converts the min-heap of size N to an array.
     * Complexity: N * (log (N))
     * Space: N
     * This function doesn't have to be counted for the overall time complexity of the program
     * since the heap already contains the top rated nodes.
     * If N turns out not to be negligible compared to <em>n</em> then retrieval from the heap should be included.
     * @return List of top nodes
     */
    public List<Node> getTopNodes() {
        return new ArrayList<>(Arrays.asList(heap.toArray(new Node[heap.size()])));
    }
}
