import React, { useState } from 'react';
import { FaArrowLeft, FaSignOutAlt } from 'react-icons/fa';
import { useAuth } from '../context/AuthContext';
import OrdersTab from '../components/account/OrdersTab';
import SecurityTab from '../components/account/SecurityTab';
import OrbitPayTab from '../components/account/OrbitPayTab';
import SupportTab from '../components/account/SupportTab';

const AccountPage = ({ onNavigate, onSelectProduct }) => {
  const [activeTab, setActiveTab] = useState('orders');
  const [supportOrderContext, setSupportOrderContext] = useState(null);
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
    onNavigate('home');
  };

  const handleSwitchToSupport = (orderNumber) => {
    console.log('Switching to support for:', orderNumber);
    setSupportOrderContext(orderNumber);
    setActiveTab('support');
  };

  const handleTabChange = (tab) => {
    if (tab !== 'support') {
      setSupportOrderContext(null);
    }
    setActiveTab(tab);
  };

  return (
    <div className="page-container">
      <button className="back-button" onClick={() => onNavigate('home')}>
        <FaArrowLeft size={20} />
        Back to Home
      </button>
      
      <div className="account-page">
        <div className="account-header">
          <div>
            <h2 className="account-title">My Account</h2>
            <p className="account-subtitle">Welcome back, {user?.name}!</p>
          </div>
          <button className="logout-button" onClick={handleLogout}>
            <FaSignOutAlt size={18} />
            Logout
          </button>
        </div>

        <div className="account-content">
          <div className="account-tabs">
            <button
              className={`account-tab ${activeTab === 'orders' ? 'active' : ''}`}
              onClick={() => handleTabChange('orders')}
            >
              Orders
            </button>
            <button
              className={`account-tab ${activeTab === 'security' ? 'active' : ''}`}
              onClick={() => handleTabChange('security')}
            >
              Login & Security
            </button>
            <button
              className={`account-tab ${activeTab === 'orbitpay' ? 'active' : ''}`}
              onClick={() => handleTabChange('orbitpay')}
            >
              Orbit Pay
            </button>
            <button
              className={`account-tab ${activeTab === 'support' ? 'active' : ''}`}
              onClick={() => handleTabChange('support')}
            >
              Customer Support
            </button>
          </div>

          <div className="account-tab-content">
            {activeTab === 'orders' && (
              <OrdersTab 
                onNavigate={onNavigate} 
                onSelectProduct={onSelectProduct}
                onSwitchToSupport={handleSwitchToSupport}
              />
            )}
            {activeTab === 'security' && <SecurityTab />}
            {activeTab === 'orbitpay' && <OrbitPayTab />}
            {activeTab === 'support' && (
              <SupportTab orderContext={supportOrderContext} />
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountPage;