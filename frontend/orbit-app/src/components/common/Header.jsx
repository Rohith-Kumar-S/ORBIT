import React from 'react';
import { FaShoppingCart, FaSearch, FaUser } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';
import { useAuth } from '../../context/AuthContext';

const Header = ({ onNavigate }) => {
  const { getTotalItems } = useCart();
  const { isAuthenticated } = useAuth();

  const handleUserClick = () => {
    if (isAuthenticated) {
      onNavigate('account');
    } else {
      onNavigate('login');
    }
  };

  return (
    <header className="header">
      <div className="header-content">
        <div className="header-left">
          <h1 className="logo" onClick={() => onNavigate('home')}>
            Orbit
          </h1>
          <div className="search-bar">
            <FaSearch size={20} style={{ color: '#9ca3af' }} />
            <input type="text" placeholder="Search products..." />
          </div>
        </div>
        <div className="header-right">
          <button className="icon-button" onClick={handleUserClick}>
            <FaUser size={22} style={{ color: '#374151' }} />
          </button>
          <button className="cart-button" onClick={() => onNavigate('cart')}>
            <FaShoppingCart size={24} style={{ color: '#374151' }} />
            {getTotalItems() > 0 && (
              <span className="cart-badge">{getTotalItems()}</span>
            )}
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
