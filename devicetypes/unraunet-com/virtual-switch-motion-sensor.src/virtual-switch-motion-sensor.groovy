/**
 *  Copyright 2020 Jack Unrau
 *
 *	Based on:  https://community.smartthings.com/t/release-asuswrt-wifi-presence/37802
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "Virtual Switch Motion Sensor", namespace: "unraunet.com", author: "Jack Unrau") {
		capability "Actuator"
		capability "Sensor"
		capability "Switch"
		capability "Motion Sensor"
		command "on"
		command "off"
	}
}

def installed() {
	log.trace "Executing 'installed'"
	off()
}

def on() {
	log.trace "Executing 'on'"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "motion", value: "active")

	runIn(10, off) 
}

def off() {
	log.trace "Executing 'off'"
	sendEvent(name: "switch", value: "off")
	sendEvent(name: "motion", value: "inactive")
}