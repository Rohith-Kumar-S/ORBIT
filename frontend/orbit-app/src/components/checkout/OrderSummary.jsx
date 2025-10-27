import React from 'react';
import { useCart } from '../../context/CartContext';

const OrderSummary = ({ onPlaceOrder }) => {
  const { getTotalPrice } = useCart();

  return (
    <div className="order-summary-section">
      <div className="order-total">
        <span className="order-total-label">Total Amount</span>
        <span className="order-total-value">${getTotalPrice()}</span>
      </div>
      <button className="place-order-btn" onClick={onPlaceOrder}>
        Place Order
      </button>
    </div>
  );
};

export default OrderSummary;
