// ============================================
// src/pages/OrderSuccessPage.jsx (CONDENSED)
// ============================================
import React from 'react';
import { FaCheckCircle, FaTruck, FaHome } from 'react-icons/fa';

const OrderSuccessPage = ({ orderDetails, onNavigate }) => {
  if (!orderDetails) {
    onNavigate('home');
    return null;
  }

  const { items, deliveryDate, deliveryTime, total, orderNumber, address } = orderDetails;

  // Format delivery date
  const formatDeliveryDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { 
      weekday: 'long', 
      year: 'numeric', 
      month: 'long', 
      day: 'numeric' 
    });
  };

  // Get time slot text - EXPLAINED
  const getTimeSlotText = (timeSlot) => {
    // This is an object that maps time slot keys to readable time ranges
    const slots = {
      morning: '8:00 AM - 12:00 PM',     // If timeSlot is 'morning', return this
      afternoon: '12:00 PM - 5:00 PM',   // If timeSlot is 'afternoon', return this
      evening: '5:00 PM - 8:00 PM'       // If timeSlot is 'evening', return this
    };
    
    // Return the matching time range, or return timeSlot itself if not found
    return slots[timeSlot] || timeSlot;
    
    // Example: getTimeSlotText('morning') returns '8:00 AM - 12:00 PM'
    // Example: getTimeSlotText('afternoon') returns '12:00 PM - 5:00 PM'
  };

  return (
    <div className="success-page">
      <div className="success-content">
        {/* Success Header - Compact */}
        <div className="success-header">
          <FaCheckCircle className="success-icon" />
          <h1 className="success-title">Order Placed!</h1>
          <p className="success-order-num">Order #{orderNumber}</p>
        </div>

        {/* Delivery Info - Compact */}
        <div className="success-delivery">
          <div className="delivery-icon-wrapper">
            <FaTruck />
          </div>
          <div className="delivery-details">
            <p className="delivery-date">Arriving by {formatDeliveryDate(deliveryDate)}</p>
            <p className="delivery-time">{getTimeSlotText(deliveryTime)}</p>
          </div>
        </div>

        {/* Items Summary - Very Compact */}
        <div className="success-items">
          <h3 className="items-heading">{items.length} Item{items.length > 1 ? 's' : ''} Purchased</h3>
          <div className="items-grid">
            {items.map((item) => (
              <div key={item.id} className="success-item">
                <div className="success-item-img">
                  {item.imgUrl ? (
                    <img src={item.imgUrl} alt={item.title || item.name} />
                  ) : (
                    <span className="success-item-emoji">{item.image}</span>
                  )}
                </div>
                <div className="success-item-info">
                  <p className="success-item-name">{item.title || item.name}</p>
                  <p className="success-item-details">Qty: {item.quantity} × ${item.price}</p>
                </div>
                <p className="success-item-total">${(item.price * item.quantity).toFixed(2)}</p>
              </div>
            ))}
          </div>
        </div>

        {/* Total - Compact */}
        <div className="success-total">
          <span>Total Paid with Orbit Pay</span>
          <span className="total-amount">${total.toFixed(2)}</span>
        </div>

        {/* Actions - Compact */}
        <div className="success-actions">
          <button className="btn-primary" onClick={() => onNavigate('home')}>
            <FaHome />
            <span>Continue Shopping</span>
          </button>
          <button className="btn-secondary" onClick={() => onNavigate('account')}>
            View Orders
          </button>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccessPage;