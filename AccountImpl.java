/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author luisgm
 */
public class AccountImpl implements Account {

    private float balance = 0;
    private final String cardNumber;
    private final String pass;
    private int[]  codes;

    public AccountImpl(String cardNumber, String pass, float balance) {
        super();
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pass = pass;
    }

    AccountImpl(String cardNumber, String pass, float balance, int[] codes) {
        super();
        this.codes = codes;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pass = pass;
         
    }

    @Override
    public String getName() {
        return this.cardNumber;
    }

    @Override
    public String getPass() {
        return this.pass;
    }

    @Override
    public float getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(float value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param value
     * @return 
     * @throws server.RejectedException
     */
    @Override
    public String withdraw(float value) throws RejectedException {
        String m = "";
        if (value < 0) {
            throw new RejectedException("Rejected: Account " + cardNumber + ": Illegal value: " + value);
        }
        if ((balance - value) < 0) {
            throw new RejectedException("Rejected: Account " + cardNumber
                    + ": Negative balance on withdraw: " + (balance - value));
        }
        balance -= value;
        System.out.println("Transaction: Account " + cardNumber + ": withdraw: $" + value + ", balance: $"
                + balance);
        m="Transaction: Account " + cardNumber + ": withdraw: $" + value + ", balance: $"
                + balance;
        return m;
    }

    @Override
    public int[] getCodes() {
        return codes;
    }
    	/**
	 * Validates a withdrawal based on an input two-digit number.
	 * 
	 * @param code The two-digit code used for validation.
	 * @return True if correct code, false otherwise.
	 */
    @Override
	   public boolean validateWithdrawal(String code) {
		int key = Integer.parseInt(code);
		return validateSearch(key);
	}

	/**
	 * Search for a valid key stored locally, inside a list.
	 * 
	 * @param key The key to search for.
	 * @return True if the key is found, false otherwise.
	 */
	private boolean validateSearch(int key) {
		for (int i = 0; i < codes.length; i++) {
			if (key == codes[i]) {
				return true;
			}
		}
		return false;
	}
}
