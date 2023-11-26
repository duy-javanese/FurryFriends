/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class ExchangeStatus {
    private int exchangeStatusId;
    private String exchangeStatusValue;

    public ExchangeStatus() {
    }

    public ExchangeStatus(int ExchangeStatusId, String ExchangeStatusValue) {
        this.exchangeStatusId = ExchangeStatusId;
        this.exchangeStatusValue = ExchangeStatusValue;
    }

    public int getExchangeStatusId() {
        return exchangeStatusId;
    }

    public void setExchangeStatusId(int ExchangeStatusId) {
        this.exchangeStatusId = ExchangeStatusId;
    }

    public String getExchangeStatusValue() {
        return exchangeStatusValue;
    }

    public void setExchangeStatusValue(String ExchangeStatusValue) {
        this.exchangeStatusValue = ExchangeStatusValue;
    }
    
}
