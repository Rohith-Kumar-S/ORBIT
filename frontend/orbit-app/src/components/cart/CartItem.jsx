import React from 'react';
import { FaPlus, FaMinus, FaTrash } from 'react-icons/fa';
import { useCart } from '../../context/CartContext';

const CartItem = ({ item }) => {
  const { updateQuantity, removeFromCart } = useCart();

  // Helper function to check if image is a URL or emoji
  const isImageUrl = (image) => {
    return image && (image.startsWith('http') || image.startsWith('/'));
  };

  return (
    <div className="cart-item">
      <div className="cart-item-content">
        <div className="cart-item-image">
          {isImageUrl(item.imgUrl) ? (
            <img src={item.imgUrl} alt={item.title} />
          ) : (
            item.imgUrl
          )}
        </div>
        <div className="cart-item-details">
          <h3 className="cart-item-name">{item.title}</h3>
          <p className="cart-item-category">{item.category}</p>
          <p className="cart-item-price">${item.price}</p>
        </div>
        <div className="cart-item-actions">
          <div className="quantity-controls">
            <button
              className="quantity-btn"
              onClick={() => updateQuantity(item.id, -1)}
            >
              <FaMinus size={16} />
            </button>
            <span className="quantity-display">{item.quantity}</span>
            <button
              className="quantity-btn"
              onClick={() => updateQuantity(item.id, 1)}
            >
              <FaPlus size={16} />
            </button>
          </div>
          <button
            className="remove-btn"
            onClick={() => removeFromCart(item.id)}
          >
            <FaTrash size={20} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default CartItem;

