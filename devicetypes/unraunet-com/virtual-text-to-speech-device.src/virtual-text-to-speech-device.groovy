/*
 *  Copyright 2020 Jack Unrau
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "Virtual Text To Speech Device", namespace: "unraunet.com", author: "Jack Unrau") {
		capability "Speech Synthesis"
        capability "Audio Notification"
        capability "Actuator"
        capability "Sensor"
        command "speakAtVolume", ["string", "number"]
        }
    preferences {
    	input("nodeRedServerIp", "string", title:"Node-red Server IP/hostname", description: "IP address or DNS hostname", required: true, displayDuringSetup: false)
        input("nodeRedServerPort", "number", title:"Node-red Server port", description: "Port", required: true, displayDuringSetup: false)
    }
}

def parse(String description) {
    log.trace "Parse description:  $description"
}

def playTrack(uri,level) {
	speakAtVolume(uri,level)
}

def playTrackAndResume(uri,level) {
	speakAtVolume(uri,level)
}

def playTrackAndRestore(uri,level) {
	speakAtVolume(uri,level)
}

def speak(message) {
	speakAtVolume(message,"")    
}

def speakAtVolume(message,volume) {
	log.trace "Executing speak($message,$volume)"

	def type
    
    if (message.startsWith("http"))
    {
    	type = "url"
    }
    else
    {
    	type = "phrase"
    }
	
    message = java.net.URLEncoder.encode(message, "UTF-8")
	log.trace "URL encoded message:  $message"

	def hubAction = new physicalgraph.device.HubAction(
                method: "GET",
                path: "/automations/alexa/announce?${type}=${message}&volume=${volume}",
                headers: ["HOST":  "${nodeRedServerIp}:${nodeRedServerPort}"])

	sendHubCommand(hubAction)
}
