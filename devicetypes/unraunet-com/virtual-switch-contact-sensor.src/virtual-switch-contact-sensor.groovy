/**
 *  Copyright 2020 Jack Unrau
 *
 *	Based on:  https://community.smartthings.com/t/release-asuswrt-wifi-presence/37802
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "Virtual Switch Contact Sensor", namespace: "unraunet.com", author: "Jack Unrau") {
		capability "Actuator"
		capability "Sensor"
		capability "Switch"
		capability "Contact Sensor"
		command "on"
		command "off"
	}
}

def parse(description) {
}

def installed() {
	log.trace "Executing 'installed'"
	off()
}

def on() {
	log.trace "Executing 'on'"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "contact", value: "open")
}

def off() {
	log.trace "Executing 'off'"
	sendEvent(name: "switch", value: "off")
	sendEvent(name: "contact", value: "closed")
}
