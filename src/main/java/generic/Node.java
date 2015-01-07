package generic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by antoine.boyer on 1/4/15.
 * All rights reserved.
 */
final public class Node
{
    private static Random rand = new Random();//used to generate random weight

	public String id;
    public float weight;
	public boolean visited = false;
    public int level = 0;
    public List<Node> adjacentNodes = new LinkedList<>();

	public Node(final String id)
	{
		this.id = id;
        this.weight = rand.nextFloat() * (10.0f - 0.0f) + 0.0f; // weight range from 0.0f to 10.0f
	}
    public Node(final String id, final float weight)
    {
        this.id = id;
        this.weight = weight;
    }
    public Node(final String id, final float weight, final List<Node> adjacentNodes)
    {
        this.id = id;
        this.weight = weight;
        this.adjacentNodes = adjacentNodes;
    }

    public Node addAdjacentNode(final Node adj) {
        this.adjacentNodes.add(adj);// O(1)
        //also add the current node to the adjacent nodes list of adj
        adj.adjacentNodes.add(this);// O(1)
        return this;
    }

    public String toString() {
        return this.id + "-" + this.weight + "-" + this.level + " ";
    }
}
