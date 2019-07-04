/**
 *  Copyright 2016 Paul Cifarelli
 *  Updated 2018/2019 Corey Murnaghan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
    definition (name: "Pi Simulated Temperature Sensor °F", namespace: "audiofreak9", author: "Corey Murnaghan") {
        capability "Temperature Measurement"
        capability "Sensor"
        command "setTemperature", ["number"]
    }
 
    // UI tile definition
    tiles(scale:2) {
        valueTile("temperature", "device.temperature", height: 4, width: 6, canChangeIcon: true) {
            state("temperature", label:'${currentValue}°', unit:"F",
                backgroundColors:[
                    [value: 31, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
                    [value: 74, color: "#44b621"],
                    [value: 84, color: "#f1d801"],
                    [value: 95, color: "#d04e00"],
                    [value: 96, color: "#bc2323"]
                ]
            )
        }
        main "temperature"
        details("temperature")
    }
}
 
// Parse incoming device messages to generate events
def parse(String description) {
    def pair = description.split(":")
    createEvent(name: pair[0].trim(), value: pair[1].trim())
}
 
def setTemperature(value) {
    sendEvent(name:"temperature", value: value, unit: "F")
}