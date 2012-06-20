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

package com.thales.ntis.subscriber.dao;

import org.springframework.stereotype.Service;

@Service
public class NetworkModelDaoImpl implements NetworkModelDao {

    /*
     * This method needs to query the network model and determine whether the
     * first lane for the MIDAS site (identified by siteGUID) is LANE_1 or
     * HARD_SHOULDER.
     * 
     * If it is LANE_1 it should return 1.
     * 
     * If it is HARD_SHOULDER, it should return 0.
     */
    @Override
    public int getStartingLaneForMidasSite(String siteGUID) {

        // pseudo code

        /*
         * LaneEnum is the datexII type for lane present on a MIDAS site.
         * Available for each MIDAS site found in the Network Model. Each MIDAS
         * site will ALWAYS have either LANE_1 or HARD_SHOULDER, along with
         * other lane numbers.
         * 
         * List<LaneEnum> lanes = getLanesForMidasSiteFromNetworkModel(siteGUID)
         * 
         * int startingLane;
         * 
         * for (LaneEnum lane : lanes) {
         * 
         * if(lane value equals LANE_!) { startingLane = 1; }
         * 
         * if(lane value equals HARD_SHOULDER) { startingLane = zero; }
         * 
         * }
         * 
         * return startingLane;
         */

        return 1;
    }

}
