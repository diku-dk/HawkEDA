package cep.result;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrentCheckoutStmtResult implements UpdateListener {
    private static final Logger log = LoggerFactory.getLogger(ConcurrentCheckoutStmtResult.class);

    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement epStatement, EPRuntime epRuntime) {
        if (newEvents == null || newEvents.length == 0) {
            log.info("No events arrived...");
            return;
        }

//        log.info("A total of {} New event(s) received in ConcurrentCheckoutStmtResult at {}.", newEvents.length, new java.util.Date());
        log.info("-------CEP Analysis (ConcurrentCheckoutStmtResult)-------");
        for (EventBean event : newEvents) {
            log.info("---------------------Result---------------------------");
            log.info(event.getUnderlying().toString());
            JSONObject eventJSONObj = new JSONObject(event.getUnderlying().toString().replace('=',':'));
            System.out.println("The current count for event "+ eventJSONObj.getString("eventName") + " is " + eventJSONObj.getInt("count"));
            // AssertExpected can be used to stop.
        }
        log.info("---------------------------------------------------------\n");
    }
}