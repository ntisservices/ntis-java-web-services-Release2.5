/*
    Copyright (C) 2011 Thales Transportation Systems UK
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
    IN THE SOFTWARE.
 */
package com.thales.ntis.subscriber.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.TextPage;
import com.thales.ntis.subscriber.datex.VmsMessage;
import com.thales.ntis.subscriber.datex.VmsMessageIndexVmsMessage;
import com.thales.ntis.subscriber.datex.VmsPublication;
import com.thales.ntis.subscriber.datex.VmsText;
import com.thales.ntis.subscriber.datex.VmsTextLineIndexVmsTextLine;
import com.thales.ntis.subscriber.datex.VmsUnit;
import com.thales.ntis.subscriber.datex.VmsUnitVmsIndexVms;

@Service
public class VMSTrafficDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(VMSTrafficDataServiceImpl.class);

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {

        LOG.info("handling VMS Request !");

        try {
            VmsPublication payloadPublication = (VmsPublication)
                    d2LogicalModel.getPayloadPublication();
            if (payloadPublication != null) {
                VmsUnit vmsUnit =
                        payloadPublication.getVmsUnit().get(0);
                if (vmsUnit != null) {
                    LOG.info("Vmsunit GUID: " + vmsUnit.getVmsUnitReference().getId());

                    List<VmsUnitVmsIndexVms> vmsIndexList = vmsUnit.getVms();
                    if (vmsIndexList.size() > 0) {
                        VmsUnitVmsIndexVms vmsUnitVmsIndexVms =
                                vmsIndexList.get(0);
                        List<VmsMessageIndexVmsMessage> vmsMessageList =
                                vmsUnitVmsIndexVms.getVms().getVmsMessage();
                        if (vmsMessageList.size() > 0) {
                            VmsMessage vmsMessage =
                                    vmsMessageList.get(0).getVmsMessage();
                            List<TextPage> textPageList =
                                    vmsMessage.getTextPage();
                            if (textPageList.size() > 0) {
                                TextPage textPage = textPageList.get(0);
                                VmsText vmsText =
                                        textPage.getVmsText();
                                List<VmsTextLineIndexVmsTextLine> vmsTextLineList = vmsText.getVmsTextLine();
                                if (vmsTextLineList.size() > 0) {
                                    VmsTextLineIndexVmsTextLine vmsTextLineIndexVmsTextLine = vmsTextLineList.get(0);
                                    LOG.info(" vms text line " +
                                            vmsTextLineIndexVmsTextLine.getVmsTextLine().getVmsTextLine());
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info("VMSTraffic Data Request: Processing Completed Successfuly");
    }

}
