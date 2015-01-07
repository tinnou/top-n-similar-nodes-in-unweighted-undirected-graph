package generic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by antoine.boyer on 1/4/15.
 * All rights reserved.
 *
 */
final public class Graph {
    private List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Time Complexity: O(n + E) where <em>n</em> is the number of nodes in the root node similarity network and
     * <em>E</em> the number of edges in the root node network
     * Space: O(|n| + |E|)
     * @param rootNode Vertex to start the traversal with
     * @param processor Object used to process nodes as they are being traversed
     */
    private void breadthFirstTraversal(Node rootNode,
            NodeProcessor processor) {
        //BFS Graph traversal
        int level = 0;//keep track of the current level: the higher the level, the further we are from the root
        Queue<Node> q = new LinkedList<>();
        q.add(rootNode);
        rootNode.visited = true;
        while (!q.isEmpty()) {
            Node n = q.poll();
            n.level = level;
            System.out.println(n);//print node for debugging - (outputs a BFS representation of the graph)
            //do something with the node
            processor.processNode(n, level);
            for (Node adj : n.adjacentNodes) {
                if (!adj.visited) {
                    adj.visited = true;
                    q.add(adj);
                }
            }
            level++;
        }
    }

    public List<Node> getHighestRatedNodes(final int N, Node rootNode) {
        TopNodesProcessor topNodesProcessor = new TopNodesProcessor(N);
        breadthFirstTraversal(rootNode, topNodesProcessor);
        return topNodesProcessor.getTopNodes();
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public Graph setNodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }
}
