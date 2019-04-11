/**
 *  App Endpoint API Access
 *
 *  Author: Corey Murnaghan
 */

definition(
    name: "MurnControl",
    namespace: "audiofreak9",
    author: "Corey Murnaghan",
    description: "PHP display and control page",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/murnaghan/Skill60x60.png",
    iconX2Url: "https://s3.amazonaws.com/murnaghan/Skill120x120.png",
    iconX3Url: "https://s3.amazonaws.com/murnaghan/Skill120x120.png",
    oauth: [displayName: "MurnControl", displayLink: "coreyswrite.com"])

preferences {
	section("Allow Endpoint to Control These Things...") {
		input "switches", "capability.switch", title: "Which Switches?", multiple: true, required: false
        input "dimmers", "capability.switchLevel", title: "Which Dimmers?", multiple: true, required: false
        input "thermostats", "capability.thermostat", title: "Which Thermostats?", multiple: true, required: false 
        input "motions", "capability.motionSensor", title: "Which Motions?", multiple: true, required: false
        input "accelerations", "capability.accelerationSensor", title: "Which Accelerations?", multiple: true, required: false
        input "contacts", "capability.contactSensor", title: "Which Contacts?", multiple: true, required: false
        input "illuminants", "capability.illuminanceMeasurement", title: "Which Illuminance Sensors?", multiple: true, required: false
        input "temperatures", "capability.temperatureMeasurement", title: "Which Temperatures?", multiple: true, required: false 
        input "humidities", "capability.relativeHumidityMeasurement", title: "Which Humidities?", multiple: true, required: false 
        input "presence", "capability.presenceSensor", title: "Which Presence?", multiple: true, required: false 
        input "lock", "capability.lock", title: "Which Locks?", multiple: true, required: false
        input "batteries", "capability.battery", title: "Which Batteries?", multiple: true, required: false
       	input "powers", "capability.powerMeter", title: "Power Meters", required:false, multiple: true
    	input "energys", "capability.energyMeter", title: "Energy Meters", required:false, multiple: true 
	}
}

mappings {
	path("/switches") {
		action: [
			GET: "listSwitches"
		]
	}
	path("/switches/:id") {
		action: [
			GET: "showSwitch"
		]
	}
	path("/switches/:id/:command") {
		action: [
			GET: "updateSwitch"
		]
	}
    path("/switches/:id/:events") {
		action: [
			GET: "showSwitchEvents"
		]
	}
    path("/dimmers") {
		action: [
			GET: "listDimmers"
		]
	}
	path("/dimmers/:id") {
		action: [
			GET: "showDimmer"
		]
	}
	path("/dimmers/:id/:command") {
		action: [
			GET: "updateDimmer"
		]
	}
    path("/dimmers/:id/:command/:level") {
		action: [
			GET: "updateDimmer"
		]
	}      
    path("/locks") {
		action: [
			GET: "listLocks"
		]
	}
    path("/locks/:id") {
		action: [
			GET: "showLock"
		]
	}
    path("/locks/:id/:command") {
		action: [
			GET: "updateLock"
		]
	}
    path("/motions") {
		action: [
			GET: "listMotions"
		]
	}
	path("/motions/:id") {
		action: [
			GET: "showMotion"
		]
	}
     path("/motions/:id/events") {
		action: [
			GET: "showMotionEvents"
		]
	}
     path("/illuminants") {
		action: [
			GET: "listIlluminants"
		]
	}
	path("/illuminants/:id") {
		action: [
			GET: "showIlluminant"
		]
	}
     path("/contacts") {
		action: [
			GET: "listContacts"
		]
	}
	path("/contacts/:id") {
		action: [
			GET: "showContact"
		]
	}
    path("/temperatures") {
		action: [
			GET: "listTemperatures"
		]
	}
	path("/temperatures/:id") {
		action: [
			GET: "showTemperature"
		]
	}
    path("/temperatures/:id/:command") {
		action: [
			GET: "updateTemperatures"
		]
	}
    path("/humidities") {
		action: [
			GET: "listHumidities"
		]
	}
    path("/humidities/:id") {
		action: [
			GET: "showHumidity"
		]
	}
    path("/batteries") {
		action: [
			GET: "listBatteries"
		]
	}
    path("/batteries/:id") {
		action: [
			GET: "showBattery"
		]
	}
    path("/powers") {
		action: [
			GET: "listPowers"
		]
	}
    path("/powers/:id") {
		action: [
			GET: "showPower"
		]
	}
    path("/energies") {
		action: [
			GET: "listEnergies"
		]
	}
    path("/energies/:id") {
		action: [
			GET: "showEnergy"
		]
	}
	path("/thermostats") {
		action: [
			GET: "listThermostats"
		]
	}
	path("/thermostats/:id") {
		action: [
			GET: "showThermostat"
		]
	}  
	path("/thermostats/:id/:command/:temp") {
		action: [
			GET: "updateThermostat"
		]
	}
    path("/presence") {
		action: [
			GET: "listPresence"
		]
	}
	path("/presences/:id") {
		action: [
			GET: "showPresence"
		]
	}
}

def installed() {}

def updated() {}

def listSwitches() {
	//switches.collect{device(it,"switch")}
	switches.collect{[type: "switch", id: it.id, name: it.displayName, status: it.currentValue('switch'), level: it.currentValue('level'), label: it.label]}?.sort{it.name}
}

def showSwitch() {
	show(switches, "switch")
}

def showSwitchEvents() {
    getEvents(switches, "switch")
}

void updateSwitch() {
	update(switches)
}

def listDimmers() {
	//dimmers.collect{device(it,"dimmer")}
	switches.collect{[type: "dimmer", id: it.id, name: it.displayName, status: it.currentValue('dimmer'), level: it.currentValue('level'), label: it.label]}?.sort{it.name}
}

def showDimmer() {
	show(dimmers, "level")
}

void updateDimmer() {
	update(dimmers)
}

def listLocks() {
    lock?.collect{[type: "lock", id: it.id, name: it.displayName, status: it.currentValue('lock')]}?.sort{it.name}

}
def showLock() {
	show(lock, "lock")
}
void updateLock() {
	update(lock)
}

def listTemperatures() {
	temperatures?.collect{[type: "temperatureMeasurement", id: it.id, name: it.displayName, status: it.currentValue('temperature')]}?.sort{it.name}

}
def showTemperature() {
	show(temperatures, "temperature")
}

def listHumidities() {
	humidities?.collect{[type: "relativeHumidityMeasurement", id: it.id, name: it.displayName, status: it.currentValue('humidity')]}?.sort{it.name}

}
def showHumidity() {
	show(humidities, "humidity")
}

def listPresence() {
	presence?.collect{[type: "presence", id: it.id, name: it.displayName, status: it.currentValue('presence')]}?.sort{it.name}
}

def showPresence() {
	show(locations, "presence")
}

def listMotions() {
     motions?.collect{[type: "motion", id: it.id, name: it.displayName, status: it.currentValue('motion')]}?.sort{it.name}
}

def showMotion() {
	show(motions, "motion")  
}

def showMotionEvents() {
    getEvents(motions, "motion")
}

def listIlluminants() {
    illuminants?.collect{[type: "illuminant", id: it.id, name: it.displayName, status: it.currentValue('illuminance')]}?.sort{it.name}
}

def showIlluminant() {
	show(illuminants, "illuminance")
}

def listContacts() {
     contacts?.collect{[type: "contact", id: it.id, name: it.displayName, status: it.currentValue('contact')]}?.sort{it.name}

}
def showContact() {
	show(contacts, "contact")
}

def listBatteries() {
     batteries?.collect{[type: "battery", id: it.id, name: it.displayName, status: it.currentValue('battery')]}?.sort{it.name}

}
def showBattery() {
	show(batteries, "battery")
}

def listPowers() {
     powers?.collect{[type: "power", id: it.id, name: it.displayName, status: it.currentValue('power')]}?.sort{it.name}

}
def showPower() {
	show(powers, "power")
}

def listEnergies() {
     energys?.collect{[type: "energy", id: it.id, name: it.displayName, status: it.currentValue('energy')]}?.sort{it.name}

}
def showEnergy() {
	show(energys, "energy")
}

def listThermostats() {
	thermostats.collect{device(it,"thermostat")}
}

def showThermostat() {
	show(thermostats, "thermostat")
}

void updateThermostat() {

	def device = thermostats.find { it.id == params.id }
	def command = params.command
	def temp = params.temp

    log.debug "$command ${params.id} at $temp"

	if(command == 'heat')
	{
		device.setHeatingSetpoint(temp)
	}
	else if(command == 'cool')
	{
	  device.setCoolingSetpoint(temp)	
	}
}

def deviceHandler(evt) {}

private void update(devices) {
	log.debug "update, request: params: ${params}, devices: $devices.id"
    
    
	//def command = request.JSON?.command
    def command = params.command
    def level = params.level
    //let's create a toggle option here
	if (command) 
    {
		def device = devices.find { it.id == params.id }
		if (!device) {
			httpError(404, "Device not found")
		} else {
        	if(command == "toggle")
       		{
            	if(device.currentValue('switch') == "on")
                  device.off();
                else
                  device.on();
       		}
            else if(command == "level")
            {
            	device.setLevel(level.toInteger())
            }
            else if(command == "events")
            {
            	device.events(max: 20)
            }
       		else
       		{
				device."$command"()
            }
		}
	}
}

private show(devices, type) {
	def device = devices.find { it.id == params.id }
	if (!device) {
		httpError(404, "Device not found")
	}
	else {
		def attributeName = type == "motionSensor" ? "motion" : type
		def s = device.currentState(attributeName)
		[id: device.id, label: device.displayName, value: s?.value, unitTime: s?.date?.time, type: type]
	}
}

private getEvents(devices, type) {
	def device = devices.find { it.id == params.id }
	if (!device) {
		httpError(404, "Device not found")
	}
	else {
        def events = device.events(max: 40)
         events = events.findAll{it.name == type}
        def result = events.collect{item(device, it)}
	    result
    }	
}

private item(device, s) {	
	device && s ? [uid: s.id, device_id: device.id, label: device.displayName, name: s.name, value: s.value, date: s.date] : null
}

private device(it, type) {
	it ? [id: it.id, label: it.label, name: it.displayName, type: type, level: it.currentValue('level'), status: it.currentValue('switch')] : null
}