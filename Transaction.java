/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.Serializable;

/**
 *
 * @author luisgm
 */
class Transaction implements Serializable, TransactionInterface {

    private final String type;
    private String amount;
    private String sCode;
    private String language;
    private String cn;
    private char[] pss;

    public Transaction(String type, String language) {
        this.language = language;
        this.type = type;

    }

    public Transaction(String type, String cn, char[] pss) {
        this.cn = cn;
        this.pss = pss;
        this.type = type;

    }

    public Transaction(String type, String amount, String sCode) {
        this.type = type;
        this.amount = amount;
        this.sCode = sCode;
    }

    @Override
    public String getUser() {
        return this.cn;
    }

    @Override
    public String getPss() {
        String pss2 = "";
        for (int i = 0; i < pss.length; i++) {
            pss2 += pss[i];
        }
        return pss2;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getAmount() {
        return this.amount;
    }

    @Override
    public String getSCode() {
        return this.sCode;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

}
