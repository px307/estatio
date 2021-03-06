package org.estatio.dom.bankmandate.contributed;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.estatio.dom.agreement.Agreement;
import org.estatio.dom.agreement.AgreementRepository;
import org.estatio.dom.agreement.role.AgreementRoleTypeRepository;
import org.estatio.dom.agreement.type.AgreementTypeRepository;
import org.estatio.dom.bankmandate.BankMandate;
import org.estatio.dom.bankmandate.BankMandateAgreementRoleTypeEnum;
import org.estatio.dom.bankmandate.BankMandateAgreementTypeEnum;
import org.estatio.dom.party.Organisation;

@Mixin(method="act")
public class Organisation_bankMandates {

    private final Organisation organisation;

    public Organisation_bankMandates(Organisation organisation) {
        this.organisation = organisation;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(contributed = Contributed.AS_ASSOCIATION)
    @CollectionLayout(defaultView = "table")
    public List<BankMandate> act() {
        final List<Agreement> agreements = agreementRepository
                .findByAgreementTypeAndRoleTypeAndParty(
                        BankMandateAgreementTypeEnum.MANDATE.findUsing(agreementTypeRepository),
                        BankMandateAgreementRoleTypeEnum.DEBTOR.findUsing(agreementRoleTypeRepository),
                        organisation);
        return agreements.stream()
                .filter(BankMandate.class::isInstance)
                .map(BankMandate.class::cast)
                .collect(Collectors.toList());
    }

    @Inject
    AgreementRepository agreementRepository;

    @Inject
    AgreementTypeRepository agreementTypeRepository;

    @Inject
    AgreementRoleTypeRepository agreementRoleTypeRepository;

}
