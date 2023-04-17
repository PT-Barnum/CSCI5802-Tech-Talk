/*
 * Copyright (c) 2012 - 2015 Ngewi Fet <ngewif@gmail.com>
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

package org.gnucash.android.test.ui;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import org.gnucash.android.R;
import org.gnucash.android.app.GnuCashApplication;
import org.gnucash.android.db.DatabaseHelper;
import org.gnucash.android.db.DatabaseSchema;
import org.gnucash.android.db.adapter.AccountsDbAdapter;
import org.gnucash.android.db.adapter.BooksDbAdapter;
import org.gnucash.android.db.adapter.CommoditiesDbAdapter;
import org.gnucash.android.db.adapter.DatabaseAdapter;
import org.gnucash.android.db.adapter.SplitsDbAdapter;
import org.gnucash.android.db.adapter.TransactionsDbAdapter;
import org.gnucash.android.model.Account;
import org.gnucash.android.model.Commodity;
import org.gnucash.android.model.Money;
import org.gnucash.android.model.Split;
import org.gnucash.android.model.Transaction;
import org.gnucash.android.model.TransactionType;
import org.gnucash.android.receivers.TransactionRecorder;
import org.gnucash.android.test.ui.util.DisableAnimationsRule;
import org.gnucash.android.test.ui.util.TestUtils;
import org.gnucash.android.ui.common.UxArgument;
import org.gnucash.android.ui.settings.PreferenceActivity;
import org.gnucash.android.ui.transaction.TransactionFormFragment;
import org.gnucash.android.ui.transaction.TransactionsActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class TransactionsActivityTest {
	private static final String FIRST_ACCOUNT_NAME = "CSCI5802Spring2022 First Account";
	private static final String FIRST_ACCOUNT_UID = "csci5802spring2022_first_account";
	private static final String SECOND_ACCOUNT_NAME = "CSCI5802Spring2022 Second Account";
	private static final String SECOND_ACCOUNT_UID = "csci5802spring2022_second_account";

    private static final String TRANSACTION_AMOUNT = "9.99";
	private static final String TRANSACTION_NAME = "Pizza";
	private static final String TRANSACTIONS_ACCOUNT_UID = "transactions-account";
	private static final String TRANSACTIONS_ACCOUNT_NAME = "Transactions Account";

    private static final String TRANSFER_ACCOUNT_NAME   = "Transfer account";
    private static final String TRANSFER_ACCOUNT_UID    = "transfer_account";
    public static final String CURRENCY_CODE = "USD";
	public static Commodity COMMODITY = Commodity.DEFAULT_COMMODITY;



	private static DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDb;


	private Transaction mTransaction;
	private long mTransactionTimeMillis;

    private static AccountsDbAdapter mAccountsDbAdapter;
    private static TransactionsDbAdapter mTransactionsDbAdapter;
    private static SplitsDbAdapter mSplitsDbAdapter;
	private TransactionsActivity mTransactionsActivity;

	@Rule public GrantPermissionRule animationPermissionsRule = GrantPermissionRule.grant(Manifest.permission.SET_ANIMATION_SCALE);

	@ClassRule
	public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();

	@Rule
	public ActivityTestRule<TransactionsActivity> mActivityRule =
			new ActivityTestRule<>(TransactionsActivity.class, true, false);

	private Account mBaseAccount;
	private Account mTransferAccount;

	public TransactionsActivityTest() {
		mBaseAccount = new Account(TRANSACTIONS_ACCOUNT_NAME, COMMODITY);
		mBaseAccount.setUID(TRANSACTIONS_ACCOUNT_UID);

		mTransferAccount = new Account(TRANSFER_ACCOUNT_NAME, COMMODITY);
		mTransferAccount.setUID(TRANSFER_ACCOUNT_UID);

		mTransactionTimeMillis = System.currentTimeMillis();
		mTransaction = new Transaction(TRANSACTION_NAME);
		mTransaction.setCommodity(COMMODITY);
		mTransaction.setNote("What up?");
		mTransaction.setTime(mTransactionTimeMillis);
		Split split = new Split(new Money(TRANSACTION_AMOUNT, CURRENCY_CODE), TRANSACTIONS_ACCOUNT_UID);
		split.setType(TransactionType.DEBIT);

		mTransaction.addSplit(split);
		mTransaction.addSplit(split.createPair(TRANSFER_ACCOUNT_UID));

		mBaseAccount.addTransaction(mTransaction);
	}

	@BeforeClass
	public static void prepareTestCase(){
		Context context = GnuCashApplication.getAppContext();
		AccountsActivityTest.preventFirstRunDialogs(context);

		mSplitsDbAdapter = SplitsDbAdapter.getInstance();
		mTransactionsDbAdapter = TransactionsDbAdapter.getInstance();
		mAccountsDbAdapter = AccountsDbAdapter.getInstance();
		COMMODITY = CommoditiesDbAdapter.getInstance().getCommodity(CURRENCY_CODE);

//		PreferenceActivity.getActiveBookSharedPreferences(context)
//				.edit().putBoolean(context.getString(R.string.key_use_compact_list), false)
//				.apply();
	}

	@Before
	public void setUp() throws Exception {
		mAccountsDbAdapter.deleteAllRecords();
        mAccountsDbAdapter.addRecord(mBaseAccount, DatabaseAdapter.UpdateMethod.insert);
        mAccountsDbAdapter.addRecord(mTransferAccount, DatabaseAdapter.UpdateMethod.insert);

        mTransactionsDbAdapter.addRecord(mTransaction, DatabaseAdapter.UpdateMethod.insert);

		assertThat(mAccountsDbAdapter.getRecordsCount()).isEqualTo(3); //including ROOT account
		assertThat(mTransactionsDbAdapter.getRecordsCount()).isEqualTo(1);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.putExtra(UxArgument.SELECTED_ACCOUNT_UID, TRANSACTIONS_ACCOUNT_UID);
		mTransactionsActivity = mActivityRule.launchActivity(intent);

		//refreshTransactionsList();
	}


	private void validateTransactionListDisplayed(){
		onView(withId(R.id.transaction_recycler_view)).check(matches(isDisplayed()));
	}
	
	private int getTransactionCount(){
        return mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID).size();
	}
	
	private void validateTimeInput(long timeMillis){
		String expectedValue = TransactionFormFragment.DATE_FORMATTER.format(new Date(timeMillis));
		onView(withId(R.id.input_date)).check(matches(withText(expectedValue)));
		
		expectedValue = TransactionFormFragment.TIME_FORMATTER.format(new Date(timeMillis));
		onView(withId(R.id.input_time)).check(matches(withText(expectedValue)));
	}

	@Test
	public void testAddTransactionShouldRequireAmount(){
		validateTransactionListDisplayed();
		
		int beforeCount = mTransactionsDbAdapter.getTransactionsCount(TRANSACTIONS_ACCOUNT_UID);
        onView(withId(R.id.fab_create_transaction)).perform(click());

		onView(withId(R.id.input_transaction_name))
				.check(matches(isDisplayed()))
				.perform(typeText("Lunch"));

		Espresso.closeSoftKeyboard();

		onView(withId(R.id.menu_save))
				.check(matches(isDisplayed()))
				.perform(click());
		onView(withText(R.string.title_add_transaction)).check(matches(isDisplayed()));
		sleep(1000);
		assertToastDisplayed(R.string.toast_transanction_amount_required);

		int afterCount = mTransactionsDbAdapter.getTransactionsCount(TRANSACTIONS_ACCOUNT_UID);
		assertThat(afterCount).isEqualTo(beforeCount);

	}

	/**
	 * Sleep the thread for a specified period
	 * @param millis Duration to sleep in milliseconds
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks that a specific toast message is displayed
	 * @param toastString String that should be displayed
	 */
	private void assertToastDisplayed(int toastString) {
		onView(withText(toastString))
				.inRoot(withDecorView(not(mTransactionsActivity.getWindow().getDecorView())))
				.check(matches(isDisplayed()));
	}


	private void validateEditTransactionFields(Transaction transaction){

		onView(withId(R.id.input_transaction_name)).check(matches(withText(transaction.getDescription())));

		Money balance = transaction.getBalance(TRANSACTIONS_ACCOUNT_UID);
		NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		onView(withId(R.id.input_transaction_amount)).check(matches(withText(formatter.format(balance.asDouble()))));
		onView(withId(R.id.input_date)).check(matches(withText(TransactionFormFragment.DATE_FORMATTER.format(transaction.getTimeMillis()))));
		onView(withId(R.id.input_time)).check(matches(withText(TransactionFormFragment.TIME_FORMATTER.format(transaction.getTimeMillis()))));
		onView(withId(R.id.input_description)).check(matches(withText(transaction.getNote())));

		validateTimeInput(transaction.getTimeMillis());
	}

    //TODO: Add test for only one account but with double-entry enabled
	@Test
	public void testAddTransaction(){
        setDoubleEntryEnabled(true);
		setDefaultTransactionType(TransactionType.DEBIT);
        validateTransactionListDisplayed();

		onView(withId(R.id.fab_create_transaction)).perform(click());

		onView(withId(R.id.input_transaction_name)).perform(typeText("Lunch"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.input_transaction_amount)).perform(typeText("899"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.input_transaction_type))
				.check(matches(allOf(isDisplayed(), withText(R.string.label_receive))))
				.perform(click())
				.check(matches(withText(R.string.label_spend)));

		String expectedValue = NumberFormat.getInstance().format(-899);
		onView(withId(R.id.input_transaction_amount)).check(matches(withText(expectedValue)));

        int transactionsCount = getTransactionCount();
		onView(withId(R.id.menu_save)).perform(click());

        validateTransactionListDisplayed();

        List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID);
        assertThat(transactions).hasSize(2);
        Transaction transaction = transactions.get(0);
		assertThat(transaction.getSplits()).hasSize(2);

        assertThat(getTransactionCount()).isEqualTo(transactionsCount + 1);
    }

	/**
	 * Tests that transactions splits are automatically balanced and an imbalance account will be created
	 * This test case assumes that single entry is used
	 */
	//TODO: move this to the unit tests
	public void testAutoBalanceTransactions(){
		setDoubleEntryEnabled(false);
		mTransactionsDbAdapter.deleteAllRecords();

		assertThat(mTransactionsDbAdapter.getRecordsCount()).isEqualTo(0);
		String imbalanceAcctUID = mAccountsDbAdapter.getImbalanceAccountUID(Commodity.getInstance(CURRENCY_CODE));
		assertThat(imbalanceAcctUID).isNull();

		validateTransactionListDisplayed();
		onView(withId(R.id.fab_create_transaction)).perform(click());
		onView(withId(R.id.fragment_transaction_form)).check(matches(isDisplayed()));

		onView(withId(R.id.input_transaction_name)).perform(typeText("Autobalance"));
		onView(withId(R.id.input_transaction_amount)).perform(typeText("499"));

		//no double entry so no split editor
		//TODO: check that the split drawable is not displayed
		onView(withId(R.id.menu_save)).perform(click());

		assertThat(mTransactionsDbAdapter.getRecordsCount()).isEqualTo(1);
		Transaction transaction = mTransactionsDbAdapter.getAllTransactions().get(0);
		assertThat(transaction.getSplits()).hasSize(2);
		imbalanceAcctUID = mAccountsDbAdapter.getImbalanceAccountUID(Commodity.getInstance(CURRENCY_CODE));
		assertThat(imbalanceAcctUID).isNotNull();
		assertThat(imbalanceAcctUID).isNotEmpty();
		assertThat(mAccountsDbAdapter.isHiddenAccount(imbalanceAcctUID)).isTrue(); //imbalance account should be hidden in single entry mode

		assertThat(transaction.getSplits()).extracting("mAccountUID").contains(imbalanceAcctUID);

	}


    private void setDoubleEntryEnabled(boolean enabled){
        SharedPreferences prefs = PreferenceActivity.getActiveBookSharedPreferences();
        Editor editor = prefs.edit();
        editor.putBoolean(mTransactionsActivity.getString(R.string.key_use_double_entry), enabled);
        editor.apply();
    }

	@Test
	public void testDefaultTransactionType(){
		setDefaultTransactionType(TransactionType.CREDIT);

		onView(withId(R.id.fab_create_transaction)).perform(click());
		onView(withId(R.id.input_transaction_type)).check(matches(allOf(isChecked(), withText(R.string.label_spend))));
	}

	private void setDefaultTransactionType(TransactionType type) {
		SharedPreferences prefs = PreferenceActivity.getActiveBookSharedPreferences();
		Editor editor = prefs.edit();
		editor.putString(mTransactionsActivity.getString(R.string.key_default_transaction_type), type.name());
		editor.commit();
	}

	//FIXME: Improve on this test
	public void childAccountsShouldUseParentTransferAccountSetting(){
		Account transferAccount = new Account("New Transfer Acct");
		mAccountsDbAdapter.addRecord(transferAccount, DatabaseAdapter.UpdateMethod.insert);
		mAccountsDbAdapter.addRecord(new Account("Higher account"), DatabaseAdapter.UpdateMethod.insert);

		Account childAccount = new Account("Child Account");
		childAccount.setParentUID(TRANSACTIONS_ACCOUNT_UID);
		mAccountsDbAdapter.addRecord(childAccount, DatabaseAdapter.UpdateMethod.insert);
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseSchema.AccountEntry.COLUMN_DEFAULT_TRANSFER_ACCOUNT_UID, transferAccount.getUID());
		mAccountsDbAdapter.updateRecord(TRANSACTIONS_ACCOUNT_UID, contentValues);

		Intent intent = new Intent(mTransactionsActivity, TransactionsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_INSERT_OR_EDIT);
		intent.putExtra(UxArgument.SELECTED_ACCOUNT_UID, childAccount.getUID());

		mTransactionsActivity.startActivity(intent);

		onView(withId(R.id.input_transaction_amount)).perform(typeText("1299"));
		clickOnView(R.id.menu_save);

		//if our transfer account has a transaction then the right transfer account was used
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(transferAccount.getUID());
		assertThat(transactions).hasSize(1);
	}

	@Test
	public void testToggleTransactionType(){
		validateTransactionListDisplayed();
		onView(withId(R.id.edit_transaction)).perform(click());

		validateEditTransactionFields(mTransaction);

		onView(withId(R.id.input_transaction_type)).check(matches(
				allOf(isDisplayed(), withText(R.string.label_receive))
		)).perform(click()).check(matches(withText(R.string.label_spend)));

		onView(withId(R.id.input_transaction_amount)).check(matches(withText("-9.99")));

		onView(withId(R.id.menu_save)).perform(click());
		
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID);
		assertThat(transactions).hasSize(1);
		Transaction trx = transactions.get(0);
		assertThat(trx.getSplits()).hasSize(2); //auto-balancing of splits
		assertThat(trx.getBalance(TRANSACTIONS_ACCOUNT_UID).isNegative()).isTrue();
	}

	@Test
	public void testOpenTransactionEditShouldNotModifyTransaction(){
		validateTransactionListDisplayed();

		onView(withId(R.id.edit_transaction)).perform(click());
		validateTimeInput(mTransactionTimeMillis);

		clickOnView(R.id.menu_save);

		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID);

		assertThat(transactions).hasSize(1);
		Transaction transaction = transactions.get(0);
		assertThat(TRANSACTION_NAME).isEqualTo(transaction.getDescription());
		Date expectedDate = new Date(mTransactionTimeMillis);
		Date trxDate = new Date(transaction.getTimeMillis());
		assertThat(TransactionFormFragment.DATE_FORMATTER.format(expectedDate))
				.isEqualTo(TransactionFormFragment.DATE_FORMATTER.format(trxDate));
		assertThat(TransactionFormFragment.TIME_FORMATTER.format(expectedDate))
				.isEqualTo(TransactionFormFragment.TIME_FORMATTER.format(trxDate));

		Split baseSplit = transaction.getSplits(TRANSACTIONS_ACCOUNT_UID).get(0);
		Money expectedAmount = new Money(TRANSACTION_AMOUNT, CURRENCY_CODE);
		assertThat(baseSplit.getValue()).isEqualTo(expectedAmount);
		assertThat(baseSplit.getQuantity()).isEqualTo(expectedAmount);
		assertThat(baseSplit.getType()).isEqualTo(TransactionType.DEBIT);

		Split transferSplit = transaction.getSplits(TRANSFER_ACCOUNT_UID).get(0);
		assertThat(transferSplit.getValue()).isEqualTo(expectedAmount);
		assertThat(transferSplit.getQuantity()).isEqualTo(expectedAmount);
		assertThat(transferSplit.getType()).isEqualTo(TransactionType.CREDIT);

	}

	@Test
	public void testDeleteTransaction(){
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText(R.string.menu_delete)).perform(click());

		assertThat(0).isEqualTo(mTransactionsDbAdapter.getTransactionsCount(TRANSACTIONS_ACCOUNT_UID));
	}

	@Test
	public void testMoveTransaction(){
		Account account = new Account("Move account");
		account.setCommodity(Commodity.getInstance(CURRENCY_CODE));
		mAccountsDbAdapter.addRecord(account, DatabaseAdapter.UpdateMethod.insert);

		assertThat(mTransactionsDbAdapter.getAllTransactionsForAccount(account.getUID())).hasSize(0);

		onView(withId(R.id.options_menu)).perform(click());
		onView(withText(R.string.menu_move_transaction)).perform(click());

		onView(withId(R.id.btn_save)).perform(click());

		assertThat(mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID)).hasSize(0);

		assertThat(mTransactionsDbAdapter.getAllTransactionsForAccount(account.getUID())).hasSize(1);

	}

	@Test
	public void testDuplicateTransaction(){
		assertThat(mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID)).hasSize(1);

		onView(withId(R.id.options_menu)).perform(click());
		onView(withText(R.string.menu_duplicate_transaction)).perform(click());

		List<Transaction> dummyAccountTrns = mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID);
		assertThat(dummyAccountTrns).hasSize(2);

		assertThat(dummyAccountTrns.get(0).getDescription()).isEqualTo(dummyAccountTrns.get(1).getDescription());
		assertThat(dummyAccountTrns.get(0).getTimeMillis()).isNotEqualTo(dummyAccountTrns.get(1).getTimeMillis());
	}

	//TODO: add normal transaction recording
	@Test
	public void testLegacyIntentTransactionRecording(){
		int beforeCount = mTransactionsDbAdapter.getTransactionsCount(TRANSACTIONS_ACCOUNT_UID);
		Intent transactionIntent = new Intent(Intent.ACTION_INSERT);
		transactionIntent.setType(Transaction.MIME_TYPE);
		transactionIntent.putExtra(Intent.EXTRA_TITLE, "Power intents");
		transactionIntent.putExtra(Intent.EXTRA_TEXT, "Intents for sale");
		transactionIntent.putExtra(Transaction.EXTRA_AMOUNT, new BigDecimal(4.99));
		transactionIntent.putExtra(Transaction.EXTRA_ACCOUNT_UID, TRANSACTIONS_ACCOUNT_UID);
		transactionIntent.putExtra(Transaction.EXTRA_TRANSACTION_TYPE, TransactionType.DEBIT.name());
		transactionIntent.putExtra(Account.EXTRA_CURRENCY_CODE, "USD");

		new TransactionRecorder().onReceive(mTransactionsActivity, transactionIntent);

		int afterCount = mTransactionsDbAdapter.getTransactionsCount(TRANSACTIONS_ACCOUNT_UID);
		
		assertThat(beforeCount + 1).isEqualTo(afterCount);
		
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(TRANSACTIONS_ACCOUNT_UID);
		
		for (Transaction transaction : transactions) {
			if (transaction.getDescription().equals("Power intents")){
				assertThat("Intents for sale").isEqualTo(transaction.getNote());
				assertThat(4.99).isEqualTo(transaction.getBalance(TRANSACTIONS_ACCOUNT_UID).asDouble());
			}
		}
	}

	/**
	 * In this test we check that editing a transaction and switching the transfer account to one
	 * which is of a different currency and then back again should not have side-effects.
	 * The split value and quantity should remain consistent.
	 */
	@Test
	public void editingTransferAccount_shouldKeepSplitAmountsConsistent() {
		mTransactionsDbAdapter.deleteAllRecords(); //clean slate
		Commodity euroCommodity = CommoditiesDbAdapter.getInstance().getCommodity("EUR");
		Account euroAccount = new Account("Euro Account", euroCommodity);

		mAccountsDbAdapter.addRecord(euroAccount);

		Money expectedValue = new Money(BigDecimal.TEN, COMMODITY);
		Money expectedQty = new Money("5", "EUR");

		String trnDescription = "Multicurrency Test Trn";
		Transaction multiTransaction = new Transaction(trnDescription);
		Split split1 = new Split(expectedValue, TRANSACTIONS_ACCOUNT_UID);
		split1.setType(TransactionType.CREDIT);
		Split split2 = new Split(expectedValue, expectedQty, euroAccount.getUID());
		split2.setType(TransactionType.DEBIT);
		multiTransaction.addSplit(split1);
		multiTransaction.addSplit(split2);
		multiTransaction.setCommodity(COMMODITY);

		mTransactionsDbAdapter.addRecord(multiTransaction);

		Transaction savedTransaction = mTransactionsDbAdapter.getRecord(multiTransaction.getUID());
		assertThat(savedTransaction.getSplits()).extracting("mQuantity").contains(expectedQty);
		assertThat(savedTransaction.getSplits()).extracting("mValue").contains(expectedValue);

		assertThat(savedTransaction.getSplits(TRANSACTIONS_ACCOUNT_UID).get(0)
				.isEquivalentTo(multiTransaction.getSplits(TRANSACTIONS_ACCOUNT_UID).get(0)))
				.isTrue();

		refreshTransactionsList();

		//open transaction for editing
		onView(withText(trnDescription)).check(matches(isDisplayed())); //transaction was added
		onView(allOf(withParent(hasDescendant(withText(trnDescription))),
				withId(R.id.edit_transaction))).perform(click());

		onView(withId(R.id.input_transfer_account_spinner)).perform(click());
		onView(withText(TRANSFER_ACCOUNT_NAME)).perform(click());

		onView(withId(R.id.input_transfer_account_spinner)).perform(click());
		onView(withText(euroAccount.getFullName())).perform(click());
		onView(withId(R.id.input_converted_amount)).perform(typeText("5"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.btn_save)).perform(click());

		onView(withId(R.id.input_transfer_account_spinner)).perform(click());
		onView(withText(TRANSFER_ACCOUNT_NAME)).perform(click());

		onView(withId(R.id.menu_save)).perform(click());

		Transaction editedTransaction = mTransactionsDbAdapter.getRecord(multiTransaction.getUID());
		assertThat(editedTransaction.getSplits(TRANSACTIONS_ACCOUNT_UID).get(0)
				.isEquivalentTo(savedTransaction.getSplits(TRANSACTIONS_ACCOUNT_UID).get(0)))
				.isTrue();

		Money firstAcctBalance = mAccountsDbAdapter.getAccountBalance(TRANSACTIONS_ACCOUNT_UID);
		assertThat(firstAcctBalance).isEqualTo(editedTransaction.getBalance(TRANSACTIONS_ACCOUNT_UID));

		Money transferBalance = mAccountsDbAdapter.getAccountBalance(TRANSFER_ACCOUNT_UID);
		assertThat(transferBalance).isEqualTo(editedTransaction.getBalance(TRANSFER_ACCOUNT_UID));

		assertThat(editedTransaction.getBalance(TRANSFER_ACCOUNT_UID)).isEqualTo(expectedValue);

		Split transferAcctSplit = editedTransaction.getSplits(TRANSFER_ACCOUNT_UID).get(0);
		assertThat(transferAcctSplit.getQuantity()).isEqualTo(expectedValue);
		assertThat(transferAcctSplit.getValue()).isEqualTo(expectedValue);

	}
//
//	@Test
//	public void testAddTransaction1(){
//		// Setup
//		String transaction = "Transaction Test";
//
//		// Exercise
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.input_transaction_amount)).perform(typeText("100.00"));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.input_transaction_type)).perform(click());
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Verify
//		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
//		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(transactions).hasSize(1);
//		Transaction firstTransaction = transactions.get(0);
//		assertThat(firstTransaction.getDescription()).isEqualTo(transaction);
//	}
//
//	@Test
//	public void testAddTransaction2(){
//		// Setup
//		String transaction = "New Transaction";
//
//		// Exercise
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText("12.50"));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Verify
//		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
//		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(transactions).hasSize(1);
//		Transaction firstTransaction = transactions.get(0);
//		assertThat(firstTransaction.getDescription()).isEqualTo(transaction);
//		onView(withId(R.id.transaction_amount)).check(matches(withText("$12.50")));
//	}
//
//	@Test
//	public void testModifyTransaction1(){
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction = "New Transaction";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText("12.50"));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Exercise
//		onView(withText(transaction)).perform(click());
//		onView(withId(R.id.fab_edit_transaction)).perform(click());
//		String description = "Notes for New Transaction";
//		onView(withId(R.id.input_description)).perform(typeText(description));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Verify
//		onView(withId(R.id.trn_notes)).check(matches(withText(description)));
//	}
//
//	@Test
//	public void testModifyTransaction2() {
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String tempName = "Temporary Transaction Name";
//		String tempAmount = "12.50";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(tempName));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText(tempAmount));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//		onView(withId(R.id.primary_text)).check(matches(withText(tempName)));
//
//		// Exercise
//		onView(withId(R.id.primary_text)).perform(click());
//		onView(withId(R.id.fab_edit_transaction)).perform(click());
//		String newName = "New Transaction Name";
//		String newAmount = "25.00";
//		onView(withId(R.id.input_transaction_name)).perform(clearText());
//		onView(withId(R.id.input_transaction_name)).perform(typeText(newName));
//		onView(withId(R.id.input_transaction_amount)).perform(clearText());
//		onView(withId(R.id.input_transaction_amount)).perform(typeText(newAmount));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Verify
//		onView(withId(R.id.trn_description)).check(matches(withText(newName)));
//		onView(withId(R.id.balance_credit)).check(matches(withText("$25.00")));
//	}
//
//	@Test
//	public void testDeleteTransaction1(){
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction1 = "Transaction 1";
//		String amount1 = "10.00";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction1));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount1));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(transactions).hasSize(1);
//
//		// Exercise
//		onView(withId(R.id.options_menu)).perform(click());
//		onView(withText("Delete")).perform(click());
//
//		// Verify
//		List<Transaction> newTransactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(newTransactions).hasSize(0);
//
//	}
//
//	@Test
//	public void testDeleteTransaction2(){
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction1 = "Transaction 1";
//		String amount1 = "10.00";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction1));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount1));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(transactions).hasSize(1);
//
//		// Exercise
//		onView(withId(R.id.options_menu)).perform(click());
//		onView(withText("Delete")).perform(click());
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction2 = "Transaction 2";
//		String amount2 = "20.00";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction2));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount2));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//		onView(withId(R.id.options_menu)).perform(click());
//		onView(withText("Delete")).perform(click());
//
//		// Verify
//		List<Transaction> finalTransactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(finalTransactions).hasSize(0);
//	}
//
//	@Test
//	public void testTransferTransaction1(){
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction = "Transaction to Transfer";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText("20.00"));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Exercise
//		onView(withId(R.id.options_menu)).perform(click());
//		onView(withText("Move…")).perform(click());
//		onView(withText("Move")).perform(click());
//
//		// Verify
//		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
//		assertThat(transactions).hasSize(0);
//	}
//
//	@Test
//	public void testTransferTransaction2(){
//		// Setup
//		onView(withId(R.id.fab_create_transaction)).perform(click());
//		String transaction = "Transaction to Transfer";
//		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
//		onView(withId(R.id.input_transaction_amount)).perform(typeText("20.00"));
//		Espresso.closeSoftKeyboard();
//		onView(withId(R.id.menu_save)).perform(click());
//
//		// Exercise
//		onView(withId(R.id.options_menu)).perform(click());
//		onView(withText("Move…")).perform(click());
//		onView(withText("Move")).perform(click());
//		onView(withId(R.id.toolbar_spinner)).perform(click());
//		onView(withText("CSCI5802Spring2022 Second Account")).perform(click());
//
//		// Verify
//		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
//	}
//
//	@BeforeClass
//	public static void initialize(){
//		TestUtils.preventFirstRunDialogs(GnuCashApplication.getAppContext());
//		TestUtils.setDoubleEntryEnabled(GnuCashApplication.getAppContext(), false);
//		String activeBookUID = BooksDbAdapter.getInstance().getActiveBookUID();
//		mDbHelper = new DatabaseHelper(GnuCashApplication.getAppContext(), activeBookUID);
//		mDb = mDbHelper.getWritableDatabase();
//		mSplitsDbAdapter        = SplitsDbAdapter.getInstance();
//		mTransactionsDbAdapter  = TransactionsDbAdapter.getInstance();
//		mAccountsDbAdapter      = AccountsDbAdapter.getInstance();
//		//initialize commodity constants
//		CommoditiesDbAdapter commoditiesDbAdapter = new CommoditiesDbAdapter(mDb);
//	}
	/**
	 * Simple wrapper for clicking on views with espresso
	 * @param viewId View resource ID
	 */
	private void clickOnView(int viewId){
		onView(withId(viewId)).perform(click());
	}

	/**
	 * Refresh the account list fragment
	 */
	private void refreshTransactionsList(){
		try {
			mActivityRule.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mTransactionsActivity.refresh();
				}
			});
		} catch (Throwable throwable) {
			System.err.println("Failed to refresh fragment");
		}
	}


	@After
	public void tearDown() throws Exception {
		if (mTransactionsActivity != null)
			mTransactionsActivity.finish();
	}

//	@Test
	@Ignore
	public void testAddTransaction1(){
		// Setup
		String transaction = "Transaction Test";

		// Exercise
		onView(withId(R.id.fab_create_transaction)).perform(click());
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.input_transaction_amount)).perform(typeText("100.00"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.input_transaction_type)).perform(click());
		onView(withId(R.id.menu_save)).perform(click());

		// Verify
		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(transactions).hasSize(1);
		Transaction firstTransaction = transactions.get(0);
		assertThat(firstTransaction.getDescription()).isEqualTo(transaction);
	}

//	@Test
	@Ignore
	public void testAddTransaction2(){
		// Setup
		String transaction = "New Transaction";

		// Exercise
		onView(withId(R.id.fab_create_transaction)).perform(click());
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
		onView(withId(R.id.input_transaction_amount)).perform(typeText("12.50"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Verify
		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(transactions).hasSize(1);
		Transaction firstTransaction = transactions.get(0);
		assertThat(firstTransaction.getDescription()).isEqualTo(transaction);
		onView(withId(R.id.transaction_amount)).check(matches(withText("$12.50")));
	}

	@Test
	public void testModifyTransaction1(){
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction = "New Transaction";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
		onView(withId(R.id.input_transaction_amount)).perform(typeText("12.50"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Exercise
		onView(withText(transaction)).perform(click());
		onView(withId(R.id.fab_edit_transaction)).perform(click());
		String description = "Notes for New Transaction";
		onView(withId(R.id.input_description)).perform(typeText(description));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Verify
		onView(withId(R.id.trn_notes)).check(matches(withText(description)));
	}

//	@Test
	@Ignore
	public void testModifyTransaction2() {
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String tempName = "Temporary Transaction Name";
		String tempAmount = "12.50";
		onView(withId(R.id.input_transaction_name)).perform(typeText(tempName));
		onView(withId(R.id.input_transaction_amount)).perform(typeText(tempAmount));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());
		onView(withId(R.id.primary_text)).check(matches(withText(tempName)));

		// Exercise
		onView(withId(R.id.primary_text)).perform(click());
		onView(withId(R.id.fab_edit_transaction)).perform(click());
		String newName = "New Transaction Name";
		String newAmount = "25.00";
		onView(withId(R.id.input_transaction_name)).perform(clearText());
		onView(withId(R.id.input_transaction_name)).perform(typeText(newName));
		onView(withId(R.id.input_transaction_amount)).perform(clearText());
		onView(withId(R.id.input_transaction_amount)).perform(typeText(newAmount));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Verify
		onView(withId(R.id.trn_description)).check(matches(withText(newName)));
		onView(withId(R.id.balance_credit)).check(matches(withText("$25.00")));
	}

//	@Test
	@Ignore
	public void testDeleteTransaction1(){
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction1 = "Transaction 1";
		String amount1 = "10.00";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction1));
		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount1));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(transactions).hasSize(1);

		// Exercise
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText("Delete")).perform(click());

		// Verify
		List<Transaction> newTransactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(newTransactions).hasSize(0);

	}

//	@Test
	@Ignore
	public void testDeleteTransaction2(){
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction1 = "Transaction 1";
		String amount1 = "10.00";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction1));
		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount1));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(transactions).hasSize(1);

		// Exercise
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText("Delete")).perform(click());
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction2 = "Transaction 2";
		String amount2 = "20.00";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction2));
		onView(withId(R.id.input_transaction_amount)).perform(typeText(amount2));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText("Delete")).perform(click());

		// Verify
		List<Transaction> finalTransactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(finalTransactions).hasSize(0);
	}

//	@Test
	@Ignore
	public void testTransferTransaction1(){
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction = "Transaction to Transfer";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
		onView(withId(R.id.input_transaction_amount)).perform(typeText("20.00"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Exercise
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText("Move…")).perform(click());
		onView(withText("Move")).perform(click());

		// Verify
		List<Transaction> transactions = mTransactionsDbAdapter.getAllTransactionsForAccount(FIRST_ACCOUNT_UID);
		assertThat(transactions).hasSize(0);
	}

//	@Test
	@Ignore
	public void testTransferTransaction2(){
		// Setup
		onView(withId(R.id.fab_create_transaction)).perform(click());
		String transaction = "Transaction to Transfer";
		onView(withId(R.id.input_transaction_name)).perform(typeText(transaction));
		onView(withId(R.id.input_transaction_amount)).perform(typeText("20.00"));
		Espresso.closeSoftKeyboard();
		onView(withId(R.id.menu_save)).perform(click());

		// Exercise
		onView(withId(R.id.options_menu)).perform(click());
		onView(withText("Move…")).perform(click());
		onView(withText("Move")).perform(click());
		onView(withId(R.id.toolbar_spinner)).perform(click());
		onView(withText("CSCI5802Spring2022 Second Account")).perform(click());

		// Verify
		onView(withId(R.id.primary_text)).check(matches(withText(transaction)));
	}

}
