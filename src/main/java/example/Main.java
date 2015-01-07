package example;

import generic.Graph;
import generic.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoine.boyer on 1/4/15.
 * All rights reserved.
 *
 * Application Example:
 *      Implement a function to return the N highest rated movies that are considered similar to a given movie. Each movie has
 *      a rating and a list of movies it is most similar to. The full list of recommendation candidates for a given movie is its entire
 *      similarity network (similarities, similarities of similarities, etc.). The order of the returned movies does not matter.
 */
public class Main {

	/**
	 * @param args no params needed
	 */
	public static void main(String[] args) 
	{

        /*
         * Construct Graph
         */
        Node hitch = new Node("hitch", 6.3f);
        Node hangover = new Node("hangover", 7.5f);
        Node sonOfAGun = new Node("sonOfAGun", 8.5f);
        Node cinderella = new Node("cinderella", 4.2f);
        Node hobbit = new Node("hobbit", 8.4f);
        Node starWars = new Node("starWars", 9.5f);
        Node red = new Node("red", 9.5f);
        Node thePianist = new Node("thePianist", 9.5f);
        Node iwoJima = new Node("iwoJima", 7.6f);
        Node oldBoy = new Node("oldBoy", 6.0f);
        Node heartBreaker = new Node("heartBraker", 7.1f);

        List<Node> nodes = new ArrayList<>();
        nodes.add(hitch);
        nodes.add(hangover);
        nodes.add(sonOfAGun);
        nodes.add(cinderella);
        nodes.add(hobbit);
        nodes.add(starWars);
        nodes.add(red);
        nodes.add(thePianist);
        nodes.add(iwoJima);
        nodes.add(oldBoy);
        nodes.add(heartBreaker);

        Graph g = new Graph(nodes);

        hitch.addAdjacentNode(hangover);
        hitch.addAdjacentNode(sonOfAGun);
        hitch.addAdjacentNode(hobbit);

        hangover.addAdjacentNode(starWars);

        starWars.addAdjacentNode(iwoJima);

        iwoJima.addAdjacentNode(oldBoy);

        oldBoy.addAdjacentNode(hobbit);
        oldBoy.addAdjacentNode(thePianist);

        sonOfAGun.addAdjacentNode(red);
        sonOfAGun.addAdjacentNode(thePianist);

        thePianist.addAdjacentNode(cinderella);

        /*
         * Retrieve top nodes as a list
         */
        List<Node> highestRatedNodes = g.getHighestRatedNodes(5, hitch);

        /*
         * Print out the top nodes
         */
        System.out.println("\nHighest Rated Nodes:");

        for (Node n : highestRatedNodes) {
            System.out.println(n);
        }
	}

}
