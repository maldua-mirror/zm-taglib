/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2006, 2007, 2008, 2009, 2010, 2012, 2013 Zimbra, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZTagLibException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class ModifyContactTag extends ContactOpTag {

    private String mId;
    private String mVar;
    private String mFolderid;
    private String mTagids;
    private boolean mReplace;

    public void setId(String id) { mId = id; }
    public void setFolderid(String folderid) { mFolderid = folderid; }
    public void setVar(String var) { mVar = var; }
    public void setReplace(boolean replace) { mReplace = replace; }
    public void setTags(String tagids) { mTagids = tagids; } 

    public void doTag() throws JspException, IOException {
        try {
            getJspBody().invoke(null);

            if (mAttrs.isEmpty() || allFieldsEmpty()){
                throw ZTagLibException.EMPTY_CONTACT("can't set all fields to blank", null);
            }

            String id = (mId == null || mId.length() == 0) ?
                    getMailbox().createContactWithMembers(mFolderid, mTagids, mAttrs, mMembers).getId() :
                    getMailbox().modifyContactWithMembers(mId, mReplace, mAttrs, mMembers).getId();
            getJspContext().setAttribute(mVar, id, PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
