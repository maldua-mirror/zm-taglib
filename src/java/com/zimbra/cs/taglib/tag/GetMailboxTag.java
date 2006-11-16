/*
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * 
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 ("License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.zimbra.com/license
 * 
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 * 
 * The Original Code is: Zimbra Collaboration Suite Server.
 * 
 * The Initial Developer of the Original Code is Zimbra, Inc.
 * Portions created by Zimbra are Copyright (C) 2006 Zimbra, Inc.
 * All Rights Reserved.
 * 
 * Contributor(s): 
 * 
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.cs.taglib.tag;

import com.zimbra.cs.taglib.bean.ZMailboxBean;
import com.zimbra.cs.service.ServiceException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class GetMailboxTag extends ZimbraSimpleTag {
    
    private String mVar;
    private boolean mRefreshAccount;
    
    public void setVar(String var) { this.mVar = var; }
    public void setRefreshaccount(boolean refresh) { this.mRefreshAccount = refresh; }

    public void doTag() throws JspException, IOException {
        JspContext ctxt = getJspContext();
        ZMailboxBean bean = (ZMailboxBean) ctxt.getAttribute(mVar, PageContext.REQUEST_SCOPE);
        if ( bean == null) {
            bean = new ZMailboxBean(getMailbox());
            ctxt.setAttribute(mVar, bean,  PageContext.REQUEST_SCOPE);
        }
        if (mRefreshAccount) {
            try {
                bean.getAccountInfoReload();
            } catch (ServiceException e) {
                throw new JspTagException(e.getMessage(), e);
            }
        }
    }
}
