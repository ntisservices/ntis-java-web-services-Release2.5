/*
    Copyright (C) 2012 Thales Transportation Systems UK
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
    IN THE SOFTWARE.
 */
package com.thales.ntis.subscriber.model;

/**
 * 
 * @author Anirudha Deshpande
 * 
 */
public enum FeedType {

    ANPR("ANPR Journey Time Data"),

    MIDAS("MIDAS Loop Traffic Data"),

    TMU("TMU Loop Traffic Data"),

    FUSED_SENSOR_ONLY("Fused Sensor-only PTD"),

    FUSED_FVD_AND_SENSOR_PTD("Fused FVD and Sensor PTD"),

    VMS("VMS and Matrix Signal Status Data"),

    NTIS_MODEL_UPDATE_NOTIFICATION("NTIS Model Update Notification");

    private String feedTypeText;

    FeedType(String feedTypeText) {
        this.feedTypeText = feedTypeText;
    }

    /**
     * 
     * @return
     */
    public String upperCase() {
        return value().toUpperCase();
    }

    /**
     * 
     * @return
     */
    public String lowerCase() {
        return value().toLowerCase();
    }

    /**
     * 
     * @return
     */
    public String value() {
        return feedTypeText;
    }
}
