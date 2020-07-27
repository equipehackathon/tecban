package com.example.tecban.interface_retrofit;

import com.google.gson.annotations.SerializedName;

public class Saldo{

    public String valor;

    @SerializedName("Content-Type")
    @Expose
    private String contentType;

    @SerializedName("x-fapi-financial-id")
    @Expose
    private String financialId;

    @SerializedName("x-fapi-interaction-id")
    @Expose
    private String interactionId;

    @SerializedName("Authorization")
    @Expose
    private String authorization;

    @SerializedName("AccountId")
    @Expose
    private String accountId;

    @SerializedName("Amount")
    @Expose
    private Double amount;

    @SerializedName("Currency")
    @Expose
    private String currency;

    @SerializedName("CreditDebitIndicator")
    @Expose
    private String creditDebit;

    public Saldo(){}

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFinancialId() {
        return financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCreditDebit() {
        return creditDebit;
    }

    public void setCreditDebit(String creditDebit) {
        this.creditDebit = creditDebit;
    }
}