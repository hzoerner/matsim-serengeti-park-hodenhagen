package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.network.Link;
import java.util.HashMap;
import java.util.Map;

public class HistogramEventHandler implements LinkEnterEventHandler {

    private final Id<Link> linkForAnalysis = Id.createLinkId("7232382780000f");

    private Map<Integer,Integer> trafficVolume = new HashMap<>();

    public Map<Integer, Integer> getTrafficVolume(){
        return trafficVolume;
    }

    @Override
    public void handleEvent(LinkEnterEvent linkEnterEvent) {

        if(!linkEnterEvent.getLinkId().equals(linkForAnalysis)) return;

        int time = getTime(linkEnterEvent.getTime());

        if(trafficVolume.containsKey(time)){
            int oldTrafficVolume = trafficVolume.get(time);

            trafficVolume.put(time, oldTrafficVolume+1);
        } else {
            trafficVolume.put(time,1);
        }

    }

    int getTime(double timeFromEvent){
        return (int) timeFromEvent/3600;
    }
}
