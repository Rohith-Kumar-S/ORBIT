import React, { useCallback, useEffect, useState } from 'react';
import { FaArrowLeft, FaWallet } from 'react-icons/fa';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import ShippingForm from '../components/checkout/ShippingForm';

const CheckoutPage = ({ onNavigate, onOrderSuccess }) => {
  const { cart, clearCart, getTotalPrice, getPricing, proceedToBuy } = useCart();
  const { user, updateUser } = useAuth();
  const [subtotal, setSubtotal] = useState(parseFloat(getTotalPrice()));
  const [shipping, setShipping] = useState(subtotal > 50 ? 0 : 5.99);
  const [tax, setTax] = useState(subtotal * 0.08);
  const [orderTotal, setOrderTotal] = useState(subtotal + shipping + tax);

  const fetchPricingDetails = useCallback(async () => {
      try {
        const response = await getPricing();
        if (response.success) {
          setSubtotal(response.data.itemsTotalCost);
          setShipping(response.data.shippingCost);
          setTax(response.data.tax);
          setOrderTotal(response.data.total);
        } else {
          console.log('No popular products found');
        }
      } catch (error) {
        console.error('Error loading popular products:', error);
      }
    }, [getPricing]);


  useEffect(() => {
      // Check if user is logged in on mount
      fetchPricingDetails();
    }, [fetchPricingDetails]);


  // Get default date (today + 3 days)
  const getDefaultDeliveryDate = () => {
    const date = new Date();
    date.setDate(date.getDate() + 3);
    return date.toISOString().split('T')[0];
  };
  const [formData, setFormData] = useState({
    name: user?.name || '',
    address: '',
    city: '',
    zip: '',
    date: getDefaultDeliveryDate(),
    time: ''
  });

  const handlePlaceOrder = async(e) => {
    e.preventDefault();
    
    // Validate form
    if (!formData.name || !formData.address || !formData.city || !formData.zip || !formData.date || !formData.time) {
      alert('Please fill in all fields');
      return;
    }

    // Check if user is logged in
    if (!user) {
      alert('Please log in to place an order');
      onNavigate('login');
      return;
    }

    // Check Orbit Pay balance
    const currentBalance = user?.orbitPayBalance || 0;
    if (currentBalance < orderTotal) {
      alert(`Insufficient Orbit Pay balance. Your balance: $${currentBalance.toFixed(2)}, Order total: $${orderTotal.toFixed(2)}`);
      return;
    }
    
    // Deduct from Orbit Pay balance
    const newBalance = currentBalance - orderTotal;
    updateUser({ ...user, orbitPayBalance: newBalance });

    // Generate order number
    const orderNumber = 'ORD-' + Date.now();

    // Prepare order details
    const orderDetails = {
      orderNumber: orderNumber,
      items: [...cart],
      deliveryDate: formData.date,
      deliveryTime: formData.time,
      address: `${formData.address}, ${formData.city}, ${formData.zip}`,
      total: orderTotal
    };
     try {
        const response = await proceedToBuy();
        if (response.success) {
          clearCart();
          orderDetails.orderNumber = response.data;
          onOrderSuccess(orderDetails);
        } else {
          console.error('Error during purchase:', response.message || 'Purchase failed');
        }
      } catch (error) {
        console.error('Error during purchase:', error);
      }

    // Navigate to success page with order details
   
  };

  return (
    <div className="checkout-container">
      <button className="back-button" onClick={() => onNavigate('cart')}>
        <FaArrowLeft size={20} />
        Back to Cart
      </button>
      
      <h2 className="cart-header">Checkout</h2>
      
      <div className="checkout-layout">
        {/* Left Side - Forms */}
        <div className="checkout-forms">
          <div className="checkout-card">
            {/* Shipping Information */}
            <ShippingForm formData={formData} onChange={setFormData} />

            {/* Delivery Schedule */}
            <div className="checkout-section">
              <h3>Delivery Schedule</h3>
              <div className="form-row">
                <div className="form-group">
                  <label className="form-label">Delivery Date</label>
                  <input
                    type="date"
                    className="form-input"
                    value={formData.date}
                    onChange={(e) => setFormData({ ...formData, date: e.target.value })}
                    min={getDefaultDeliveryDate()}
                    required
                  />
                  <p className="form-hint">Earliest delivery: {new Date(getDefaultDeliveryDate()).toLocaleDateString()}</p>
                </div>
                <div className="form-group">
                  <label className="form-label">Delivery Time</label>
                  <select
                    className="form-select"
                    value={formData.time}
                    onChange={(e) => setFormData({ ...formData, time: e.target.value })}
                    required
                  >
                    <option value="">Select time slot</option>
                    <option value="morning">Morning (8AM - 12PM)</option>
                    <option value="afternoon">Afternoon (12PM - 5PM)</option>
                    <option value="evening">Evening (5PM - 8PM)</option>
                  </select>
                </div>
              </div>
            </div>

            {/* Payment Method - Orbit Pay Only */}
            <div className="checkout-section">
              <h3>Payment Method</h3>
              <div className="payment-option-single">
                <FaWallet size={28} />
                <div className="payment-option-details">
                  <span className="payment-option-name">Orbit Pay</span>
                  {user?.orbitPayBalance !== undefined ? (
                    <span className="payment-option-balance">
                      Available Balance: ${user.orbitPayBalance.toFixed(2)}
                    </span>
                  ) : (
                    <span className="payment-option-warning">
                      Please log in to use Orbit Pay
                    </span>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Right Side - Order Summary */}
        <div className="checkout-summary-container">
          <div className="checkout-summary-card">
            <h3 className="checkout-summary-title">Order Summary</h3>
            
            {/* Price Breakdown Only */}
            <div className="checkout-summary-breakdown">
              <div className="summary-row">
                <span>Items ({cart.reduce((sum, item) => sum + item.quantity, 0)}):</span>
                <span>${subtotal.toFixed(2)}</span>
              </div>
              <div className="summary-row">
                <span>Shipping & handling:</span>
                <span className={shipping === 0 ? 'free-shipping' : ''}>
                  {shipping === 0 ? 'FREE' : `$${shipping.toFixed(2)}`}
                </span>
              </div>
              <div className="summary-row">
                <span>Estimated tax:</span>
                <span>${tax.toFixed(2)}</span>
              </div>
              <div className="summary-divider"></div>
              <div className="summary-row summary-total">
                <span>Order Total:</span>
                <span>${orderTotal.toFixed(2)}</span>
              </div>
            </div>

            {/* Place Order Button */}
            <button 
              className="place-order-btn"
              onClick={handlePlaceOrder}
              type="button"
            >
              Place Order
            </button>

            {/* Additional Info */}
            <div className="checkout-info">
              <p>By placing your order, you agree to ShopHub's privacy notice and conditions of use.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckoutPage;