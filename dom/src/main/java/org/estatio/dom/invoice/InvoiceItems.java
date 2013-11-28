/*
 *
 *  Copyright 2012-2013 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.dom.invoice;

import java.util.List;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Prototype;

import org.estatio.dom.EstatioDomainService;

public class InvoiceItems extends EstatioDomainService<InvoiceItem> {

    public InvoiceItems() {
        super(InvoiceItems.class, InvoiceItem.class);
    }
    
    // //////////////////////////////////////

    @ActionSemantics(Of.NON_IDEMPOTENT)
    @Programmatic
    public InvoiceItem newInvoiceItem(
            final Invoice invoice,
            final @Named("Due date") LocalDate dueDate) {
        InvoiceItem invoiceItem = newTransientInstance();
        invoiceItem.setInvoice(invoice);
        invoiceItem.setDueDate(dueDate);
        persistIfNotAlready(invoiceItem);
        return invoiceItem;
    }

    // //////////////////////////////////////
    
    @Prototype
    @ActionSemantics(Of.SAFE)
    @MemberOrder(name="Invoices", sequence = "99")
    public List<InvoiceItem> allInvoiceItems() {
        return allInstances();
    }

}