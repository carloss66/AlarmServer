/**
 *  DSC Chime Toggle Button
 *
 *  Author: Carlos Santiago <carloss66@gmail.com>
 *  Original Code By: Rob Fisher <robfish@att.net>
 *  Date: 06/29/2015
 */
 // for the UI

preferences {
    input("ip", "text", title: "IP", description: "The IP of your AlarmServer")
    input("port", "text", title: "Port", description: "The port")
} 
 
metadata {
	// Automatically generated. Make future change here.
	definition (name: "Alarm Chime Toggle Button", author: "Carlos Santiago <carloss66@gmail.com>") {
		capability "Switch"
/*        capability "Refresh" */
	}

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("button", "device.switch", width: 1, height: 1, canChangeIcon: true) {
			state "off", label: 'Chime On', action: "switch.on", icon: "st.entertainment.entertainment2", backgroundColor: "#79b821"
			state "on", label: 'Chime Off', action: "switch.off", icon: "st.entertainment.entertainment2", backgroundColor: "#800000"
    }
/*        standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
            state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
    
    } */
		main "button"
        details(["button"])
        /*details(["button", "refresh"]) */
	}
}

def parse(String description) {
}

def on() {
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/api/alarm/chime",
        headers: [
            HOST: "$ip:$port"
            //HOST: getHostAddress()
        ]
    )
    log.debug "response" : "Request to toggle chime received"
    //log.debug "chime"
    sendEvent (name: "switch", value: "on")
    return result
}

def off() {
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/api/alarm/chime",
        headers: [
            HOST: "$ip:$port"
            //HOST: getHostAddress()
        ]
    )
    log.debug "response" : "Request to disarm received"
    //log.debug "chime"
    sendEvent (name: "switch", value: "off")
    return result
}

/*def refresh() {
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/api/refresh",
        headers: [
            HOST: "$ip:$port"
            //HOST: getHostAddress()
        ]
    )
    log.debug "response" : "Request to refresh received"
    //log.debug "refresh"
    sendEvent (name: "switch", value: "refresh")
    return result
}
*/