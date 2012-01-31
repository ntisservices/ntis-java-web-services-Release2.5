package com.thales.ntis.subscriber.model;

import java.sql.Timestamp;

/**
 * This is an example model class for demo purpose only. Amend accordingly for
 * your needs.
 * 
 * @author Dev
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
