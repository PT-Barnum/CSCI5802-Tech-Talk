/*
 * Copyright (c) 2012 - 2014 Ngewi Fet <ngewif@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gnucash.android.test.unit.model;

import android.graphics.Color;

import org.assertj.core.api.Assertions;
import org.gnucash.android.model.Account;
import org.gnucash.android.model.Commodity;
import org.gnucash.android.model.Money;
import org.gnucash.android.model.Transaction;
import org.gnucash.android.test.unit.testutil.ShadowUserVoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28, packageName = "org.gnucash.android", shadows = {ShadowUserVoice.class})
public class AccountTest{


	private Commodity testCommodity = new Commodity("Test Commodity", "TST", 100);
	private Transaction testTransaction1 = new Transaction("Test Transaction 1");
	private Transaction testTransaction2 = new Transaction("Test Transaction 2");
	private Transaction testTransaction3 = new Transaction("Test Transaction 3");

	@Test
	public void testAccountUsesDefaultCurrency1(){
		Account account = new Account("Dummy account");
		assertThat(account.getCommodity().getCurrencyCode()).isEqualTo("USD");
	}

	@Test
	public void testAccountAlwaysHasUID(){
		Account account = new Account("Dummy");
		assertThat(account.getUID()).isNotNull();
	}

	@Test
	public void testTransactionsHaveSameCurrencyAsAccount(){
		Account acc1 = new Account("Japanese", Commodity.JPY);
		acc1.setUID("simile");
		Transaction trx = new Transaction("Underground");
		Transaction term = new Transaction( "Tube");

		assertThat(trx.getCurrencyCode()).isEqualTo("USD");

		acc1.addTransaction(trx);
		acc1.addTransaction(term);

		assertThat(trx.getCurrencyCode()).isEqualTo("JPY");
		assertThat(term.getCurrencyCode()).isEqualTo("JPY");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetInvalidColorCode(){
		Account account = new Account("Test");
		account.setColor("443859");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetColorWithAlphaComponent(){
		Account account = new Account("Test");
		account.setColor(Color.parseColor("#aa112233"));
	}

	@Test
	public void shouldSetFullNameWhenCreated(){
		String fullName = "Full name ";
		Account account = new Account(fullName);
		assertThat(account.getName()).isEqualTo(fullName.trim()); //names are trimmed
		assertThat(account.getFullName()).isEqualTo(fullName.trim()); //names are trimmed
	}

	@Test
	public void settingNameShouldNotChangeFullName(){
		String fullName = "Full name";
		Account account = new Account(fullName);

		account.setName("Name");
		assertThat(account.getName()).isEqualTo("Name");
		assertThat(account.getFullName()).isEqualTo(fullName);
	}

	@Test
	public void newInstance_shouldReturnNonNullValues() {
		Account account = new Account("Test account");
		assertThat(account.getDescription()).isEqualTo("");
		assertThat(account.getColor()).isEqualTo(Account.DEFAULT_COLOR);
	}

	/**
	 * Check if the account commodity is set to default
	 */
	@Test
	public void testAccountCommodityInit(){
		//Setup
		String accountName = "John Doe";

		//Exercise
		Account account = new Account(accountName);
		Commodity usd = new Commodity("US Dollars", "USD", 100);

		//Verify
		assertThat(account.getCommodity()).isEqualTo(usd);
	}

	/**
	 * Check if the account name is initialized correctly
	 */
	@Test
	public void testAccountNameInit(){
		//Setup
		String accountName = "John Doe";

		//Exercise
		Account account = new Account(accountName);

		//Verify
		assertThat(account.getName()).isEqualTo(accountName);
	}

	/**
	 * Check if the account commodity is initialized correctly
	 */
	@Test
	public void testAccountCommodityConstructorCommodityInit(){
		//Setup
		String accountName = "John Doe";

		//Exercise
		Account account = new Account(accountName, testCommodity);

		//Verify
		assertThat(account.getCommodity()).isEqualTo(testCommodity);
	}

	/**
	 * Check if the account name is initialized correctly
	 */
	@Test
	public void testAccountCommodityConstructorNameInit(){
		//Setup
		String accountName = "John Doe";

		//Exercise
		Account account = new Account(accountName, testCommodity);

		//Verify
		assertThat(account.getName()).isEqualTo(accountName);
	}

	/**
	 * Check if addTransaction() correctly adds the transaction to the transaction list
	 */
	@Test
	public void testAccountAddSingleTransaction(){
		//Setup
		String accountName = "John Doe";
		Account account = new Account(accountName, testCommodity);

		//Exercise
		account.addTransaction(testTransaction1);

		//Verify
		assertThat(account.getTransactions().get(0)).isEqualTo(testTransaction1);
	}

	/**
	 * Check if addTransaction() correctly handles adding multiple transactions
	 */
	@Test
	public void testAccountAddMultipleTransactions(){
		//Setup
		String accountName = "John Doe";
		Account account = new Account(accountName, testCommodity);

		//Exercise
		account.addTransaction(testTransaction1);
		account.addTransaction(testTransaction2);
		account.addTransaction(testTransaction3);

		//Verify
		assertThat(account.getTransactions().get(0)).isEqualTo(testTransaction1);
		assertThat(account.getTransactions().get(1)).isEqualTo(testTransaction2);
		assertThat(account.getTransactions().get(2)).isEqualTo(testTransaction3);
	}

	@Test
	public void testAccountSetter1(){
		Account account = new Account("Test Account Name 1");		// Exercise function
		assertThat(account.getName()).isEqualTo("Test Account Name 1");		// Verify results
	}

	@Test
	public void testAccountSetter2(){
		Account account = new Account("Different Account Name 2");		// Exercise function
		String compare_name = "Different Account Name 2";
		assertThat(account.getName()).isEqualTo(compare_name);		// Verify results
	}

	@Test
	public void testAccountNameAndCommodity1(){
		Account account = new Account("Test Name 1", Commodity.EUR);		// Exercise function
		String compare_name = "Test Name 1";
		String mNemonic = "EUR";
		assertThat(account.getName()).isEqualTo(compare_name);		// Verify name
		assertThat(account.getCommodity().getCurrencyCode()).isEqualTo(mNemonic);		// Verify
	}																					// Commodity

	@Test
	public void testAccountNameAndCommodity2(){
		String account_name = "Test Name 2";		// Setup
		Commodity account_commodity = new Commodity("", "PES", 1);		// Setup
		Account account = new Account(account_name, account_commodity);		// Exercise function
		assertThat(account.getName()).isEqualTo("Test Name 2");		// Verify name
		assertThat(account.getCommodity().getCurrencyCode()).isEqualTo("PES");		// Verify
	}																				// Commodity

	@Test
	public void testAddTransaction1(){
		// Setup
		Account account = new Account("Dummy Account");
		Transaction account_transaction = new Transaction("Dummy Transaction");

		// Exercise
		account.addTransaction(account_transaction);
		Transaction check_transaction = account.getTransactions().get(0);

		// Verify
		assertThat(check_transaction.getDescription()).isEqualTo("Dummy Transaction");
	}

	@Test
	public void testAddTransaction2(){
		// Setup
		Account account = new Account("Dummy Account 2");
		Transaction account_transaction = new Transaction("Dummy Transaction 2");

		// Exercise
		account.addTransaction(account_transaction);

		// Verify
		assertThat(account.getTransactions().get(0).getDescription()).isEqualTo("Dummy Transaction 2");
	}

	/**
	 * Check that getTransactionCount() correctly returns the absolute number of transactions added
	 */
	@Test
	public void testTransactionCount1(){
		//Setup
		Account account = new Account("Account Name");
		Transaction transaction1 = new Transaction("Transaction 1");
		Transaction transaction2 = new Transaction("Transaction 2");

		//exercise
		account.addTransaction(transaction1);
		account.addTransaction(transaction2);

		//verify
		assertThat(account.getTransactionCount()).isEqualTo(2);
	}

	/**
	 * Check that getTransactionCount() will return the same value if the same amount of
	 * transactions are added to two separate accounts
	 */
	@Test
	public void testTransactionCount2(){
		//Setup
		Account account_one = new Account("Account 1");
		Transaction transaction_one = new Transaction("Transaction 1");
		Account account_two = new Account("Account 2");
		Transaction transaction_two = new Transaction("Transaction 2");

		//Exercise
		account_one.addTransaction(transaction_one);
		account_two.addTransaction(transaction_two);

		//verify
		assertThat(account_one.getTransactionCount()).isEqualTo(account_two.getTransactionCount());
	}

	/**
	 * Checking that getBalance() will return the correct value for new accounts
	 */
	@Test
	public void testGetBalance1(){
		//Setup
		Account account = new Account("Account Name");
		Money money = new Money("0.00", "USD");

		//Verify
		assertThat(account.getBalance().asString()).isEqualTo(money.asString());
	}

	/**
	 * Checking that getBalance() will return the correct value after money has been
	 * added to the account
	 */
	@Test
	public void testGetBalance2(){
		//Setup
		Account account = new Account("Account Name");
		Money money = new Money("100.00", "USD");
		Transaction dummyTransaction = Mockito.mock(Transaction.class);
		Money dummyTransactionMoney = new Money("100.00", "USD");
		Mockito.doReturn(dummyTransactionMoney).when(dummyTransaction).getBalance(any());

		//Exercise
		account.addTransaction(dummyTransaction);

		//Verify
		assertThat(account.getBalance()).isEqualTo(money);
	}

	/**
	 * Checking that setColor() correctly changes the color value for the account
	 */
	@Test
	public void testSetColor1(){
		//Setup
		Account account = new Account("Account Name");
		int color = 0xFFFF0000;

		//exercise
		account.setColor(color);

		//verify
		assertThat(account.getColor()).isEqualTo(color);

	}

	/**
	 * Checking that setColor() correctly throws and exception when given a bad color value
	 */
	@Test
	public void testSetColor2(){
		//setup
		Account account = new Account("Account Name");
		//assert(account.setColor(254) = IllegalArgumentException.class);

		try{

			//exercise
			account.setColor(254);

			//verify
			Assertions.failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		}
		catch (IllegalArgumentException ignored){
		}
	}

}
