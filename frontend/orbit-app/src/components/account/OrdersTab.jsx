import React, { useState, useEffect } from 'react';
import { ordersAPI } from '../../services/api';
import { FaBox, FaExternalLinkAlt, FaHeadset } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';

const OrdersTab = ({ onNavigate, onSelectProduct, onSwitchToSupport }) => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [expandedOrder, setExpandedOrder] = useState(null);
  const { addMultipleToCart } = useCart();

  useEffect(() => {
    loadOrders();
  }, []);

  const loadOrders = async () => {
    try {
      const response = await ordersAPI.getOrders();
      setOrders(response.data);
    } catch (error) {
      console.error('Error loading orders:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleBuyAgain = (items) => {
    // Add all items from the order to cart at once
    addMultipleToCart(items);
    const totalItems = items.reduce((sum, item) => sum + item.quantity, 0);
    alert(`${totalItems} item(s) added to cart!`);
  };

  const handleViewItem = (item) => {
    onSelectProduct(item);
    onNavigate('product');
  };

  const handleProductSupport = (orderNumber) => {
    onSwitchToSupport(orderNumber);
  };

  const toggleOrderDetails = (orderId) => {
    setExpandedOrder(expandedOrder === orderId ? null : orderId);
  };

  if (loading) {
    return <div className="loading">Loading orders...</div>;
  }

  return (
    <div className="orders-tab-enhanced">
      <h3 className="tab-title">Your Orders</h3>
      {orders.length === 0 ? (
        <div className="empty-state">
          <p>No orders yet</p>
        </div>
      ) : (
        <div className="enhanced-orders-list">
          {orders.map(order => (
            <div key={order.id} className="enhanced-order-card">
              <div className="enhanced-order-header">
                <div className="order-meta">
                  <div className="order-meta-row">
                    <span className="order-label">Order Placed:</span>
                    <span className="order-value">{order.date}</span>
                  </div>
                  <div className="order-meta-row">
                    <span className="order-label">Total:</span>
                    <span className="order-value">${order.total}</span>
                  </div>
                  <div className="order-meta-row">
                    <span className="order-label">Ship To:</span>
                    <span className="order-value">{order.shipTo || 'John Doe'}</span>
                  </div>
                </div>
                <div className="order-id-section">
                  <span className="order-id-label">Order #</span>
                  <span className="order-id-value">{order.orderNumber}</span>
                  <button 
                    className="order-details-link"
                    onClick={() => toggleOrderDetails(order.id)}
                  >
                    Order Details <FaExternalLinkAlt size={12} />
                  </button>
                </div>
              </div>

              <div className="order-status-banner">
                <span className={`enhanced-order-status ${order.status.toLowerCase().replace(' ', '-')}`}>
                  {order.status}
                </span>
              </div>

              <div className="enhanced-order-items">
                {order.items.map((item) => (
                  <div key={item.id} className="enhanced-order-item">
                    <div className="enhanced-item-image">
                      {item.imgUrl ? (
                        <img src={item.imgUrl} alt={item.name} />
                      ) : (
                        <span className="enhanced-item-emoji">{item.image || '📦'}</span>
                      )}
                    </div>
                    <div className="enhanced-item-details">
                      <p className="enhanced-item-name">{item.name}</p>
                      <p className="enhanced-item-qty">Qty: {item.quantity} × ${item.price}</p>
                    </div>
                    <div className="enhanced-item-actions">
                      <button 
                        className="item-action-btn view-item-btn"
                        onClick={() => handleViewItem(item)}
                      >
                        View Item
                      </button>
                      <p className="enhanced-item-total">${(item.price * item.quantity).toFixed(2)}</p>
                    </div>
                  </div>
                ))}
              </div>

              {expandedOrder === order.id && (
                <div className="order-details-expanded">
                  <h4>Order Details</h4>
                  <div className="order-detail-info">
                    <div className="detail-row">
                      <span>Shipping Address:</span>
                      <span>{order.shippingAddress || '123 Main St, New York, NY 10001'}</span>
                    </div>
                    <div className="detail-row">
                      <span>Payment Method:</span>
                      <span>{order.paymentMethod || 'Orbit Pay'}</span>
                    </div>
                    <div className="detail-row">
                      <span>Order Total:</span>
                      <span className="detail-total">${order.total}</span>
                    </div>
                  </div>
                </div>
              )}

              <div className="enhanced-order-actions">
                <button 
                  className="order-action-btn buy-again-btn"
                  onClick={() => handleBuyAgain(order.items)}
                >
                  <FaBox size={16} />
                  Buy Again
                </button>
                <button 
                  className="order-action-btn support-btn"
                  onClick={() => handleProductSupport(order.orderNumber)}
                >
                  <FaHeadset size={16} />
                  Get Product Support
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default OrdersTab;