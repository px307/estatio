package org.estatio.capex.dom.invoice.state.transitions;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.services.registry.ServiceRegistry2;

import org.estatio.capex.dom.invoice.IncomingInvoice;
import org.estatio.capex.dom.invoice.state.IncomingInvoiceStateTransitionType;
import org.estatio.capex.dom.state.StateTransitionService;

public abstract class IncomingInvoice_transitionAbstract {

    protected final IncomingInvoice incomingInvoice;
    protected final IncomingInvoiceStateTransitionType transitionType;

    protected IncomingInvoice_transitionAbstract(
            final IncomingInvoice incomingInvoice,
            final IncomingInvoiceStateTransitionType transitionType) {
        this.incomingInvoice = incomingInvoice;
        this.transitionType = transitionType;
    }

    @Action()
    public IncomingInvoice $$(
                            @Nullable
                            final String comment){
        stateTransitionService.apply(incomingInvoice, transitionType, comment);
        return incomingInvoice;
    }

    public boolean hide$$() {
        return !stateTransitionService.canApply(incomingInvoice, transitionType);
    }

    @Inject
    private ServiceRegistry2 serviceRegistry2;
    @Inject
    private StateTransitionService stateTransitionService;

}
