package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class FirstEventHandler implements ActivityEndEventHandler, ActivityStartEventHandler {

    private int dummy = 0;

    private final Map<Id<Person>,Double> personToStartTime = new HashMap<>();

    @Override
    public void handleEvent(ActivityEndEvent activityEndEvent) {
        System.out.println("Person " + activityEndEvent.getPersonId() + " just finished activity type: " + activityEndEvent.getActType());
        personToStartTime.put(activityEndEvent.getPersonId(),activityEndEvent.getTime());
    }

    @Override
    public void handleEvent(ActivityStartEvent activityStartEvent) {
        System.out.println("Person" + activityStartEvent.getPersonId() + " just started activity type: " + activityStartEvent.getActType());

        double arrivingtime = activityStartEvent.getTime();

        double traveltime = arrivingtime - personToStartTime.get(activityStartEvent.getPersonId());

        System.out.println("Person " + activityStartEvent.getPersonId() + " travelled " + traveltime + " seconds.");
    }
}
