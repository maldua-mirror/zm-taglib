/*
 * ***** BEGIN LICENSE BLOCK *****
 *
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2007 Zimbra, Inc.
 *
 * The contents of this file are subject to the Yahoo! Public License
 * Version 1.0 ("License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 * http://www.zimbra.com/license.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 *
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.cs.taglib.bean;

import com.zimbra.cs.zclient.ZAppointmentHit;

public class ZAppointmentHitBean extends ZSearchHitBean {

    private ZAppointmentHit mHit;

    public ZAppointmentHitBean(ZAppointmentHit hit) {
        super(hit, HitType.appointment);
        mHit = hit;
    }
	public ZAppointmentHit getAppointment() {
	    return mHit;
	}

	public String getDocId() {
		return mHit.getId();
	}

	public float getDocScore() {
		return mHit.getScore();
	}

	public String getDocSortField() {
		return mHit.getSortField();
	}
}