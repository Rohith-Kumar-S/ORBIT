import React from 'react';
import { useCart } from '../../context/CartContext';

const CartSummary = ({ onCheckout , items, onNavigate }) => {
  const { getTotalPrice, proceedToCheckout } = useCart();

  const handleCheckout = async () => {
    const response = await proceedToCheckout(items);
    if (response.data.success) {
      onCheckout();
      // Handle successful checkout
    } else {
      // Handle checkout error
       onNavigate('login')
    }
  };

  return (
    <div className="cart-summary">
      <h3>Order Summary</h3>
      <div className="summary-row">
        <span className="summary-label">Subtotal</span>
        <span className="summary-value">${getTotalPrice()}</span>
      </div>
      <div className="summary-row">
        <span className="summary-label">Shipping</span>
        <span className="summary-value" style={{ color: '#10b981' }}>Free</span>
      </div>
      <div className="summary-row summary-total">
        <span className="summary-label">Total</span>
        <span className="summary-value">${getTotalPrice()}</span>
      </div>
      <button className="checkout-btn" onClick={handleCheckout}>
        Proceed to Checkout
      </button>
    </div>
  );
};

export default CartSummary;
