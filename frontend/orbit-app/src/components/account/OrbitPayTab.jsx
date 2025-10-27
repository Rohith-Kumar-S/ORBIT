import React, { useState, useEffect, useCallback } from 'react';
import { orbitPayAPI } from '../../services/api';
import { useAuth } from '../../context/AuthContext';

const OrbitPayTab = () => {
  const [balance, setBalance] = useState(0);
  const [amount, setAmount] = useState('');
  const [loading, setLoading] = useState(false);
  const [transactions, setTransactions] = useState([]);
  const [success, setSuccess] = useState('');
  const { user, updateUser } = useAuth();

  const loadBalance = useCallback(async () => {
  try {
    if (user?.accountBalance !== undefined) {
      setBalance(user.accountBalance);
    } else {
      const response = await orbitPayAPI.getBalance();
      setBalance(response.data.balance);
    }
  } catch (error) {
    console.error('Error loading balance:', error);
  }
}, [user]);

const loadTransactions = useCallback(async () => {
    try {
      const response = await orbitPayAPI.getTransactions();
      setTransactions(response.data);
    } catch (error) {
      console.error('Error loading transactions:', error);
    }
  }, []);

  useEffect(() => {
    loadBalance();
    loadTransactions();
  }, [loadBalance, loadTransactions]);
  
  const handleAddFunds = async (e) => {
    e.preventDefault();
    if (!amount || parseFloat(amount) <= 0) {
      return;
    }

    setLoading(true);
    setSuccess('');
    
    try {
      const response = await orbitPayAPI.addFunds(amount, 'card');
      setBalance(response.data.newBalance);
      updateUser({ ...user, orbitPayBalance: response.data.newBalance });
      setSuccess(`Successfully added $${amount} to your Orbit Pay balance!`);
      setAmount('');
      loadTransactions();
    } catch (error) {
      console.error('Error adding funds:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="orbitpay-tab">
      <h3 className="tab-title">Orbit Pay Balance</h3>
      
      <div className="balance-card">
        <p className="balance-label">Current Balance</p>
        <h2 className="balance-amount">${balance.toFixed(2)}</h2>
      </div>

      {success && <div className="success-message">{success}</div>}

      <div className="add-funds-section">
        <h4 className="section-subtitle">Add Funds</h4>
        <form onSubmit={handleAddFunds} className="add-funds-form">
          <div className="form-group">
            <label className="form-label">Amount</label>
            <input
              type="number"
              className="form-input"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              placeholder="0.00"
              min="1"
              step="0.01"
              required
            />
          </div>
          <button type="submit" className="submit-button" disabled={loading}>
            {loading ? 'Processing...' : 'Add Funds'}
          </button>
        </form>
      </div>

      <div className="transactions-section">
        <h4 className="section-subtitle">Recent Transactions</h4>
        {transactions.length === 0 ? (
          <p className="empty-state">No transactions yet</p>
        ) : (
          <div className="transactions-list">
            {transactions.map(transaction => (
              <div key={transaction.id} className="transaction-item">
                <div>
                  <p className="transaction-description">{transaction.description}</p>
                  <p className="transaction-date">{transaction.date}</p>
                </div>
                <span className={`transaction-amount ${transaction.type.toLowerCase()}`}>
                  {transaction.type === 'Credit' ? '+' : '-'}${transaction.amount}
                </span>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default OrbitPayTab;
