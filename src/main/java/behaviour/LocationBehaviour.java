package behaviour;

import agents.Taxi;
import city.DropoffPoint;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import utils.agentMethods.TaxiMethods;
import utils.misc.Activity;
import utils.shortestPath.DijkstraUndirectedSP;
import utils.shortestPath.Edge;
import utils.shortestPath.Path;
import utils.simulation.Timer;

/**
 * Created by jherez on 6/12/16.
 */
public class LocationBehaviour extends OneShotBehaviour {
    public Taxi agent;
    public DropoffPoint origin;
    public DropoffPoint destination;
    public Path path = new Path();
    DijkstraUndirectedSP sp;
    public Timer timer;
    public double jobTime;
    public int initTime;

    public LocationBehaviour(DropoffPoint origin, DropoffPoint destination, Taxi taxi, Timer runtime) {
        this.timer = runtime;
        this.initTime = runtime.getSecond();
        this.agent = taxi;
        this.origin = origin;
        this.destination = destination;
        sp = this.agent.vCity.getShortestPaths(this.agent.vCity.G, origin.index);
        this.path.w = origin.index;
        this.path.v = destination.index;
        this.path.weight = sp.distTo(destination.index);
        for (Edge e : sp.pathTo(destination.index)) {
            this.path.list.add(e);
        }
        this.agent.activity = Activity.TRANSPORTING_PASSENGER;
        String msg = "Taxi " + this.agent.index + " travelling from " + this.origin.index;
        msg += " to " + destination.index + " via " + this.path.list.toString();
        msg += " for a distance of " + this.path.weight;

        System.out.println(msg);
        this.jobTime = TaxiMethods.getTotalJobDistance(this.agent.vCity,this.agent.currentLocation,this.agent.confirmed_request);
        this.jobTime = (int) (this.jobTime / TaxiMethods.SPEED);
        this.jobTime = this.jobTime * 100 * 60;
    }

    @Override
    public void action() {
//        for(Edge e : this.path.list) {
//            System.out.println(e);
//        }
        if(this.timer.getSecond() >= this.initTime + this.jobTime) {
            this.agent.activity = Activity.WAITING_FOR_JOB;
            this.agent.destination = this.destination;
            this.agent.currentLocation = this.destination;
            if (this.agent.currentLocation == this.destination) {
                System.out.println("Taxi " + agent.index + ": Arrived at " + this.destination);
            }
        }
    }
}
