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

import java.sql.Timestamp;

/**
 * This is an example model class for demo purpose only. Amend accordingly for
 * your needs.
 * 
 */
public class TrafficData {

    private String guid;
    private Timestamp dbDatetime;
    private Timestamp midasDatetime;
    private String equipment;
    private int laneNumber;

    private Integer flowCat1;
    private Integer flowCat2;
    private Integer flowCat3;
    private Integer flowCat4;
    private Integer avgSpeedKmh;
    private Integer avgOccupancy;
    private Integer avgHeadway;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Timestamp getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(Timestamp dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    public Timestamp getMidasDatetime() {
        return midasDatetime;
    }

    public void setMidasDatetime(Timestamp midasDatetime) {
        this.midasDatetime = midasDatetime;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public Integer getFlowCat1() {
        return flowCat1;
    }

    public void setFlowCat1(Integer flowCat1) {
        this.flowCat1 = flowCat1;
    }

    public Integer getFlowCat2() {
        return flowCat2;
    }

    public void setFlowCat2(Integer flowCat2) {
        this.flowCat2 = flowCat2;
    }

    public Integer getFlowCat3() {
        return flowCat3;
    }

    public void setFlowCat3(Integer flowCat3) {
        this.flowCat3 = flowCat3;
    }

    public Integer getFlowCat4() {
        return flowCat4;
    }

    public void setFlowCat4(Integer flowCat4) {
        this.flowCat4 = flowCat4;
    }

    public Integer getAvgSpeedKmh() {
        return avgSpeedKmh;
    }

    public void setAvgSpeedKmh(Integer avgSpeedKmh) {
        this.avgSpeedKmh = avgSpeedKmh;
    }

    public Integer getAvgOccupancy() {
        return avgOccupancy;
    }

    public void setAvgOccupancy(Integer avgOccupancy) {
        this.avgOccupancy = avgOccupancy;
    }

    public Integer getAvgHeadway() {
        return avgHeadway;
    }

    public void setAvgHeadway(Integer avgHeadway) {
        this.avgHeadway = avgHeadway;
    }

}
