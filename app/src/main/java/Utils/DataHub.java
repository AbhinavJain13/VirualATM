package Utils;

/**
 * Created by Ramakant on 3/12/2016.
 */
public class DataHub {

    public static String USER_ID = "ramakant.singh17@gmail.com";
    public static String USER_PASS_CODE = "ICIC2381";

    public static String CLIENT_ID = "client_id";
    public static String PASSWORD = "password";
    public static String EQUAL_OPERATOR = "=";
    public static String AND_OPERATOR = "&";
    public static String QUESTION_MARK_OPERATOR = "?";

    //authentication url
    public static String AUTHENTICATION_TOKEN = "token";
    public static String AUTHENTICATION_URL = "http://corporate_bank.mybluemix.net/corporate_banking/mybank/authenticate_client";

    //balance information
    public static String BALANCE = "balance";
    public static String ACCOUNT_NO = "accountno";
    public static String ACCOUNT_TYPE = "accounttype";
    public static String BALANCE_TIME = "balancetime";
    public static String BALANCE_ENQUIRY_URL = "http://retailbanking.mybluemix.net/banking/icicibank/balanceenquiry";

    //bank account summary
    public static String CUSTOMER_ID = "custid";
    public static String PRODUCT_DISC = "product_desc";
    public static String PRODUCT_TYPE = "product_type";
    public static String SUB_PRODUCT_TYPE = "sub_product_type";
    public static String ACCOUNT_STATUS = "account_status";
    public static String MOBILE_NUMBER = "mobileno";
    public static String PRODUCT_CATEGORY = "product_category";
    public static String BANK_ACCOUNT_SUMMARY_URL = "http://retailbanking.mybluemix.net/banking/icicibank/account_summary";

    //Mini statement
    public static String TRANSACTION_DATE = "transactiondate";
    public static String CLOSING_BALANCE = "closing_balance";
    public static String CREDIT_DEBIT_FLAG = "credit_debit_flag";
    public static String TRANSACTION_AMOUNT = "transaction_amount";
    public static String REMARK = "remark";
    public static String MINI_STATEMENT_URL = "http://retailbanking.mybluemix.net/banking/icicibank/recenttransaction";

    //fund transfer
    public static String FUND_TRANSFER_URL = "http://retailbanking.mybluemix.net/banking/icicibank/fundTransfer";
    public static String SOURCE_ACCOUNT = "srcAccount";
    public static String DESTINATION_ACCOUNT = "destAccount";
    public static String AMOUNT = "amt";
    public static String PAYEE_DESCRIPTION = "payeeDesc";
    public static String PAYEE_ID = "payeeId";
    public static String PAYEE_TYPE = "payeeType";
    public static String PAYMENT_TYPE = "paymentType";
    public static String TYPE_OF_TRANSECTION = "type_of_transaction";

    public static String TRANSACTION_DATE_RESPONSE = "transaction_date";//response
    public static String DESTINATION_ACCOUNT_RESPONSE = "destination_accountno";
    public static String REFERENCE_RESPONSE = "referance_no";
    public static String TRANSACTION_AMOUNT_RESPONSE = "transaction_amount";//response
    public static String PAYEE_NAME_RESPONSE = "payee_name";
    public static String PAYEE_ID_RESPONSE = "payee_id";
    public static String STATUS_RESPONSE = "status";


    //test data, account 1
    public static String CARD_ACCOUNT_NO_1 = "4111133444441528";
    public static String LOAN_ACCOUNT_NO_1 = "LBMUM11112221528";
    public static String CUST_ID_1 = "88882528";
    public static String ACCOUNT_NO_CUST_ID_1 = "5555666677771528";
    public static String TREASURY_CURRENCY_PAIR_1 = "AUD/INR,SGD/INR,AUD/INR,SGD/INR,AUD/INR,SGD/INR";
    public static String TREASURY_USER_ID_1 = "A1";
    public static String TREASURY_CUST_ID_1 = "88882528";

    //test data, account 2
    public static String CARD_ACCOUNT_NO_2 = "4111133444441529";
    public static String LOAN_ACCOUNT_NO_2 = "LBMUM11112221529";
    public static String CUST_ID_2 = "88882529";
    public static String ACCOUNT_NO_CUST_ID_2 = "5555666677771529";
    public static String TREASURY_CURRENCY_PAIR_2 = "AUD/INR,SGD/INR,AUD/INR,SGD/INR,AUD/INR,SGD/INR,USD/INR,GBP/INR,EUR/INR,USD/INR,GBP/INR,EUR/INR,USD/INR,GBP/INR,EUR/INR";
    public static String TREASURY_USER_ID_2 = "A2";
    public static String TREASURY_CUST_ID_2 = "88882529";

    //test data, account 3  //MY ATM ACCOUNT (this Account will be used as an ATM For Now)
    public static String ATM_CARD_ACCOUNT_NO = "4111133444441530";
    public static String ATM_LOAN_ACCOUNT_NO = "LBMUM11112221530";
    public static String ATM_CUST_ID = "88882530";
    public static String ATM_ACCOUNT_NO = "5555666677771530";
    public static String ATM_TREASURY_CURRENCY_PAIR = "AUD/INR,SGD/INR,AUD/INR,SGD/INR,AUD/INR,SGD/INR,USD/INR,GBP/INR,EUR/INR,USD/INR,GBP/INR,EUR/INR,USD/INR,GBP/INR,EUR/INR,CAD/INR,AUD/INR,USD/INR,EUR/INR,GBP/INR,CAD/INR,AUD/INR,USD/INR,EUR/INR,GBP/INR,CAD/INR,AUD/INR,USD/INR,EUR/INR,GBP/INR\"";
    public static String ATM_TREASURY_USER_ID = "A3";
    public static String ATM_TREASURY_CUST_ID = "88882530";

    //test data, corporate account
    public static String CORP_ID = "13131981";
    public static String UCC = "767698";
    public static String CUST_ID_CORP = "9090905110";
    public static String RM_MOBILE = "3333445608";
    public static String USER_ID_CORP = "ALEXANDERC";//- MINIM- OLIVIAM- SEBASTIANC

}
